package com.community.communitypropertymgmtsystem.repository;

import com.community.communitypropertymgmtsystem.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository <Resident, Long> {

}
