/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

/**
 *
 * @author Amair
 */
public class CapitalizationRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();

    public CapitalizationRule() {
    }

    public CapitalizationRule(TokenStream stream) {
        this.objTokenStream = stream;
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
            String nexttokentext = objTokenStream.getnextToken();
            String previoustokentext = objTokenStream.getpreviousToken();
            String NewAddition = " ";
//Camel Case Letter Checking
           
            if(text == null || nexttokentext == null){
            }
            else if ((text.matches("([A-Z])([a-z]+)"))
                    && (nexttokentext.matches("([A-Z])([a-z]+)"))) {

                //Wont check previous token
                if (objTokenStream.getcurrentindex() == 0) {
                                 
                       //Not working for San Fransisco             
                    text = text + NewAddition + nexttokentext;
                    
                    t.setTermText(text);
                    //Check Remove
                    objTokenStream.removeNtokens(1);
                    
                    
                    //System.out.println("The text is" + text);
                } else if (!previoustokentext.contains(".")) {

                    text = text + NewAddition + nexttokentext;
                    //text = text.toLowerCase();
                    t.setTermText(text);
                    //System.out.println("The text is" + text);
                      objTokenStream.removeNtokens(1);
//MERGE

                    // }
                } 
               
            }
            //Checking for single token capitalization
            else if (text.matches("(([A-Z])([a-z]+))")) {
                    text = text.toLowerCase();
                    t.setTermText(text);
                }
             //return true;
        } 
            this.objTokenStream.Tokenlist.trimToSize();
            return false;
        
    }

    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
