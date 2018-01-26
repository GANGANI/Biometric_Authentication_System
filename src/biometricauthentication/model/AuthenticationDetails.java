/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication.model;

/**
 *
 * @author Gangani Chamika
 */
public class AuthenticationDetails implements UserDetails {
    double[][] averageTime;
    int[][] count;
    
    public AuthenticationDetails(){
        // first 26 simple letters and rest of the 26 capital letters and 53rd for space
        averageTime = new double[53][53];
        count = new int[53][53];
        
        // Initialize arrays with zeros
        for(int i=0; i<53; i++){
            for(int j=0; j<53; j++){
                count[i][j] = 0;
                averageTime[i][j] = 0;
            }
        }
    }
    
    public void updateDataSet(String sentence, double[] times){
        for(int i=1; i<sentence.length(); i++){
            int prev = (int) sentence.charAt(i-1);
            int current = (int) sentence.charAt(i);
            
            // Check capital or simple
            if (prev >= 97){
                prev = prev - 97 + 26;
            }else if(prev==32){
                prev = 52; 
            }
            else{
                prev -= 65;
            }
            
            if(current >= 97){
                current = current - 97 + 26;
            }else if(current==32){
                current = 52;
            }
            else{
                current -= 65;
            }

            averageTime[prev][current] = (averageTime[prev][current] * count[prev][current] + times[i-1]) / (count[prev][current] + 1);
            count[prev][current] ++;
        }
    }
    
    private double getOverallAverage(){
        
        double totalTime = 0;
        int totalCount = 0;
        
        for(int i=0; i < 53; i ++){
            for(int j=0 ; j < 53; j++){
                totalTime += averageTime[i][j];
                totalCount += count[i][j];
            }
        }
        
        return totalTime/totalCount;
    }
    
    public boolean getAuthentication(String sentence, double[] times){
        double variance = 0;
        double overallAverage = getOverallAverage();
        
        for(int i=1; i < sentence.length(); i ++){
            int prev = (int) sentence.charAt(i-1);
            int current = (int) sentence.charAt(i);
            
            // Check capital or simple
            if (prev >= 97){
                prev = prev - 97 + 26;
            }else if(prev == 32){
                prev = 52;
            }
            else{
                prev -= 65;
            }
            
            if(current >= 97){
                current = current - 97 + 26;
            }else if(current==32){
                current = 52;
            }
            else{
                current -= 65;
            }

            if (count[prev][current] == 0){
                variance += Math.pow(overallAverage - times[i-1], 2);
            }else{
                variance += Math.pow(averageTime[prev][current] - times[i], 2);
            }
            
        }
        
        variance = Math.sqrt(variance);
        
        System.out.println("Variance : "+variance+" max: "+(25 * sentence.length()));
        if (variance> Math.max(35 * sentence.length(), 700)){
            return false;
        }
        
        updateDataSet(sentence, times);
        return true;
        
    }
    
    @Override
    public boolean getAuthentication(UserDetails loginInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStats(UserDetails loginInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
