package com.quemsi.commons.util;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;

public class ApacheDurationDeserializer extends DurationDeserializer {

    @Override
    public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if(parser.currentToken() == JsonToken.START_ARRAY){
            LinkedList<Integer> vals = new LinkedList<>();
            parser.nextToken();
            while(parser.getCurrentToken() != JsonToken.END_ARRAY){
                vals.addLast(parser.getValueAsInt());
                parser.nextToken();
            }
            Integer[] arr = vals.reversed().toArray(new Integer[vals.size()]);
            long seconds = 0;
            if(arr.length > 0){ // seconds
                seconds += arr[0];
            }
            if(arr.length > 1){ //minutes
                seconds += arr[1] * 60;
            }
            if(arr.length > 2){ // hours
                seconds += arr[2] * 60 * 60;
            }
            if(arr.length > 3){ // days
                seconds += arr[3] * 24 * 60 * 60;
            }
            return Duration.ofSeconds(seconds);
        }
        return super.deserialize(parser, context);
    }

    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt, Duration intoValue) throws IOException {
        return super.deserialize(p, ctxt, intoValue);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        return new ApacheDurationDeserializer();
    }
}
