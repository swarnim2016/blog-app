package com.springproject.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.springproject.blog.entities.User;
import com.springproject.blog.entities.Category;
import com.springproject.blog.entities.Post;
import com.springproject.blog.exceptions.ResourceNotFoundException;
import com.springproject.blog.payloads.PostDto;
import com.springproject.blog.payloads.PostResponse;
import com.springproject.blog.repositories.CategoryRepo;
import com.springproject.blog.repositories.PostRepo;
import com.springproject.blog.repositories.UserRepo;
import com.springproject.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId){
		  User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId" ,userId));
		  Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

		    Post post = this.modelMapper.map(postDto, Post.class);
		    post.setImageName("default.png");
		    post.setAddDate(new Date());
		    post.setUser(user);  
		    post.setCategory(cat);

		    Post created = this.postRepo.save(post);
		    
		    return this.modelMapper.map(created, PostDto.class);
		
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId" ,postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updated = this.postRepo.save(post);
		PostDto postDtos = this.modelMapper.map(updated, PostDto.class);
		return postDtos;
		
		
		
	}

	@Override
	public void DeletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId" ,postId));;
		this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort  = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pg = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> Pagepost = this.postRepo.findAll(pg);
		List<Post> content  = Pagepost.getContent();
		List<PostDto> postDto = content.stream().map((posts)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(Pagepost.getNumber());
		postResponse.setPageSize(Pagepost.getSize());
		postResponse.setTotalElements(Pagepost.getTotalElements());
		postResponse.setTotalPages(Pagepost.getTotalPages());
		postResponse.setLastPage(Pagepost.isLast());
		return postResponse;
		
		
		
	}

	@Override
	public PostDto getSinglePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId" ,postId));
		PostDto singlePost = this.modelMapper.map(post, PostDto.class);
		return singlePost;
	}

	@Override
	public List<PostDto> getByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));;
		List<Post>posts = this.postRepo.findByCategory(cat);
		List<PostDto>postDtos = posts.stream().map((post )->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Post>posts = this.postRepo.findByUser(user);
		List<PostDto> postDto= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post>posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto>postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
	}
	

}
