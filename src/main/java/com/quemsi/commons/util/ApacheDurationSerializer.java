package com.quemsi.commons.util;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;

public class ApacheDurationSerializer extends DurationSerializer {

    @Override
    public void serialize(Duration duration, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (Objects.nonNull(duration)) {
            long ticks = duration.toSeconds();
            int[] components = new int[4];
            /* Seconds */
            components[3] =(int)ticks % 60;
            ticks = ticks / 60;
            /* Minutes */
            components[2] = (int)ticks % 60;
            ticks = ticks / 60;
            /* Hours */
            components[1] = (int)ticks % 24;
            ticks = ticks / 24;
            /* Days */
            components[0] = (int)ticks;
            generator.writeArray(components, 0, components.length);
        } else {
            super.serialize(duration, generator, provider);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return new ApacheDurationSerializer();
    }
}
