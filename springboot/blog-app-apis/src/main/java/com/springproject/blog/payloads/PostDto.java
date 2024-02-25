package com.springproject.blog.payloads;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.springproject.blog.entities.Comment;
import com.springproject.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class PostDto {
	private Integer postId;
	
	private String title;
	private String content;
	private String imageName;
	private Date addDate; 
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto>comments = new HashSet<>();

		
	
	
	

}
