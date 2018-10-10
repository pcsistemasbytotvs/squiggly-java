package com.github.bohnman.squiggly.context;

import com.github.bohnman.squiggly.parser.SquigglyNode;

import java.util.List;

/**
 * A squiggly context provides parsing and filtering information to the
 * {@link com.github.bohnman.squiggly.filter.SquigglyPropertyFilter}.  Contexts are usually not thread safe.
 */
public interface SquigglyContext {

    /**
     * Get the top-level bean class being filtered.
     *
     * @return bean class
     */
    Class getBeanClass();

    /**
     * Get the parsed nodes.
     *
     * @return nodes
     */
    List<SquigglyNode> getNodes();

    /**
     * Get the filter expression.
     *
     * @return filter expression
     */
    String getFilter();

}
