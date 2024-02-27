package com.biddflux.commons.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDto<K extends Serializable> {
	@JsonView(Views.OnlyIdName.class)
	private K id;
	private boolean active;
}

