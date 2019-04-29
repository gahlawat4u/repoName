package com.gms.xms.weblib.security;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Posted from BufferedHttpResponseWrapper.java
 * <p>
 * Author Toantq Date Apr 4, 2015 Time: 2:30:20 AM
 */
public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper {

    PrintWriter writer = null;
    ByteArrayOutputStream baos = null;

    public BufferedHttpResponseWrapper(HttpServletResponse response) {
        super(response);
        baos = new ByteArrayOutputStream();
        writer = new PrintWriter(baos);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    public String getOutput() {
        writer.flush();
        writer.close();
        return baos.toString();
    }
}