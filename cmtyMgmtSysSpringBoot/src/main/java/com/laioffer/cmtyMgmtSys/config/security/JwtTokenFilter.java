package com.laioffer.cmtyMgmtSys.config.security;

import com.laioffer.cmtyMgmtSys.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // Get authorization header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        //if header is null
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        //validate header
        if (header.isEmpty() || ! header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        //get the jwtToken from the header
        final String jwtToken = header.split(" ")[1].trim();
        String username = null;
        //get the username from token with exception handling
        try {
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }
        //get the UserDetails from username decrypted from the token
        if (username != null) {
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) auth;
            //upAuth.getPrincipal();
            chain.doFilter(request, response);
        }
    }
}

