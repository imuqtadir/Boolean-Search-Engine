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
public class Title implements Analyzer {

    public TokenStream objTokenStream = new TokenStream();

    public Title(TokenStream stream) {
        this.objTokenStream = stream;
//        objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
    }

    public TokenStream process(TokenStream stream) {
        //System.out.println("In Title Process");
        try {
            //System.out.println("In Title Class");
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();

            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, stream);
            stream.reset();
            filterByType.increment();
            stream = filterByType.getStream();

//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.ACCENT, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            
//            stream = filterByType.getStream();
//            
//            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            
//            stream = filterByType.getStream();
//            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            
//            stream = filterByType.getStream();
//            
//            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SYMBOL, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            stream = filterByType.getStream();
//            
//            
//                        filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.NUMERIC, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            stream = filterByType.getStream();
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.DATE, stream);
//            stream.reset();
//            while (stream.hasNext()) {
//			filterByType.increment();
//		}
//            stream = filterByType.getStream();
//            
//            
//            for (int i = 0 ;i < stream.Tokenlist.size();i++){
//                System.out.println(" stream after filter "+stream.Tokenlist.get(i).getTermText());
//            }
            /*
            
            
            
             //stream = filterByType.process_Filter(stream);
            
            
            
            
             filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, stream);
             stream.reset();
             while (stream.hasNext()) {
             filterByType.increment();
             }
            
             stream = filterByType.getStream();
            
            
             filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, stream);
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
    public TokenStream getStream() {
        return this.objTokenStream;
    }

    @Override
    public boolean increment() throws TokenizerException {
        try {
            //System.out.println("In Title Class");
            TokenFilter filterByType;
            TokenFilterFactory objTokenFilterFactory = TokenFilterFactory.getInstance();
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             //System.out.println("This is size "+this.objTokenStream.Tokenlist.size());
             this.objTokenStream.reset();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SYMBOL, this.objTokenStream);
            filterByType.increment();
            this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.ACCENT, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            
            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.SPECIALCHARS, this.objTokenStream);
//            filterByType.increment();
//             this.objTokenStream =  filterByType.getStream();
//             this.objTokenStream.reset();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.NUMERIC, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            //System.out.println("This is size end"+this.objTokenStream.Tokenlist.size());
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.CAPITALIZATION, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             this.objTokenStream.reset();
            
            
            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STEMMER, this.objTokenStream);
            filterByType.increment();
             this.objTokenStream =  filterByType.getStream();
             
             this.objTokenStream.reset();
            
            
//            filterByType = objTokenFilterFactory.getFilterByType(TokenFilterType.STOPWORD, this.objTokenStream);
//            filterByType.increment();
//             this.objTokenStream =  filterByType.getStream();
//             this.objTokenStream.reset();
            
            
           
            
            
            
            
            
            
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
