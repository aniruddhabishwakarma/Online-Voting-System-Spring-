package com.OnlineVotingSystem.OVS.repository;

import com.OnlineVotingSystem.OVS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByuserName(String userName);
}
