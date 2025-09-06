package com.quemsi.commons.persistence;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultPage <T>{
	private Integer page;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalElements;
	private Integer size;
	private List<T> content;
	private boolean hasContent;
	boolean first;
	boolean last;
}
