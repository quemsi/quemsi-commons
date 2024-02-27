package com.biddflux.commons.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    @Autowired
    private ObjectMapper objectMapper;

    public boolean asBoolean(JsonNode node, boolean def){
        return node == null?def:node.asBoolean();
    }
    @SuppressWarnings("unchecked")
    public <T> Set<T> asSet(JsonNode node){
        if(node == null){
            return null;
        }
        return objectMapper.convertValue(node, Set.class);    
    }
}
