/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication.model;
import java.io.Serializable;
/**
 *
 * @author Gangani Chamika
 */
public class SystemUser implements Serializable{
    private final String username;
    private UserDetails details;

    public SystemUser(String username) {
        details = new AuthenticationDetails ();
        this.username = username.toLowerCase();
    }

    public UserDetails getCredintials() {
        return details;
    }

    public String getUsername() {
        return username;
    }
    
}
