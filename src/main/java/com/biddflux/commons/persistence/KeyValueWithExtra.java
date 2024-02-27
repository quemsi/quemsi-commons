package com.biddflux.commons.persistence;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueWithExtra extends KeyValue {
	private Map<String, Object> extra;
	
	@Builder
	public KeyValueWithExtra(String key, String value, Map<String, Object> extra) {
		super(key, value);
		this.extra = extra;
	}
}
