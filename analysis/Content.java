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
public class Content implements Analyzer{
    
    public TokenStream objTokenStream = new TokenStream();
    
    
    public Content(TokenStream stream) {
        this.objTokenStream = stream;
//        objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
    }
    
    @Override
    public TokenStream process(TokenStream stream) {
        //System.out.println("In Title Process");
        try {
            //System.out.println("In Title Class");
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, stream);
            stream.reset();
            while (stream.hasNext()) {
			filterByType.increment();
		}
            
            stream = filterByType.getStream();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.ACCENT, stream);
            stream.reset();
            filterByType.increment();
            stream = filterByType.getStream();
            
            
            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            
//            stream = filterByType.getStream();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, stream);
            stream.reset();
            filterByType.increment();
            stream = filterByType.getStream();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SYMBOL, stream);
            stream.reset();
            filterByType.increment();
	    stream = filterByType.getStream();
            
                        
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.NUMERIC, stream);
            stream.reset();
            filterByType.increment();
            stream = filterByType.getStream();
            
            
            
            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.DATE, stream);
//            stream.reset();
//            filterByType.increment();
//            stream = filterByType.getStream();
//            
//            

            
            
            
            
//            for (int i = 0 ;i < stream.Tokenlist.size();i++){
//                System.out.println(" stream after filter "+stream.Tokenlist.get(i).getTermText());
//            }
            /*
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SYMBOL, stream);
            stream.reset();
            while (stream.hasNext()) {
			filterByType.increment();
		}
            stream = filterByType.getStream();
            
            
            //stream = filterByType.process_Filter(stream);
            
            
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, stream);
            stream.reset();
            while (stream.hasNext()) {
			filterByType.increment();
		}
            
            stream = filterByType.getStream();
            
            
            
            
//            filterByType.process_Filter(stream);
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean increment() throws TokenizerException {
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, this.objTokenStream);
            filterByType.increment();
            this.objTokenStream =  filterByType.getStream();
            this.objTokenStream.reset();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SYMBOL, this.objTokenStream);
            filterByType.increment();
            this.objTokenStream =  filterByType.getStream();
            this.objTokenStream.reset();
//            
//            
            //System.out.println("Filter1" +this.objTokenStream.Tokenlist.size());
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.ACCENT, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
//            
        //    System.out.println("Filter2" +this.objTokenStream.Tokenlist.size());
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, this.objTokenStream);
//            filterByType.increment();
//             this.objTokenStream =  filterByType.getStream();
//             this.objTokenStream.reset();
//            
       //     System.out.println("Filter3" +this.objTokenStream.Tokenlist.size());
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.NUMERIC, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
//            
           // System.out.println("Filter4" +this.objTokenStream.Tokenlist.size());
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
//            
            //System.out.println("Filter5" +this.objTokenStream.Tokenlist.size());
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STEMMER, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            
         //   System.out.println("Filter6" +this.objTokenStream.Tokenlist.size());
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            
          //  System.out.println("Filter7 " +this.objTokenStream.Tokenlist.size());
          //  System.out.println("\n\n\n\n\n");
            
            return false;
    }

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }
}
