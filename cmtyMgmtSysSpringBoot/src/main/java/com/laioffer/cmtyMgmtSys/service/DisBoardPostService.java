package com.laioffer.cmtyMgmtSys.service;

import com.laioffer.cmtyMgmtSys.dao.DisBoardPostRepository;
import com.laioffer.cmtyMgmtSys.entity.DisBoardPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisBoardPostService {
    @Autowired
    DisBoardPostRepository postRepo;

    public List<DisBoardPost> getAllPosts(){
        return postRepo.findAll();
    }

    public DisBoardPost addPost(DisBoardPost post){
        return postRepo.save(post);
    }
}
