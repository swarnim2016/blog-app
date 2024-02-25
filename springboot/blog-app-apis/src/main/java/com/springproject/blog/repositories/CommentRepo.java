package com.springproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
