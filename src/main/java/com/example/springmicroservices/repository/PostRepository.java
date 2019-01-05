package com.example.springmicroservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springmicroservices.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
