package com.monocept.app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CommentErrorResponse {
	
	private int status;
	private String message;
	private LocalDateTime timeStamp;
	
}
