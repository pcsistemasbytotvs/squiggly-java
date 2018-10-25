package com.github.bohnman.squiggly.web;

import com.github.bohnman.squiggly.parser.SquigglyParser;

/**
 * Custom context provider that gets the filter expression from the request.
 */
public class RequestExpandableContextProvider extends RequestSquigglyContextProvider {

  public RequestExpandableContextProvider() {
    this("expand", "");
  }

  public RequestExpandableContextProvider(String filterParam, String defaultFilter) {
    super(new SquigglyParser(), filterParam, defaultFilter);
  }
}
