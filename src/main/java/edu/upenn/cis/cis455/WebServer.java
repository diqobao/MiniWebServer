package edu.upenn.cis.cis455;

import org.apache.logging.log4j.Level;

public class WebServer {
    static String dir;
    static String ipAddress;
    static int port;
    public static void main(String[] args) {
        org.apache.logging.log4j.core.config.Configurator.setLevel("edu.upenn.cis.cis455", Level.DEBUG);
        
        // TODO: make sure you parse *BOTH* command line arguments properly
//        port = Integer.parseInt(args[0]);
//        dir = args[1];
        port = 7878;
        dir = "./www";
        ipAddress = "http://127.0.0.1/";
        System.out.println("Launching on port " + port);
        System.out.println("File location " + dir);
        // TODO: launch your server daemon
        ServiceFactory.up(dir, ipAddress, port, 8);
        System.out.println("Waiting to handle requests!");
    }
}
