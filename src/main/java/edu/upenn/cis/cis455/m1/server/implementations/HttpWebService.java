package edu.upenn.cis.cis455.m1.server.implementations;

import edu.upenn.cis.cis455.handlers.Route;
import edu.upenn.cis.cis455.m1.server.HttpServer;
import edu.upenn.cis.cis455.m1.server.interfaces.WebService;

public class HttpWebService extends WebService {
    private int maxThread;
    private int port;
    private String dir;
    String address;

    public HttpWebService(int _port, String _dir, int _maxThread, String _address) {
        basicServer = new HttpServer(_port, _dir, _maxThread);
        port = _port;
        dir = _dir;
        maxThread = _maxThread;
        address = _address;
    }

    @Override
    public void start() {
        basicServer.start();
    }

    @Override
    public void stop() {
    }

    @Override
    public void staticFileLocation(String directory) {
        dir = directory;
    }

    @Override
    public void get(String path, Route route) {

    }

    @Override
    public void post(String path, Route route) {

    }

    @Override
    public void put(String path, Route route) {

    }

    @Override
    public void delete(String path, Route route) {

    }

    @Override
    public void ipAddress(String ipAddress) {

    }

    @Override
    public void port(int port) {

    }

    @Override
    public void threadPool(int threads) {

    }

    @Override
    public void head(String path, Route route) {

    }
}
