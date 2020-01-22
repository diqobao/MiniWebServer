package edu.upenn.cis.cis455;

public class HandlerMatcher {
    RoutesService routesService;
    FiltersService filtersService;

    public HandlerMatcher() {
        routesService = new RoutesService();
        filtersService = new FiltersService();
    }

    public void init() {

    }
}
