package edu.upenn.cis.cis455.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public static String trimURI(String uri) {
        return uri.replaceAll("^/*|/*$", "");
    }

    public static String parseHtmlFile(Path uri) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(uri.toString()));
            String str;
            while ((str = in.readLine()) != null) {
                content.append(str);
            }
            in.close();
        } catch (IOException e) {
            return "NO FILE ERROR";
        }
        return content.toString();
    }
}
