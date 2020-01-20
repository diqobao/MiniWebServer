package edu.upenn.cis.cis455.util;

import junit.framework.TestCase;

public class FileParserTest extends TestCase {

    public void testTrimURI() {
        String uri = "//test/path/0/";
        String trimmedURI = FileParser.trimURI(uri);
        String expected = "test/path/0";
        assertEquals(expected, trimmedURI);
    }

    public void testParseHtmlFile() {
    }
}