package edu.upenn.cis.cis455.m1.server.implementations;

import edu.upenn.cis.cis455.exceptions.HaltException;
import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import edu.upenn.cis.cis455.util.FileParser;

import java.io.IOException;
import java.nio.file.Path;

public class StaticRequestHandler implements HttpRequestHandler {
    Path serverRoot;
    public StaticRequestHandler(Path serverRoot) {
        this.serverRoot = serverRoot;
    }

    @Override
    public void handle(Request request, Response response) throws HaltException {
        String reqMethod = request.requestMethod().toUpperCase();
        switch (reqMethod) {
            case "GET":
                handleGET(request, response);
                break;
            case "POST":
                handlePOST(request, response);
                break;
            case "PUT":

                break;
            case "DELETE":

                break;
        }
    }

    private void handleGET(Request request, Response response) {
        String reqURI = request.uri();
        reqURI = FileParser.trimURI(reqURI);
        Path uri = serverRoot.resolve(reqURI);
        uri = uri.normalize();
        String contentType = getContentType(reqURI);
        try {
            response.bodyRaw(FileParser.parseHtmlFile(uri));
            response.status(200);
        } catch (IOException e) {
            response.status(404);
            response.body("File not Found");
        }finally {
            response.type(contentType);
        }
    }

    private void handlePOST(Request request, Response response) {}

    private String getContentType(String uri) {
        if (uri.endsWith(".html") || uri.endsWith(".htm"))
            return "text/html";
        else if (uri.endsWith(".jpg") || uri.endsWith(".jpeg"))
            return "image/jpeg";
        else if (uri.endsWith(".gif"))
            return "image/gif";
        else if (uri.endsWith(".class"))
            return "application/octet-stream";
        return "text/plain";
    }
}
