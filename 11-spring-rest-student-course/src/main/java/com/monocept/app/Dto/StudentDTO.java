package com.monocept.app.Dto;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(value = Include.NON_NULL)
public class StudentDTO {
	private long id;
	private String studentName;
	private List<CourseDTO> courses;
}