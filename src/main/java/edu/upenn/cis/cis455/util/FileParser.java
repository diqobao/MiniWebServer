package edu.upenn.cis.cis455.util;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public static String trimURI(String uri) {
        return uri.replaceAll("^/*|/*$", "");
    }

    public static byte[] parseHtmlFile(Path uri) throws IOException {
        File file = new File(uri.toString());
        byte[] content = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int reader;
            while ((reader = inputStream.read(content)) != -1) { }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return content;
    }
}
