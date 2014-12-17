/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.Hashtable;

/**
 *
 * @author Imran Bijapuri
 */
public class DateRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();
    
    
    public static void main(String [] args){
        String text = "December 7, 1941,";
        if(text.matches("^((31(?!\\ (Feb(ruary)?|Apr(il)?|June?|(Sep(?=\\b|t)t?|Nov)(ember)?)))|((30|29)(?!\\ Feb(ruary)?))|(29(?=\\ Feb(ruary)?\\ (((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)))))|(0?[1-9])|1\\d|2[0-8])\\ (Jan(uary)?|Feb(ruary)?|Ma(r(ch)?|y)|Apr(il)?|Ju((ly?)|(ne?))|Aug(ust)?|Oct(ober)?|(Sep(?=\\b|t)t?|Nov|Dec)(ember)?)\\ ((1[6-9]|[2-9]\\d)\\d{2})$")){
           System.out.println("Hey we got a hit");
        }
        else if(text.matches("\\b(?:Sun|Sunday|Mon|Monday|Tue|Tuesday|Wed|Wednesday|Thu|Thursday|Fri|Friday|Sat|Saturday)ece(?:1[0-2]|[1-9])ber\\\\h+7,\\\\h+1941,\\\\z")){
            System.out.println("Hey we got 2nd hit");
        }
    }

    public DateRule() {
    }

    public DateRule(TokenStream stream) {
        this.objTokenStream = stream;
    }
    
    public static boolean isIntegerandbetween1and31(String s) {
        try {
            System.out.println("isIntegerandbetween1and31 input string" + s);
            int x = Integer.parseInt(s);
            if (x > 0 && x < 32) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false

    }

    public static boolean isIntegerandbetween1899and2015(String s) {
        try {
            System.out.println("We are in isIntegerandbetween1899and2015 for " + s);
            int x = Integer.parseInt(s);
            if (x > 1899 && x < 2015) {
                //System.out.println("returning true for " + s);
                return true;
            } else {
                //System.out.println("returning false for " + s);
                return false;
            }
        } catch (NumberFormatException e) {
            //System.out.println("returning false for " + s);
            return false;
        }
        // only got here if we didn't return false

    }
    
    public static String getnoofdigits(String str){
        int length = String.valueOf(str).length();
        //System.out.print(" length is "+length);
        if(length == 1){
            return "0" + str;
        }else if(length == 2){
            return str;
        }else{
            return str;
        }
    }

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }
    
    @Override
    public boolean increment() throws TokenizerException {

        Token t = objTokenStream.next();
        if (t != null) {
        String text = t.getTermText().trim().toLowerCase();
        String nexttoken = objTokenStream.getnthToken(1).toLowerCase().trim();
        String next2token = objTokenStream.getnthToken(2).toLowerCase().trim();
        Hashtable table = new Hashtable();
        System.out.println(text + "\t" + nexttoken   + "\t" + next2token );
        table.put("january", "01");
        table.put("february", "02");
        table.put("march", "03");
        table.put("april", "04");
        table.put("may", "05");
        table.put("june", "06");
        table.put("july", "07");
        table.put("august", "08");
        table.put("september", "09");
        table.put("october", "10");
        table.put("november", "11");
        table.put("december", "12");

        table.put("jan", "01");
        table.put("feb", "02");
        table.put("mar", "03");
        table.put("apr", "04");
        table.put("may", "05");
        table.put("jun", "06");
        table.put("jul", "07");
        table.put("aug", "08");
        table.put("sep", "09");
        table.put("oct", "10");
        table.put("nov", "11");
        table.put("dec", "12");

        //System.out.println(" nexttoken.substring(0, 3) " + nexttoken.substring(0, nexttoken.length()-1) );
        String nexttoken_substr = nexttoken.substring(0, nexttoken.length()-1);
        String next2token_substr = next2token.substring(0, next2token.length()-1);
        
        
        if(isIntegerandbetween1and31(text) && table.containsKey(nexttoken) && isIntegerandbetween1899and2015(next2token)){
          text = next2token + table.get(nexttoken) + getnoofdigits(text) ;
          objTokenStream.removeNtokens(2);
          t.setTermText(text);
       }
        else if(table.containsKey(text) && isIntegerandbetween1and31(nexttoken.substring(0, nexttoken.length()-1)) && isIntegerandbetween1899and2015(next2token.substring(0, next2token.length()-1))){
          text = next2token.substring(0, next2token.length()-1) + table.get(text) + getnoofdigits(nexttoken.substring(0, nexttoken.length()-1)) + ",";
          objTokenStream.removeNtokens(2);
          System.out.println("We have a hit");
          t.setTermText(text);
       }
        
        
        
        return true;
        }else {
            return false;
        }
    }

    public boolean checkcontainskey(String s,Hashtable hashmap){
        try{
            // System.out.println("checkcontainskey  incoming "+s);
            if(hashmap.containsKey(s.toLowerCase())){
            System.out.println("true for " +s);
            return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("catch for " +s);
            return false;
        }
    }

    

    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
