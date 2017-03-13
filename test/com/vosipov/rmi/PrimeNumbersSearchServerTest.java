package com.vosipov.rmi;

import com.vosipov.rmi.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test register new client. Created by info_000 on 13-Mar-17.
 */
public class PrimeNumbersSearchServerTest {

    private PrimeNumbersSearchClient client;
    private PrimeNumbersSearchServer server;

    @BeforeMethod
    public void setUp() throws Exception {
        server = new PrimeNumbersSearchServer();
        client = new PrimeNumbersSearchClient();
    }

    @Test
    public void testRegister() throws Exception {
        server.register(client);
        Assert.assertTrue(true);
    }
}