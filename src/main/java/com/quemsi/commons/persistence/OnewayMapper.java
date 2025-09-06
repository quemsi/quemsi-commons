package com.quemsi.commons.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface OnewayMapper<D, E> {
	default Optional<D> toOptionalDTO(Optional<E> e){
		if(e == null){
			return null;
		}
		if(e.isEmpty()){
			return Optional.empty();
		}
		return Optional.of(toDTO(e.get()));
	}
	D toDTO(E entity);
	List<D> toDTOList(List<E> entities);
	@SuppressWarnings("unchecked")
	default ResultPage<D> toDTOPage(Object o) {
		if (o instanceof Page) {
			Page<E> page = (Page<E>) o;
			List<D> content = toDTOList(page.getContent());
			if(page.getPageable().isUnpaged()){
				return new ResultPage<>(null, null, 1, Long.valueOf(content.size()), content.size(), content, !content.isEmpty(), true, true);
			}
			return new ResultPage<>(Integer.valueOf(page.getPageable().getPageNumber()), page.getPageable().getPageSize(), page.getTotalPages(), page.getTotalElements(), page.getSize(), content,
					!content.isEmpty(), page.isFirst(), page.isLast());
		} else if(o instanceof List) {
			List<E> list = (List<E>)o;
			List<D> content = toDTOList(list);
			return new ResultPage<>(0, list.size(), 1, Long.valueOf(list.size()), list.size(), content, !list.isEmpty(), true, true);
		}
		return null;
	}
}
