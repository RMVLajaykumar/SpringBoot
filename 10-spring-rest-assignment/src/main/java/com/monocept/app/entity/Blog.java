package com.monocept.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.ConstructorParameters;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="blog")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message="title should not be empty")
	private String title;
	@NotBlank(message="give some category")
	private String category;
	@NotBlank(message="enter data")
	@Size(min=10,max=200 ,message="please check size")
	private String data;
	@NotNull(message = "Please enter the published date")
	@FutureOrPresent(message = "The published date must be in the present or future")
	private LocalDateTime publishedDate = LocalDateTime.now();
	private boolean published;
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, orphanRemoval = true, mappedBy = "blog")
	@JsonManagedReference
	private List<Comment> comment;
	
	public void addComment(Comment comment2) {
		comment.add(comment2);

		
	}
	public void remove(Comment comment2) {
		comment.remove(comment2);
		
	}
	
	
	
	

}
