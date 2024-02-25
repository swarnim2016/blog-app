package com.springproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
