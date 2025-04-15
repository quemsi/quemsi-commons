package com.quemsi.commons.dto;

import java.io.IOException;

import com.quemsi.commons.util.SizeParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class HumanReadableSizeSerializer extends JsonSerializer<Long>{
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value != null){
            gen.writeString(SizeParser.toString(value));
        }
    }
}
