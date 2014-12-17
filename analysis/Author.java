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
public class Author implements Analyzer {
    
    public TokenStream objTokenStream = new TokenStream();
    
    
    public Author(TokenStream stream) {
        this.objTokenStream = stream;
//        objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
    }
    

        public TokenStream process(TokenStream stream) {
        
        try {
            //System.out.println("In Author Class");
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, this.objTokenStream);
            filterByType.increment();
            this.objTokenStream =  filterByType.getStream();
            this.objTokenStream.reset();
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }

    
    @Override
    public boolean increment() throws TokenizerException {
        TokenFilter filterByType;
        TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
     
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, this.objTokenStream);
            filterByType.increment();
            this.objTokenStream =  filterByType.getStream();
            this.objTokenStream.reset();
            
            
            
            
            
            return false;
        
    }


}
