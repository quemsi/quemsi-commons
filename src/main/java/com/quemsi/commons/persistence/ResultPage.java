package com.quemsi.commons.persistence;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView({Views.OnlyIdName.class, Views.FkBase.class})
public class ResultPage <T>{
	private int page;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private int size;
	private List<T> content;
	private boolean hasContent;
	boolean first;
	boolean last;
}
