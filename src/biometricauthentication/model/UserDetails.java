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
public interface UserDetails extends Serializable {
    // To check the variance value with a given login info
    public boolean getAuthentication(UserDetails loginInfo);
    
    // If success, update the statistics to make the model more accurate
    public void updateStats(UserDetails loginInfo);
}
