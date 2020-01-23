package edu.upenn.cis.cis455;

import edu.upenn.cis.cis455.handlers.Filter;
import edu.upenn.cis.cis455.handlers.Route;
import edu.upenn.cis.cis455.m1.server.HttpServer;
import edu.upenn.cis.cis455.m2.server.interfaces.WebService;
import edu.upenn.cis.cis455.util.HttpMethod;

public class HttpWebService extends WebService {
    private int maxThread;
    private int port;
    private String dir;
    String address;
    private HandlerMatcher handlerMatcher;


    public HttpWebService(int _port, String _dir, int _maxThread, String _address) {
        basicServer = new HttpServer(_port, _dir, _maxThread);
        port = _port;
        dir = _dir;
        maxThread = _maxThread;
        address = _address;
        handlerMatcher = new HandlerMatcher();
    }

    @Override
    public void start() {
        basicServer.start();
        handlerMatcher.init();
    }

    @Override
    public void stop() {
        basicServer.stop();
    }

    @Override
    public void staticFileLocation(String directory) {
        dir = directory;
    }

    @Override
    public void get(String path, Route route) {
        handlerMatcher.routesService.addRoute(HttpMethod.get, path, route);
    }

    @Override
    public void post(String path, Route route) {
        handlerMatcher.routesService.addRoute(HttpMethod.post, path, route);
    }

    @Override
    public void put(String path, Route route) {
        handlerMatcher.routesService.addRoute(HttpMethod.put, path, route);
    }

    @Override
    public void delete(String path, Route route) {
        handlerMatcher.routesService.addRoute(HttpMethod.delete, path, route);
    }

    @Override
    public void ipAddress(String ipAddress) {
        this.address = ipAddress;
    }

    @Override
    public void port(int port) {
        this.port = port;
    }

    @Override
    public void threadPool(int threads) {
        maxThread = threads;
    }

    @Override
    public void head(String path, Route route) {

    }

    @Override
    public void options(String path, Route route) {

    }

    @Override
    public void before(Filter filter) {

    }

    @Override
    public void after(Filter filter) {

    }

    @Override
    public void before(String path, String acceptType, Filter filter) {

    }

    @Override
    public void after(String path, String acceptType, Filter filter) {

    }
}
