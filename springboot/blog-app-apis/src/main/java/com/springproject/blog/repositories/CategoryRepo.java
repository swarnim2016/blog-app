package com.springproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
