package com.gms.delivery.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientFactory {

    private MultiThreadedHttpConnectionManager httpConnectionManager;

    public static HttpClientFactory instance = new HttpClientFactory();

    private HttpClientFactory() {
        init();
    }

    public static HttpClientFactory getInstance() {
        return instance;
    }

    private void init() {
        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(5);
        params.setMaxTotalConnections(50);
        params.setConnectionTimeout(15000);
        params.setSoTimeout(15000);
        params.setDefaultMaxConnectionsPerHost(20);
        httpConnectionManager.setParams(params);
        this.httpConnectionManager = httpConnectionManager;
    }

    public HttpClient getHttpClient() {
        return new HttpClient(httpConnectionManager);
    }
}
