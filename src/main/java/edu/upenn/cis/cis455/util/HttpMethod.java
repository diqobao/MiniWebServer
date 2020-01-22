package edu.upenn.cis.cis455.util;

import java.util.HashMap;

public enum HttpMethod {
    get, post, put, patch, delete, head, trace, connect, options, before, after, afterafter, unsupported;

    HashMap<String, HttpMethod> strToMethod;

    public HttpMethod convertFromStr(String methodStr) {
        if(!strToMethod.containsKey(methodStr)) {
            return null; // TODO: 2020/1/22 throw exception?
        }
        return strToMethod.get(methodStr);
    }
}

