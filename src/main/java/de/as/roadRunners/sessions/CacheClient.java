package de.as.roadRunners.sessions;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class CacheClient implements SessionManager{

    private final static String MEMCACHED_SERVER_IP = "192.168.178.44";
    private final static int MEMCACHED_SERVER_PORT = 11211;

    private static MemcachedClient client = null;

    public CacheClient connect() {
        try {
            if (client == null) {
                client = new XMemcachedClient(MEMCACHED_SERVER_IP, MEMCACHED_SERVER_PORT);
            }
        } catch (IOException ex) {
            handleConnectionException(ex);
        }

        return this;
    }

    public Object get(String key) {
        connect();
        try {
            return client.get(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            handleExceptionWhileTryToGetValueFromCache(ex, key);
        }
        
        return null;
    }
    
    public synchronized void put(String key, Object value){
        connect();
        try {
            client.add(key, 100, value);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            handleExceptionWhileTryToAddValueToCache(ex, key);
        }
    }

    @Override
    public synchronized void delete(String key) {
        connect();
        try {
            client.delete(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            handleExceptionWhileDeletingValueFromCache(ex, key);
        }
    }
    
    protected void handleConnectionException(IOException ex) throws RuntimeException {
        throw new RuntimeException("Error while connection Memcached Server on IP " + MEMCACHED_SERVER_IP + " Port " + MEMCACHED_SERVER_PORT, ex);
    }

    protected void handleExceptionWhileTryToGetValueFromCache(java.lang.Exception ex, String key) {
        throw new RuntimeException("Error while getting cached object with key " + key, ex);
    }

    protected void handleExceptionWhileTryToAddValueToCache(java.lang.Exception ex, String key) {
        throw new RuntimeException("Error while adding object to cache with key " + key, ex); 
    }

    protected void handleExceptionWhileDeletingValueFromCache(java.lang.Exception ex, String key) {
        throw new RuntimeException("Error while deleting object form cache with key " + key, ex);
    }

}
