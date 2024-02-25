package com.springproject.blog.services;

import java.util.List;

import com.springproject.blog.entities.Post;
import com.springproject.blog.payloads.PostDto;
import com.springproject.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void DeletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDto getSinglePost(Integer postId);
	List<PostDto> getByCategory(Integer categoryId);
	List<PostDto> getByUser(Integer userId);
	List<PostDto> searchPost(String keyword);

}
