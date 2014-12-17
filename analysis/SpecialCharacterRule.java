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
 * @author Amair
 */
public class SpecialCharacterRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();

    public SpecialCharacterRule() {
    }

    public SpecialCharacterRule(TokenStream stream) {
        this.objTokenStream = stream;
    }

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }

    @Override
    public boolean increment() throws TokenizerException {

        while (objTokenStream.hasNext()) {
        Token t = objTokenStream.next();
        
            String text = t.getTermText();

            
                System.out.println(text);
                
//Normal Punctuations present except hyphen, exclamation and full stop as they were handled in previous filters
            Matcher m = Pattern.compile("([_,/\\<\\>:;\"\\{\\}\\[\\]\\\\\\|\\+\\=\\(\\)\\*&\\^%\\$#@`~]+)").matcher(text);
            //Special case to handle hyphens present along with special characters between the letters
            Matcher m2 = Pattern.compile("([a-zA-Z]+)([-])([a-zA-Z]+)").matcher(text);
            //Regex for matching hyphen
            Matcher m3= Pattern.compile("\\-*").matcher(text);
            //If any special character is present
            if (m.find() || m2.find()) {
                //Handling hyphen
                if (m2.find()) {
                    text=  m3.replaceAll("");
                   // t.setTermText(text);
                    
                    objTokenStream.index--;
                    
                                        
                } 
                //Normal cases
                else {
                    text = m.replaceAll("");
                    text = text.replaceAll("[\\s]+", "");
                    text = text.replaceAll("^\\s", "");
                    text = text.trim();
                    objTokenStream.index--;
                }
                t.setTermText(text);

            }
            

           // return true;
        }
            this.objTokenStream.Tokenlist.trimToSize();
            return false;
        }

    

    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

