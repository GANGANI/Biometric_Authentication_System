/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication.model;

import biometricauthentication.ObjectSerialization;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gangani Chamika
 */
public class security {

    private ArrayList<String> users;
    private SystemUser systemUser;

    public security() {
        loadUserNames();
    }
    
    private void loadUserNames(){
        ObjectSerialization serialization = new ObjectSerialization();
        if (serialization.isFileAvailable("usernames")){
            users = (ArrayList<String>) serialization.loadObject("usernames");
        }
        else{
            users = new ArrayList<>();
        }
    }

    public ArrayList<String> getUserNames() {
        return users;
    }
    
    private void addNewUserName(String userName){
        userName = userName.toLowerCase();
        users.add(userName);
        ObjectSerialization serialization = new ObjectSerialization();
        serialization.saveObject(users, "usernames");
    }
    
    public void createUserKeyStroke(String userName){
        SystemUser user = new SystemUser(userName);
        ObjectSerialization serialization = new ObjectSerialization();
        systemUser = user;
        serialization.saveObject(user, userName);
        addNewUserName(userName);
    }
    
    public String getSenetence(int count){
        String[] sentences = new String[10];
        
        sentences[0] = "Keystroke dynamics can be used for authentication";
        sentences[1] = "Behavioral biometrics have higher variations";
        sentences[2] = "keystroke dynamics are less reliable than physiological biometrics";
        sentences[3] = "Keystroke dynamics is a behavioral biometric";
        sentences[4] = "To authenticate user based on their typing samples";
        sentences[5] = "This biometric solution can be used by keyboard users";
        sentences[6] = "Keystroke dynamics are the patterns of rhythm and timing created when a person types";
        sentences[7] = "Keystroke dynamics does not need special hardware";
        sentences[8] = "Use of keystroke rhythm is a natural choice for computer security";
        sentences[9] = "Biometric schemes rely on aspects of body and its behavior";
        
        
        return sentences[count];
        
    }

    public void updateCurrentDataSet(String text, double[] times){
         //System.out.println(text+" security   "+times[1]);
         System.out.println(systemUser.getCredintials());
        ((AuthenticationDetails)systemUser.getCredintials()).updateDataSet(text, times);
    }
    
    public void saveKeyStrokeUser(){
        ObjectSerialization serialization = new ObjectSerialization();
        serialization.saveObject(systemUser, systemUser.getUsername());
    }
    
    public String getAuthSentence(){
        String[] sentence = new String[5];
        
        sentence[0] = "Keystroke dynamics is about how you type";
        sentence[1] = "Keystroke behaviour is used to verify identity of a person";
        sentence[2] = "Keystroke dynamics biometrics is economical";
        sentence[3] = "Keystroke event can be measured up to milliseconds";
        sentence[4] = "Keystroke patterns are harder to be reproduced ";
        
        return sentence[Math.abs(new Random().nextInt()) % 5];
    }
    
    public boolean validateKeyStrokeAuth(String userName, String sentence, double[] times){
        ObjectSerialization serialization = new ObjectSerialization();
        SystemUser user = (SystemUser) serialization.loadObject(userName);
        return ((AuthenticationDetails)user.getCredintials()).getAuthentication(sentence, times);
    }
}

