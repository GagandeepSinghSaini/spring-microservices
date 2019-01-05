package com.example.springmicroservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springmicroservices.beans.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

}
