package com.monocept.app.controller;

import java.util.List;

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

import com.monocept.app.dto.BlogDTO;
import com.monocept.app.dto.CommentDTO;
import com.monocept.app.entity.Blog;
import com.monocept.app.entity.Comment;
import com.monocept.app.service.BlogService;
import com.monocept.app.util.PagedResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class BlogController {
	
	private BlogService blogService;

	public BlogController(BlogService blogService) {
		super();
		this.blogService = blogService;
	}
	
	
	
	@GetMapping("blog")
	public ResponseEntity<PagedResponse<BlogDTO>> getAllBlogs
		(@RequestParam(name="page",defaultValue = "0") int page,
				@RequestParam(name="size",defaultValue = "5")int size,
				@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
				@RequestParam(name="direction",defaultValue="asc")String direction){
		
		PagedResponse<BlogDTO> blog=blogService.getAllBlog(page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<BlogDTO>>(blog,HttpStatus.OK);
		
	}
	
	@GetMapping("blog/{id}")
	public ResponseEntity<BlogDTO> getBlogId(@PathVariable(name="id")int id){
		BlogDTO blog=blogService.getBlogById(id);
		return new  ResponseEntity<BlogDTO>(blog,HttpStatus.OK);
	}
	
	
	@PostMapping("add-blog")
	public ResponseEntity<BlogDTO> createABlog(@Valid @RequestBody BlogDTO blogDTO){
		return new ResponseEntity<BlogDTO>(blogService.addandUpdateBlog(blogDTO),HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("update-blog")
		public ResponseEntity<BlogDTO> updateABlog(@Valid @RequestBody BlogDTO blogDTO){
		return new ResponseEntity<BlogDTO>(blogService.addandUpdateBlog(blogDTO),HttpStatus.ACCEPTED);
			
		}
	@DeleteMapping("delete-blog/{id}")
	public ResponseEntity<String> DeleteBlogId(@PathVariable(name="id")int id){
		String message=blogService.deleteBlogById(id);
		return new  ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PutMapping("addComment/{id}")
	public ResponseEntity<BlogDTO> addComment(@PathVariable(name = "id") int id,@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<BlogDTO>(blogService.addComment(id,commentDTO),HttpStatus.OK);
	}
	
	
	@PutMapping("{bid}/removeComment/{id}")
	public ResponseEntity<BlogDTO> removeComment(@PathVariable(name = "bid") int bid, @PathVariable(name = "id") int id) {
	    BlogDTO blogDTO = blogService.removeComment(bid, id);
	    if (blogDTO != null) {
	        return new ResponseEntity<>(blogDTO, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	
	
	
	
	
	

}
