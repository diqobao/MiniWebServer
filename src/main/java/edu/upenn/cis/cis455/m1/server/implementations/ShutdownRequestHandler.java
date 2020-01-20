package edu.upenn.cis.cis455.m1.server.implementations;

import edu.upenn.cis.cis455.exceptions.HaltException;
import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;

import java.nio.file.Path;

public class ShutdownRequestHandler implements HttpRequestHandler {
    Path serverRoot;

    public ShutdownRequestHandler(Path serverRoot) {
        this.serverRoot = serverRoot;
    }

    @Override
    public void handle(Request request, Response response) throws HaltException {

    }
}
