/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.buffalo.cse.irf14.analysis;

/**
 *
 * @author Imran Bijapuri
 */
public class Newsdate implements Analyzer{
    @Override
    public TokenStream process(TokenStream stream) {
        //System.out.println("In Title Process");
        try {
            //System.out.println("In Title Class");
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.DATE, stream);
            stream.reset();
            while (stream.hasNext()) {
			filterByType.increment();
		}
            stream = filterByType.getStream();
            
            


        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean increment() throws TokenizerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TokenStream getStream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
