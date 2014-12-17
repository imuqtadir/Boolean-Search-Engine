/**
 *
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author nikhillo Class that represents a stream of Tokens. All
 * {@link Analyzer} and {@link TokenFilter} instances operate on this to
 * implement their behavior
 */
public class TokenStream implements Iterator<Token> {

    public int index = -1;
    public ArrayList<Token> Tokenlist = new ArrayList<Token>();
   
    
    
    
    public static int removed = 0;

    public void add(Token token) {
        Tokenlist.add(token);
    }

    
    public void Emptytokenlist(){
        this.Tokenlist.clear();
    }
    /**
     * Method that checks if there is any Token left in the stream with regards
     * to the current pointer. DOES NOT ADVANCE THE POINTER
     *
     * @return true if at least one Token exists, false otherwise
     */
    @Override
    public boolean hasNext() {
        try {
            if (this != null) {
                return this.index + 1 < this.Tokenlist.size();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to return the next Token in the stream. If a previous hasNext()
     * call returned true, this method must return a non-null Token. If for any
     * reason, it is called at the end of the stream, when all tokens have
     * already been iterated, return null
     */
    @Override
    public Token next() {
        try {
            
            
            if((this.index+1) >= this.Tokenlist.size()){
             return null;  
            }
            else{
            this.index++;
            Token t = this.Tokenlist.get(this.index);
            return t;
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to remove the current Token from the stream. Note that "current"
     * token refers to the Token just returned by the next method. Must thus be
     * NO-OP when at the beginning of the stream or at the end
     */
    @Override
    public void remove() {
        if (this.index >= 0) {
            this.Tokenlist.remove(this.index);
            //this.Tokenlist.trimToSize();
            //trial
            this.index--;
            //System.out.println("Deceremented val " +this.index);
        }else {
          //  System.out.println("dadasda " +this.index);
           // this.index--;
        }
        
                int i = 0;
//        for (i = this.index + count ; i > this.index; i--) {
//          //  System.out.println("removing    " + this.Tokenlist.get(i).getTermText());
//this.Tokenlist.remove(i);
//        }
    }

    /**
     * Method to reset the stream to bring the iterator back to the beginning of
     * the stream. Unless the stream has no tokens, hasNext() after calling
     * reset() must always return true.
     */
    public void reset() {
        this.index = -1;
        //TODO : YOU MUST IMPLEMENT THIS
    }

    /**
     * Method to append the given TokenStream to the end of the current stream
     * The append must always occur at the end irrespective of where the
     * iterator currently stands. After appending, the iterator position must be
     * unchanged Of course this means if the iterator was at the end of the
     * stream and a new stream was appended, the iterator hasn't moved but that
     * is no longer the end of the stream.
     *
     * @param stream : The stream to be appended
     */
    public void append(TokenStream stream) {
        if (stream == null) {

        } else {
            this.Tokenlist.addAll(stream.Tokenlist);
        }
        //TODO : YOU MUST IMPLEMENT THIS
    }
    
    
    
    
    
   
    /**
     * Method to get the current Token from the stream without iteration. The
     * only difference between this method and {@link TokenStream#next()} is
     * that the latter moves the stream forward, this one does not. Calling this
     * method multiple times would not alter the return value of
     * {@link TokenStream#hasNext()}
     *
     * @return The current {@link Token} if one exists, null if end of stream
     * has been reached or the current Token was removed
     */
    public Token getCurrent() {
       //System.out.println(" We are returning null " + this.index);
        if (this.index == -1 || this.Tokenlist.size() == 0 || this.index >= this.Tokenlist.size()) {
       
            return null;
        }
        
        else {
            return this.Tokenlist.get(this.index);
        }

    }

    // TODO YOU MUST IMPLEMENT THIS
// TODO YOU MUST IMPLEMENT THIS    
//            index++;
//            return Tokenlist.size() < index;
//        
//return false;
    public String getnextToken() {
        if (getStreamsize() > (this.index + 1)) {
            String text = this.Tokenlist.get(this.index + 1).getTermText();
            return text;
        } else {
            return null;
        }
    }

    public String getnthToken(int id) {
        //System.out.println("Current index " + this.index);
        //System.out.println("Streamsize " + getStreamsize());
        if((this.index + id) < getStreamsize() ){
            //System.out.println("We are tryong to acess " + (this.index + id));
            Token t = this.Tokenlist.get((this.index + id));
            //System.out.println("getTermText " + t.getTermText());
            return t.getTermText();
        }else{
            return "null";
        }
        //    return null;
        

    }

    public String getpreviousToken() {
        if(this.index > 0){
        String text = this.Tokenlist.get(this.index - 1).getTermText();
        return text;
        }else{
            return "";
        }
    }

    public int getStreamsize() {
        return this.Tokenlist.size();
    }

    public void removeNtokens(int count) {
        int i = 0;
        
        
        for (i = this.index + count ; i > this.index; i--) {
           // System.out.println("removing    " + this.Tokenlist.get(i).getTermText());
            this.Tokenlist.remove(i);
        }
    }

    public int getcurrentindex() {
        return this.index;
    }

}
