package com.quemsi.commons.util;

import java.util.LinkedList;
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

    public int asInteger(JsonNode node, int def){
        return node == null?def:node.asInt();
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> asSet(JsonNode node){
        if(node == null){
            return null;
        }
        return objectMapper.convertValue(node, Set.class);    
    }
    @SuppressWarnings("unchecked")
    public <T> LinkedList<T> asLinkedList(JsonNode node){
        if(node == null){
            return null;
        }
        return objectMapper.convertValue(node, LinkedList.class);    
    }
}
