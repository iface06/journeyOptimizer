
package de.as.roadRunners.sessions;


public interface SessionManager {
    
    public Object get(String key);
    public void put(String key, Object o);
    public void delete(String key);

}
