/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

import java.text.Normalizer;

/**
 *
 * @author Amair
 */
public class AccentRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();

    public static void main(String[] args) {

    }

    public AccentRule() {

    }

    public AccentRule(TokenStream stream) {

        this.objTokenStream = stream;
//        objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
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
            text = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            t.setTermText(text);
        } 
            return false;
        

    }

    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
