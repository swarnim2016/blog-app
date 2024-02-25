package com.springproject.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.blog.entities.Comment;
import com.springproject.blog.entities.Post;
import com.springproject.blog.exceptions.ResourceNotFoundException;
import com.springproject.blog.payloads.CommentDto;
import com.springproject.blog.repositories.CommentRepo;
import com.springproject.blog.repositories.PostRepo;
import com.springproject.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id" ,postId));
		Comment com = this.modelMapper.map(commentDto,Comment.class);
		com.setPost(post);
		Comment savedcom = this.commentRepo.save(com);
		return this.modelMapper.map(savedcom, CommentDto.class);
			
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id" ,commentId));;
		this.commentRepo.delete(com);

	}

}
