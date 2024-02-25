package com.springproject.blog.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter


public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min =5 ,message = "size must be greater than or equal to 5 characters")
	 private String categoryTitle;
	@NotBlank
	@Size(min = 10 ,message ="size must be greater than or equal to 10 characters")
	 private String categoryDescription;
}
