package com.github.frunoman.model;

import com.github.frunoman.model_pojo.Status;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import java8.util.stream.RefStreams;
import java8.util.stream.Stream;


/**
 * @author charlie (Dmitry Baev).
 */
public class StatusDeserializer extends StdDeserializer<Status> {
    protected StatusDeserializer() {
        super(Status.class);
    }

    @Override
    public Status deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.readValueAs(String.class);
        return RefStreams.of(Status.values())
                .filter(status -> status.value().equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }
}
