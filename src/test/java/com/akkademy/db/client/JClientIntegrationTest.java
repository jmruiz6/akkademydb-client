package com.akkademy.db.client;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

@Ignore
public class JClientIntegrationTest {
    JClient client = new JClient("192.168.1.60:2552");

    @Test
    public void itShouldSetRecord() throws Exception {
        client.set("123", 123);
        Integer result = (Integer) ((CompletableFuture) client.get("123")).get();
        assert(result == 123);
        client.terminate();
    }
}