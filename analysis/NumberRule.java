/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

import static edu.buffalo.cse.irf14.analysis.DateRule.getnoofdigits;
import static edu.buffalo.cse.irf14.analysis.DateRule.isIntegerandbetween1899and2015;
import static edu.buffalo.cse.irf14.analysis.DateRule.isIntegerandbetween1and31;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Imran Bijapuri
 */
public class NumberRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();

    public NumberRule() {
    }

    public NumberRule(TokenStream stream) {
        this.objTokenStream = stream;
//objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
    }

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }

    @Override
    public boolean increment() throws TokenizerException {

        while (objTokenStream.hasNext()) {
            //System.out.println("In number rule");
            Token t = objTokenStream.next();
            String text = t.getTermText();

            Matcher m1 = Pattern.compile("\\d*\\.*,*-*[\\d*]\\.*,*-*\\d*").matcher(text);

            if (m1.find()) {
                if (text.contains("%")) {
                    t.setTermText("%");
                } else if (text.contains("/")) {
                    t.setTermText("/");
                } else {
                    objTokenStream.remove();
                }
               // System.out.println("Removing "+text);
            }

            
        }

        this.objTokenStream.Tokenlist.trimToSize();
        return false;

    }

    public static boolean isInteger(String s) {
        try {
            int x = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
// only got here if we didn't return false
        return true;
    }

    public static boolean isFloat(String s) {
        try {
            float x = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
// only got here if we didn't return false
        return true;
    }

    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
