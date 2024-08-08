package com.monocept.app.Dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(value = Include.NON_NULL)
public class CourseDTO {
	private long id;
	private String title;
	private List<StudentDTO> students;
}