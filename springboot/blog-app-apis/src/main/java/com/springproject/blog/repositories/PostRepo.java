package com.springproject.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.blog.entities.Category;
import com.springproject.blog.entities.Post;
import com.springproject.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	//custom finder method in repository
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	

	
	

}
