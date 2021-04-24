package com.laioffer.cmtyMgmtSys.controller;

import com.laioffer.cmtyMgmtSys.entity.DisBoardPost;
import com.laioffer.cmtyMgmtSys.dao.DisBoardPostRepository;
import com.laioffer.cmtyMgmtSys.service.DisBoardPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DisBoardPostController {
    @Autowired
    DisBoardPostService postService;

    //get all posts
    @GetMapping("/allPosts")
    public List<DisBoardPost> getAllPosts(){
        return postService.getAllPosts();
    }

    //create a new Post
    @PostMapping("/posts")
    public DisBoardPost addPost(@RequestBody DisBoardPost post){
        return postService.addPost(post);
    }
    /*
    @GetMapping("/getPosts{id}")
    public DisBoardPost getPostById(@RequestParam("id") int id){
        Optional<DisBoardPost> optionalDisBoardPost = postService.
    }
     */
}
