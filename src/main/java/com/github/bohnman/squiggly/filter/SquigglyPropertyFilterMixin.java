package com.github.bohnman.squiggly.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.bohnman.squiggly.expandable.ExpandableProperty;
import net.jcip.annotations.ThreadSafe;

/**
 * Jackson mixin that register the filter id for the @{@link SquigglyPropertyFilter}.
 */
@ThreadSafe
@JsonFilter(SquigglyPropertyFilter.FILTER_ID)
@JsonAppend(
    prepend = true,
    props = {
        @JsonAppend.Prop(name = "_expandables", value = ExpandableProperty.class)
    })
public class SquigglyPropertyFilterMixin {
}
