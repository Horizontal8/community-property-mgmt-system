package com.community.communitypropertymgmtsystem;

import com.community.communitypropertymgmtsystem.entity.Resident;
import com.community.communitypropertymgmtsystem.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class CommunityPropertyMgmtSystemApplication implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(CommunityPropertyMgmtSystemApplication.class, args);
//    }
//
//    @Autowired
//    private ResidentRepository residentRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        this.residentRepository.save(new Resident("haha","heihei","123","haha"));
//        this.residentRepository.save(new Resident("aa","bb","456","aabb"));
//        this.residentRepository.save(new Resident("xiaop","xiaoq","789","xiaop"));
//    }
//
//}

public class CommunityPropertyMgmtSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityPropertyMgmtSystemApplication.class, args);
    }

}
