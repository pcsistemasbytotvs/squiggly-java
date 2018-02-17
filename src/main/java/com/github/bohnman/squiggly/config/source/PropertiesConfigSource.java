package com.github.bohnman.squiggly.config.source;

import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

public class PropertiesConfigSource implements  SquigglyConfigSource {

    private final Properties properties;
    private final String location;

    public PropertiesConfigSource(String location) {
        this(location, new Properties());
    }

    public PropertiesConfigSource(String location, Properties properties) {
        this.location = checkNotNull(location);
        this.properties = checkNotNull(properties);
    }

    public PropertiesConfigSource(URL url) {
        this(url.toString());

        try (InputStream inputStream = url.openStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(String.format("Unable to load properties [%s]", url), e);
        }
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        return MoreObjects.firstNonNull(properties.getProperty(name), defaultValue);
    }

    @Override
    public String getLocation(String name) {
        if (!properties.containsKey(name)) {
            return null;
        }
        return location;
    }
}
