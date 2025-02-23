package com.quemsi.commons.dto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quemsi.commons.util.Exceptions;
import com.quemsi.commons.util.SizeParser;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class HumanReadableSizeDeserializer extends JsonDeserializer<Long>{
    
    private static final Pattern SIZE_PATTERN = Pattern.compile("^([0-9.]{1,6}) *(MB|GB|KB)");
    
    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getText();
        if(value == null){
            return null;
        }
        Matcher m = SIZE_PATTERN.matcher(value);
        if(!m.matches()){
            throw Exceptions.badRequest("invalid-size-format").withExtra("value", value).get();
        }
        return SizeParser.parse(m.group(1), m.group(2));
    }
}
