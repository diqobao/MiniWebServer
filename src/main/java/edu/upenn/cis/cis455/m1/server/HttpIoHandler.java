package edu.upenn.cis.cis455.m1.server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.upenn.cis.cis455.ServiceFactory.*;

import edu.upenn.cis.cis455.exceptions.HaltException;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import edu.upenn.cis.cis455.util.HttpParsing;

/**
 * Handles marshalling between HTTP Requests and Responses
 */
public class HttpIoHandler {
    final static Logger logger = LogManager.getLogger(HttpIoHandler.class);

    /**
     * Sends an exception back, in the form of an HTTP response code and message.  Returns true
     * if we are supposed to keep the connection open (for persistent connections).
     */
    public static boolean sendException(Socket socket, Request request, HaltException except) {
        return true;
    }

    /**
     * Sends data back.   Returns true if we are supposed to keep the connection open (for 
     * persistent connections).
     */
    public static boolean sendResponse(Socket socket, Request request, Response response) throws IOException {
        InputStream inputStream = socket.getInputStream();
        Map<String, String> headers = new HashMap<>();
        Map<String, List<String>> params = new HashMap<>();
        String uri = HttpParsing.parseRequest(socket.getRemoteSocketAddress().toString(), inputStream, headers, params);
        request = createRequest(socket, uri, true, headers, params);
        HttpRequestHandler reqHandler = createRequestHandlerInstance(Paths.get("./www"));
        reqHandler.handle(request, response);
        OutputStream outputStream = socket.getOutputStream();
        String httpResponse = String.format("HTTP/1.1 %d OK\n", response.status())
                + String.format("Content-Type: %s", response.getContentType())
                + "\r\n\r\n"
                + response.body();
        outputStream.write(httpResponse.getBytes("UTF-8"));;
        outputStream.flush();
        outputStream.close();
        return true;
    }
}
