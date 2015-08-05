package de.as.roadRunners;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import static org.junit.Assert.*;

public class MemchachedTest {

    @Test
    public void test() throws TimeoutException, InterruptedException, MemcachedException, IOException {
        MemcachedClient client = new XMemcachedClient("192.168.178.44", 11211);

        String someObject = "Test";
        client.set("key", 3600, someObject);
//Retrieve a value.(synchronously).
        String result = client.get("key");
        assertEquals(someObject, result);

        
        
//Touch cache item ,update it's expire time to 10 seconds.
        boolean success = client.touch("key", 10);

//delete value
        client.delete("key");
    }

}
