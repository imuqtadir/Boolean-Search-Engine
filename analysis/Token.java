/**
 *
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author nikhillo This class represents the smallest indexable unit of text.
 * At the very least it is backed by a string representation that can be
 * interchangeably used with the backing char array.
 *
 * You are encouraged to add more data structures and fields as you deem fit.
 */
public class Token {

    //The backing string representation -- can contain extraneous information
    private String termText;
    //The char array backing termText
    private char[] termBuffer;
    //The fileid refers to the original file name eg: 0000005
    private String fileid;
    //The category refers to the original folder to which the file belonged eg : acq
    private String category;
    
    private boolean isdate;
    
    private String docid;
    
    
    protected void setIsdate(boolean isdate1) {
        isdate = isdate1;
        //termBuffer = (termText != null) ? termText.toCharArray() : null;
    }

    protected boolean getIsdate() {
        return isdate;
    }


    /**
     * Method to set the termText to given text. This is a sample implementation
     * and you CAN change this to suit your class definition and data structure
     * needs.
     *
     * @param text
     */
    protected void setTermText(String text) {
        termText = text;
        //termBuffer = (termText != null) ? termText.toCharArray() : null;
    }

    /**
     * Getter for termText This is a sample implementation and you CAN change
     * this to suit your class definition and data structure needs.
     *
     * @return the underlying termText
     */
    protected String getTermText() {
        return termText;
    }

    /**
     * Method to set the termBuffer to the given buffer. This is a sample
     * implementation and you CAN change this to suit your class definition and
     * data structure needs.
     *
     * @param buffer: The buffer to be set
     */
    protected void setTermBuffer(char[] buffer) {
        termBuffer = buffer;
        // termText = new String(buffer);
    }

    /**
     * Getter for the field termBuffer
     *
     * @return The termBuffer
     */
    protected char[] getTermBuffer() {
        return termBuffer;
    }

    /**
     * @return the fileid
     */
    protected String getFileid() {
        return fileid;
    }

    /**
     * @param fileid the fileid to set
     */
    protected void setFileid(String fileid) {
        this.fileid = fileid;
    }

    /**
     * @return the category
     */
    protected String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    protected void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method to merge this token with the given array of Tokens You are free to
     * update termText and termBuffer as you please based upon your Token
     * implementation. But the toString() method below must return whitespace
     * separated value for all tokens combined Also the token order must be
     * maintained.
     *
     * @param tokens The token array to be merged
     */
protected void merge(Token... tokens) {
        try {
            //System.out.println("I am in merge");
            String tokenstr = this.termText;

            for (Token token : tokens) {
                //System.out.println("Token for loop    "+ token.termText);
                tokenstr = tokenstr + " " + token.termText;
            }
            this.termText = tokenstr;
            //System.out.println("tokenstr fin val    "+ tokenstr);
        } catch (Exception e) {
            //System.out.println(" localized " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        //TODO : YOU MUST IMPLEMENT THIS METHOD
    }




//    protected void merge(Token... tokens) {
//        try {
//            System.out.println("I am in merge");
//            String tokenstr = this.termText;
//
//            for (Token token : tokens) {
//                tokenstr = tokenstr + "#&56#$^" + token.termText;
//            }
//            tokenstr = tokenstr + "#&56#$^" + this.termText;
//            this.termText = tokenstr;
//            System.out.println("I am in merge    "+ this.termText);
//        } catch (Exception e) {
//            //System.out.println(" localized " + e.getLocalizedMessage());
//            e.printStackTrace();
//        }
//        //TODO : YOU MUST IMPLEMENT THIS METHOD
//    }

    /**
     * Returns the string representation of this token. It must adhere to the
     * following rules: 1. Return only the associated "text" with the token. Any
     * other information must be suppressed. 2. Must return a non-empty value
     * only for tokens that are going to be indexed If you introduce special
     * token types (sentence boundary for example), return an empty string 3. IF
     * the original token A (with string as "a") was merged with tokens B ("b"),
     * C ("c") and D ("d"), toString should return "a b c d"
     *
     * @return The raw string representation of the token
     */
    @Override
    public String toString() {
        String str = "";
        String strarr[];
        int i = 0;
        //System.out.println("pppppppppppppppppppppppppppp " +this.termText.contains("&^"));
        strarr = this.termText.split(" ");
        //System.out.println("strarr[0]" +strarr[0]);
//        System.out.println("strarr[1]" +strarr[1]);
        for(i=0;i<strarr.length;i++){
            str = str + " " +strarr[i];
        }
//        System.out.println("value of string " +str);
//        System.out.println("value of string " +str.trim());
        //TODO: YOU MUST IMPLEMENT THIS METHOD
        return str.trim();
    }

    /**
     * @return the docid
     */
    public String isDocid() {
        return docid;
    }

    /**
     * @param docid the docid to set
     */
    public void setDocid(String docid) {
        this.docid = docid;
    }

    
    
    
}
