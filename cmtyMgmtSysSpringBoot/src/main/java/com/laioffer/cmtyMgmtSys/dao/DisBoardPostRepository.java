package com.laioffer.cmtyMgmtSys.dao;

import com.laioffer.cmtyMgmtSys.entity.DisBoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisBoardPostRepository extends JpaRepository<DisBoardPost, Long> {
}
