package com.monocept.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monocept.app.dto.BlogDTO;
import com.monocept.app.dto.CommentDTO;
import com.monocept.app.entity.Blog;
import com.monocept.app.entity.Comment;
import com.monocept.app.exception.BlogNotFoundException;
import com.monocept.app.exception.CommentNotFoundException;
import com.monocept.app.repository.BlogRepository;
import com.monocept.app.repository.CommentRepository;
import com.monocept.app.util.PagedResponse;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;
    private CommentRepository commentRepository;

   

	public BlogServiceImpl(BlogRepository blogRepository, CommentRepository commentRepository) {
		super();
		this.blogRepository = blogRepository;
		this.commentRepository = commentRepository;
	}

	@Override
    public PagedResponse<BlogDTO> getAllBlog(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy);
        if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Blog> blogPage = blogRepository.findAll(pageable);
        List<BlogDTO> blogDTOs = convertBlogToBlogDTO(blogPage.getContent());
        return new PagedResponse<>(blogDTOs, blogPage.getNumber(), blogPage.getSize(),
                blogPage.getTotalElements(), blogPage.getTotalPages(), blogPage.isLast());
    }

    @Override
    public BlogDTO getBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new BlogNotFoundException("Blog not found with the id: " + id);
        }
        return convertBlogToBlogDTO(blog);
    }

    @Override
    public BlogDTO addandUpdateBlog(BlogDTO blogDTO) {
        Blog blog = convertBlogDTOToBlog(blogDTO);
        if (blogDTO.getId() == 0) {
            return convertBlogToBlogDTO(blogRepository.save(blog));
        } else {
            Blog existingBlog = blogRepository.findById(blogDTO.getId()).orElse(null);

            if (existingBlog != null) {
                existingBlog.setTitle(blog.getTitle());
                existingBlog.setCategory(blog.getCategory());
                existingBlog.setData(blog.getData());
                existingBlog.setPublishedDate(blog.getPublishedDate());
                existingBlog.setPublished(blog.isPublished());

               
                if (existingBlog.getComment() != null) {
                    existingBlog.getComment().clear();
                    if (blog.getComment() != null) {
                        for (Comment comment : blog.getComment()) {
                            comment.setBlog(existingBlog); 
                            existingBlog.getComment().add(comment);
                        }
                    }
                } else {
                    existingBlog.setComment(blog.getComment());
                }

                return convertBlogToBlogDTO(blogRepository.save(existingBlog));
            } else {
                throw new BlogNotFoundException("Blog not found with id: " + blogDTO.getId());
            }
        }
    }

    

    private BlogDTO convertBlogToBlogDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setCategory(blog.getCategory());
        blogDTO.setData(blog.getData());
        blogDTO.setPublishedDate(blog.getPublishedDate());
        blogDTO.setPublished(blog.isPublished());
        blogDTO.setComments(convertCommentsToCommentDTOs(blog.getComment())); 
        return blogDTO;
    }

    private List<BlogDTO> convertBlogToBlogDTO(List<Blog> blogs) {
        List<BlogDTO> blogDTOs = new ArrayList<>();
        for (Blog blog : blogs) {
            blogDTOs.add(convertBlogToBlogDTO(blog));
        }
        return blogDTOs;
    }

    private Blog convertBlogDTOToBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setId(blogDTO.getId());
        blog.setTitle(blogDTO.getTitle());
        blog.setCategory(blogDTO.getCategory());
        blog.setData(blogDTO.getData());
        blog.setPublishedDate(blogDTO.getPublishedDate());
        blog.setPublished(blogDTO.isPublished());
        blog.setComment(convertCommentDTOsToComments(blogDTO.getComments(), blog)); 
        return blog;
    }

    private List<Comment> convertCommentDTOsToComments(List<CommentDTO> commentDTOs, Blog blog) {
        List<Comment> comments = new ArrayList<>();
        if (commentDTOs != null) {
            for (CommentDTO commentDTO : commentDTOs) {
                Comment comment = new Comment();
                comment.setId(commentDTO.getId());
                comment.setDescription(commentDTO.getDescription());
                comment.setBlog(blog); 
                comments.add(comment);
            }
        }
        return comments;
    }

    private List<CommentDTO> convertCommentsToCommentDTOs(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        if (comments != null) {
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setDescription(comment.getDescription());
                // No need to set the Blog reference here
                commentDTOs.add(commentDTO);
            }
        }
        return commentDTOs;
    }

    @Override
    public String deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new BlogNotFoundException("Blog not found with the id: " + id);
        }
        blogRepository.deleteById(id);
        return "deleted successfully";
    }

	@Override
	public BlogDTO addComment(int id, CommentDTO commentDTO) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if(blog!=null) {
		Comment comment = convertCommentDTOToComment(commentDTO,blog);
		blog.addComment(comment);
		return convertBlogToBlogDTO(blogRepository.save(blog));
		}
		else {
			throw new BlogNotFoundException("Blog not found with the id: " + id);
		}
	}
	
	private Comment convertCommentDTOToComment(CommentDTO commentDTO,Blog blog) {
		Comment comment=new Comment();
		comment.setId(commentDTO.getId());
		comment.setDescription(commentDTO.getDescription());
		comment.setBlog(blog);
		return comment;
	}

	@Override
	public BlogDTO removeComment(int bid, int id) {
	    Blog blog = blogRepository.findById(bid).orElse(null);
	    if (blog != null) {
	        Comment comment = commentRepository.findById(id).orElse(null);
	        if (comment != null && blog.getComment().contains(comment)) {
	            blog.getComment().remove(comment);
	            blogRepository.save(blog);
	            commentRepository.delete(comment);
	            return convertBlogToBlogDTO(blog);
	        }
	        else {
	        	throw new CommentNotFoundException("comment not found with id:"+id);
	        }
	    }
	    else {
	    	throw new BlogNotFoundException("Blog not found with the id: " + bid);	
	  
	    }
	}

	
	
}
