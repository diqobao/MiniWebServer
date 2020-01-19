package edu.upenn.cis.cis455.m1.server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.servlet.http.HttpServletResponse;

import edu.upenn.cis.cis455.m1.server.implementations.MockRequestHandler;
import edu.upenn.cis.cis455.m1.server.interfaces.HttpRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.upenn.cis.cis455.ServiceFactory.*;

import edu.upenn.cis.cis455.ServiceFactory;
import edu.upenn.cis.cis455.exceptions.HaltException;
import edu.upenn.cis.cis455.m1.server.interfaces.Request;
import edu.upenn.cis.cis455.m1.server.interfaces.Response;
import edu.upenn.cis.cis455.util.HttpParsing;
import org.mockito.Mock;

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
        Map<String, List<String>> parms = new HashMap<>();
        HttpParsing.parseRequest(socket.getRemoteSocketAddress().toString(), inputStream, headers, parms);
        MockRequestHandler httpReqHandler = new MockRequestHandler(); // TODO: 2020/1/19 replace MOCK by functional one
        httpReqHandler.handle(request, response);
        OutputStream outputStream = socket.getOutputStream();
        String httpResponse = String.format("HTTP/1.1 %d OK\n\"Content-type: %s;\r\n\r\nHello Word", response.status(), "text/html");
        outputStream.write(httpResponse.getBytes("UTF-8"));;
        outputStream.flush();
        outputStream.close();
        return true;
    }
}
