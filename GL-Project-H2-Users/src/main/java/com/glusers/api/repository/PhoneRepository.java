package com.glusers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glusers.api.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer>{

}
