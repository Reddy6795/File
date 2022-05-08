package com.asbnotebook.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String error;
	private Integer count;
	private List<Codes> errors;
	private String path;
}