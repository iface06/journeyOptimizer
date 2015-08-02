
package de.as.roadRunners.logging;


public interface Logger {
    
    public void error (Exception ex, String message);
    public void info (String message);

}
