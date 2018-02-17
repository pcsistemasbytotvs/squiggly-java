package com.github.bohnman.squiggly.config.source;

import java.util.Arrays;


import static com.google.common.base.Preconditions.checkNotNull;

public class CompositeConfigSource implements SquigglyConfigSource {

    private final Iterable<SquigglyConfigSource> sources;

    public CompositeConfigSource(SquigglyConfigSource... sources) {
        this(Arrays.asList(sources));
    }

    public CompositeConfigSource(Iterable<SquigglyConfigSource> sources) {
        this.sources = checkNotNull(sources);
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        String property = null;

        for (SquigglyConfigSource source : sources) {
            property = source.getProperty(name);

            if (property != null) {
                break;
            }
        }

        if (property == null) {
            property = defaultValue;
        }

        return property;
    }

    @Override
    public String getLocation(String name) {
        String location = null;

        for (SquigglyConfigSource source : sources) {
            location = source.getLocation(name);

            if (location != null) {
                break;
            }
        }

        if (location == null) {
            location = "not found";
        }

        return location;
    }
}
