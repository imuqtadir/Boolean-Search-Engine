/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.buffalo.cse.irf14.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Imran Bijapuri
 */
public class Testclass {
    public static void main(String []args){
        Token t1 = new Token();
        Token t2 = new Token();
        Token t3 = new Token();
        t1.setTermText("destructor");
        t2.setTermText("is");
        t3.setTermText("^%%$$method()");
        
        
        TokenStream objTokenStream = new TokenStream();
        objTokenStream.add(t1);
        objTokenStream.add(t2);
        objTokenStream.add(t3);
        
        while(objTokenStream.hasNext()){
           Token current = objTokenStream.next();
           String currenttoken = current.getTermText();
           //System.out.println(currenttoken);
           Matcher m = Pattern.compile("(^*)(\\w+)(^*)").matcher(currenttoken);
           if (m.find()) {
               System.out.println(currenttoken.replace(m.group(), "   "));
               
           }
            
        }
        
    }
}
