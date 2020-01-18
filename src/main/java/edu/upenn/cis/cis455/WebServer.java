package edu.upenn.cis.cis455;

import org.apache.logging.log4j.Level;

public class WebServer {
    static String dir;
    static String ipAddress;
    static int port;
    public static void main(String[] args) {
        org.apache.logging.log4j.core.config.Configurator.setLevel("edu.upenn.cis.cis455", Level.DEBUG);
        
        // TODO: make sure you parse *BOTH* command line arguments properly
        System.out.println("Launching on port " + args[0]);
        System.out.println("File location " + args[1]);
        
        // TODO: launch your server daemon        
        ServiceFactory.up(dir, ipAddress, port, 10);
        
        System.out.println("Waiting to handle requests!");
    }
}
