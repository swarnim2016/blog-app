package com.springproject.blog.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springproject.blog.entities.Post;
import com.springproject.blog.payloads.ApiResponse;
import com.springproject.blog.payloads.PostDto;
import com.springproject.blog.payloads.PostResponse;
import com.springproject.blog.services.PostService;

@RestController
@RequestMapping("/api/")

public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
	        @RequestBody PostDto postDto,
	        @PathVariable Integer userId,
	        @PathVariable Integer categoryId)
	{
			PostDto created = 	this.postService.createPost(postDto, userId, categoryId);
					return new ResponseEntity<PostDto>(created,HttpStatus.CREATED);

	
		
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> post =this.postService.getByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
		
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> post =this.postService.getByCategory(userId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
	}
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
	@RequestParam(value  ="sortBy",defaultValue = "postId",required=false)String sortBy,
	@RequestParam(value ="sortDir",defaultValue = "asc",required = false)String sortDir)
	{
		
		PostResponse pr = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(pr,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> singlePostById(@PathVariable Integer postId){
		PostDto post = this.postService.getSinglePost(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable Integer postId){
		this.postService.DeletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("POST DELETED SUCCESSFULLY",true),HttpStatus.OK);
		
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
		PostDto post =this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keyword")String keyword){
		List<PostDto>posts=this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	
	
}