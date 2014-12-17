/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;

/**
 *
 * @author Imran Bijapuri
 */
public class TokenStream_Term extends TokenStream {

    public static int index = 0;
    public ArrayList<Token> Tokenlist = new ArrayList<Token>();
    public static ArrayList<Token> Tokenlist_processed = new ArrayList<Token>();
    
    
    
    public void add(Token token) {
        Tokenlist.add(token);
    }

}
