package edu.upenn.cis.cis455.m1.server.implementations;

import edu.upenn.cis.cis455.exceptions.HaltException;

import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class ControlRequestHandler implements HttpRequestHandler {
    final static Logger logger = LogManager.getLogger(ControlRequestHandler.class);
    Path serverRoot;

    public ControlRequestHandler(Path serverRoot) {
        this.serverRoot = serverRoot;
    }

    @Override
    public void handle(Request request, Response response) throws HaltException {
        logger.info("Received Control Call");
        // TODO: 2020/1/20 get threads status and put to response body
        response.status(200);
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<html><head><title>Response</title></head><body><h1>Thread Pool</h1><p>Test");
        responseBody.append("</p></body></html>");
    }
}
