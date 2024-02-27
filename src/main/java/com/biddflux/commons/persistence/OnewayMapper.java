package com.biddflux.commons.persistence;

import java.util.List;

import org.springframework.data.domain.Page;

public interface OnewayMapper<D, E> {
	D toDTO(E entity);
	List<D> toDTOList(List<E> entities);
	@SuppressWarnings("unchecked")
	default ResultPage<D> toDTOPage(Object o) {
		if (o instanceof Page) {
			Page<E> page = (Page<E>) o;
			List<D> content = toDTOList(page.getContent());

			return new ResultPage<>(page.getPageable().getPageNumber(), page.getPageable().getPageSize(), page.getTotalPages(), page.getTotalElements(), page.getSize(), content,
					!content.isEmpty(), page.isFirst(), page.isLast());
		} else if(o instanceof List) {
			List<E> list = (List<E>)o;
			List<D> content = toDTOList(list);
			return new ResultPage<>(0, list.size(), 1, list.size(), list.size(), content, !list.isEmpty(), true, true);
		}
		return null;
	}
}
