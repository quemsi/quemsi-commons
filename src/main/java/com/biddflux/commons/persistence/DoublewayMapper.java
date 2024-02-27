package com.biddflux.commons.persistence;

import java.util.List;


public interface DoublewayMapper<D, E> extends OnewayMapper<D, E>{
	E toEntity(D dto);
	List<E> toEntityList(List<D> dtos);
}