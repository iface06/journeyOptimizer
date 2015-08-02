
package de.as.roadRunners.app.journeyCommands;

import de.as.roadRunners.app.entities.User;


public class CommandContext {
    
    public static User DEFAULT_USER;
    
    static {
        DEFAULT_USER = new User();
        DEFAULT_USER.setId("553270d8e4b00a6d021b810e");
        DEFAULT_USER.setEmailAddress("default@user.local");
        DEFAULT_USER.setName("Musteruser");
        DEFAULT_USER.setHashedPassword("12345");
    }
    
    private User user;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    

    

}
