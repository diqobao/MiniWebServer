package edu.upenn.cis.cis455.util;

import java.util.HashMap;

public enum HttpMethod {
    get, post, put, patch, delete, head, trace, connect, options, before, after, afterafter, unsupported;

    private static HashMap<String, HttpMethod> strToMethod = new HashMap<>();

    static {
        for (HttpMethod method : values()) {
            strToMethod.put(method.toString(), method);
        }
    }

    public static HttpMethod convertFromStr(String methodStr) {
        if(!strToMethod.containsKey(methodStr)) {
            return null; // TODO: 2020/1/22 throw exception?
        }
        return strToMethod.get(methodStr);
    }
}

