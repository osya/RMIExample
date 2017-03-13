package com.vosipov.rmi;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;

/**
 * Test prime checking function. Created by info_000 on 13-Mar-17.
 */
public class PrimeNumbersSearchClientTest {
    private PrimeNumbersSearchClient client;

    @BeforeMethod
    public void setUp() throws Exception {
        client = new PrimeNumbersSearchClient();
    }

    @org.testng.annotations.Test
    public void testCheck() throws Exception {
        Assert.assertTrue(client.check(new BigDecimal(2)));

        Assert.assertTrue(client.check(new BigDecimal(3)));

        Assert.assertFalse(client.check(new BigDecimal(4)));

        Assert.assertTrue(client.check(new BigDecimal(5)));
    }
}