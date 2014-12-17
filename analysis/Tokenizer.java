/**
 *
 */
package edu.buffalo.cse.irf14.analysis;

import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.ParserException;
import edu.buffalo.cse.irf14.index.IndexWriter;
/**
 * @author nikhillo Class that converts a given string into a
 * {@link TokenStream} instance
 */
public class Tokenizer {

    String delimiter;
    
    /**
     * Default constructor. Assumes tokens are whitespace delimited
     */
    public Tokenizer() {
        //TODO : YOU MUST IMPLEMENT THIS METHOD
        delimiter = " ";
    }

    /**
     * Overloaded constructor. Creates the tokenizer with the given delimiter
     *
     * @param delim : The delimiter to be used
     */
    public Tokenizer(String delim) {
        //TODO : YOU MUST IMPLEMENT THIS METHOD
        delimiter = delim;
    }

    /**
     * Method to convert the given string into a TokenStream instance. This must
     * only break it into tokens and initialize the stream. No other processing
     * must be performed. Also the number of tokens would be determined by the
     * string and the delimiter. So if the string were "hello world" with a
     * whitespace delimited tokenizer, you would get two tokens in the stream.
     * But for the same text used with lets say "~" as a delimiter would return
     * just one token in the stream.
     *
     * @param str : The string to be consumed
     * @return : The converted TokenStream as defined above
     * @throws TokenizerException : In case any exception occurs during
     * tokenization
     */
    public TokenStream consume(String str) throws TokenizerException {
        
        TokenStream objtokenstream = new TokenStream();
//System.out.println("sa "+str);
    
    if(str == null){
      //  System.out.println("We are throwing an exceeeeeeeeeeeeeeeeeeeeeee");
        throw new TokenizerException("Error In TokenStream");
    }else{
    
        //Declaring variables 
        String[] tokenstrarr;
        Document d = null;
        
        int i = 0;
        String termBuffer = "";
        Token objToken = null;
        
        

        tokenstrarr = str.split(delimiter);
        if (tokenstrarr.length > 0) {
            for (i = 0; i < tokenstrarr.length; i++) {
                //System.out.println("tokenstrarr    " + tokenstrarr[i]);
                objToken = new Token();
                objToken.setTermText(tokenstrarr[i]);
                termBuffer = "T";
                objToken.setTermBuffer(termBuffer.toCharArray());
                objToken.setDocid(Integer.toString(IndexWriter.docid));
                objtokenstream.add(objToken);
                
            }
        }
     //   System.out.prin`tln("this is Tokenlist_processed size " +objtokenstream.Tokenlist.size());
        
//        Analyzer analyzerForField = AnalyzerFactory.getInstance()
//                .getAnalyzerForField(FieldNames.TITLE, objtokenstream);
//        
//        analyzerForField.process(objtokenstream);
//        objtokenstream.append(objtokenstream);
//        
        //System.out.println("this is Tokenlist_processed size " +objtokenstream.Tokenlist_processed.size());
        
        
     
        
       
    }
        //TODO : YOU MUST IMPLEMENT THIS METHOD
        return objtokenstream;
    }
}
