package com.monocept.app.service;

import com.monocept.app.dto.BlogDTO;
import com.monocept.app.dto.CommentDTO;
import com.monocept.app.entity.Blog;
import com.monocept.app.util.PagedResponse;

import jakarta.validation.Valid;

public interface BlogService {

	PagedResponse<BlogDTO> getAllBlog(int page, int size, String sortBy, String direction);

	BlogDTO getBlogById(int id);

	 public BlogDTO addandUpdateBlog(BlogDTO blogDTO);

	String deleteBlogById(int id);

	BlogDTO addComment(int id, CommentDTO commentDTO);

	BlogDTO removeComment(int bid, int id);
	
	

}
