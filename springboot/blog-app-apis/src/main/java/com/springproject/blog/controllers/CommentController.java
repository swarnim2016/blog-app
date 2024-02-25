package com.springproject.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.blog.entities.Comment;
import com.springproject.blog.payloads.ApiResponse;
import com.springproject.blog.payloads.CommentDto;
import com.springproject.blog.services.CommentService;
@RestController
@RequestMapping("/api/")


public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto com = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(com,HttpStatus.OK);
		
	}
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("POST DELETED SUCCESSFULLY",true),HttpStatus.OK);
	}
	

}
