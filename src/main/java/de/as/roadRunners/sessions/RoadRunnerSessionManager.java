
package de.as.roadRunners.sessions;


public class RoadRunnerSessionManager implements SessionManager{

    private static final SessionManager client = new CacheClient();
    
    @Override
    public Object get(String key) {
        
        return client.get(key);
    }

    @Override
    public void put(String key, Object o) {
        client.put(key, o);
    }

    @Override
    public void delete(String key) {
        client.delete(key);
    }

    
    
}
