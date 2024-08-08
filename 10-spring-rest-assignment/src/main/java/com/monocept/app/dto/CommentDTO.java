package com.monocept.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {
    private int id;

    @NotBlank(message = "Description must not be blank. Please provide a description to add more details.")
    @Size(min = 2, max = 50, message = "Description must be between 50 and 200 characters.")
    private String description;
  
}
