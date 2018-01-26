/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication.controller;

import biometricauthentication.model.security;
import biometricauthentication.view.Login;
import java.util.ArrayList;
/**
 *
 * @author Gangani Chamika
 */
public class Controller {
    security security;
    
    public Controller(){
        security = new security();       
        Login login = new Login(this);
        login.setVisible(true);    
    }
    
    public boolean checkUserNameExists(String user){
        ArrayList<String> users = security.getUserNames();
        
        for(String name : users){
            if(user.equalsIgnoreCase(name)) return true;
        }
        
        return false;
    }
    
    public boolean createUserKeyStroke (String name){
        if (checkUserNameExists(name)){
            return false;
        }
        
        security.createUserKeyStroke(name);
        return true;
    }
    
    public void updateCurrentDataSet(String text, double[] times){
        //System.out.println(text+" controller "+times[1]);
        security.updateCurrentDataSet(text, times);
    }
    
    public void saveKeyStrokeUser(){
        security.saveKeyStrokeUser();
    }
    
    public String getSentence(int count){
        return security.getSenetence(count);
    }
    
    public String getAuthSentene(){
        return security.getAuthSentence();
    }
    
    public boolean validateKeyStrokeAuth(String userName, String sentence, double[] times){
        return security.validateKeyStrokeAuth(userName, sentence, times);
    }
}
