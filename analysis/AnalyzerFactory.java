/**
 *
 */
package edu.buffalo.cse.irf14.analysis;

import edu.buffalo.cse.irf14.document.FieldNames;

/**
 * @author nikhillo This factory class is responsible for instantiating
 * "chained" {@link Analyzer} instances
 */
public class AnalyzerFactory {

    private static AnalyzerFactory objAnalyzerFactory = null;

    /**
     * Static method to return an instance of the factory class. Usually factory
     * classes are defined as singletons, i.e. only one instance of the class
     * exists at any instance. This is usually achieved by defining a private
     * static instance that is initialized by the "private" constructor. On the
     * method being called, you return the static instance. This allows you to
     * reuse expensive objects that you may create during instantiation
     *
     * @return An instance of the factory
     */
    public static AnalyzerFactory getInstance() {
        objAnalyzerFactory = new AnalyzerFactory();
        //TODO : YOU MUST IMPLEMENT THIS METHOD
        return objAnalyzerFactory;
		//TODO: YOU NEED TO IMPLEMENT THIS METHOD

    }

    /**
     * Returns a fully constructed and chained {@link Analyzer} instance for a
     * given {@link FieldNames} field Note again that the singleton factory
     * instance allows you to reuse {@link TokenFilter} instances if need be
     *
     * @param name: The {@link FieldNames} for which the {@link Analyzer} is
     * requested
     * @param TokenStream : Stream for which the Analyzer is requested
     * @return The built {@link Analyzer} instance for an indexable
     * {@link FieldNames} null otherwise
     */
    public Analyzer getAnalyzerForField(FieldNames name, TokenStream stream) {

        //System.out.println(name);
        String str = name.toString();
        
            Analyzer objAnalyzer = null;
        if (str.equals("TITLE")) {
            objAnalyzer = new Title(stream);
            return objAnalyzer;
        } 
        
        else if(str.equals("NEWSDATE")){
            objAnalyzer = new Newsdate();
            return objAnalyzer;
        }
        else if(str.equals("CONTENT")){
            objAnalyzer = new Content(stream);
            return objAnalyzer;
        }else if(str.equals("AUTHOR")){
            objAnalyzer = new Author(stream);
            return objAnalyzer;
        }
        
        else {
            //objAnalyzer = new Title();
            return null;
        }

		//TODO : YOU NEED TO IMPLEMENT THIS METHOD
    }
}
