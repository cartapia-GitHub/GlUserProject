package com.glusers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glusers.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
