package com.github.bohnman.squiggly.expandable;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend(
    prepend = true,
    props = {
        @JsonAppend.Prop(name = "_expandables", value = ExpandableProperty.class)
})
public class ExpandableMixin {
}
