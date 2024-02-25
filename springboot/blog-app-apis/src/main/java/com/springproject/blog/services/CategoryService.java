package com.springproject.blog.services;

import java.util.List;



import com.springproject.blog.payloads.CategoryDto;

public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	void deleteCategory(Integer categoryId);
	public CategoryDto getCategory(Integer categoryId);
	List<CategoryDto> getAllCategory();

}
