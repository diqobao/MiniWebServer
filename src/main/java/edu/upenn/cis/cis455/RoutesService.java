package edu.upenn.cis.cis455;

import edu.upenn.cis.cis455.handlers.Route;
import edu.upenn.cis.cis455.util.HttpMethod;

import java.util.HashMap;

public class RoutesService {
    private HashMap<HttpMethod, HashMap<String, Route>> routes;
    boolean initialized = false;

    RoutesService() {
        routes = new HashMap<>();
    }

    public void init() {
        if(!initialized) {
            // TODO: 2020/1/22 initialize service
            for(HttpMethod method: HttpMethod.values()) {
                routes.put(method, new HashMap<>());
            }
            initialized = true;
        }
    }

    public void addRoute(HttpMethod method, String path, Route route) {
        routes.get(method).put(path, route);
    }

    public void removeRoute(HttpMethod method, String path) {
        if(!routes.get(method).containsKey(path)) {
            return ;
        }
        routes.get(method).remove(path);
    }

    public Route getRoute(HttpMethod method, String path) {
        return null;
    }
}
