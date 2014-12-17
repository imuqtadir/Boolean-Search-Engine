/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.buffalo.cse.irf14.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Imran Bijapuri
 */
//Any punctuation marks that possibly mark the end of a sentence (. ! ?) should be removed. Obviously if the symbol appears within a token it should be retained (a.out for example).
//Any possessive apostrophes should be removed (‘s s’ or just ‘ at the end of a word). Common contractions should be replaced with expanded forms but treated as one token. (e.g. should’ve => should have). All other apostrophes should be removed.
//If a hyphen occurs within a alphanumeric token it should be retained (B-52, at least one of the two constituents must have a number). If both are alphabetic, it should be replaced with a whitespace and retained as a single token (week-day => week day). Any other hyphens padded by spaces on either or both sides should be removed.
public class SymbolRule extends TokenFilter {

    public TokenStream objTokenStream = new TokenStream();

    public SymbolRule() {
    }

    public SymbolRule(TokenStream stream) {
        this.objTokenStream = stream;
//        objTokenStream.itr =  this.objTokenStream.Tokenlist.listIterator();
    }

    public static void main(String[] args) {

    }

    @Override
    public TokenStream getStream() {
        return this.objTokenStream;
    }

  @Override
    public boolean increment() throws TokenizerException {

        while (objTokenStream.hasNext()) {
            String text = "";
            boolean status = false;
            //System.out.println("sizeeeeeeeeeeeeeeee"+objTokenStream.Tokenlist.size());

            Token t = objTokenStream.next();
            text = t.getTermText();
            //System.out.println("In while " + text);

            Matcher m = Pattern.compile("([\\!\\?\\.])([\\s]+)").matcher(text);

            if (m.find()) {
                text = m.replaceFirst(m.group(2));
                //status = true;
                //t.setTermText(text);
            }

            m = Pattern.compile("([\\!\\?\\.])$").matcher(text);

            if (m.find()) {
                text = m.replaceFirst("");
                status = true;
                //System.out.println("End of sentence patten matched : "+text);
                t.setTermText(text);
                objTokenStream.index--;
                continue;
                
            }
            
            m = Pattern.compile("([w]+)([\\!\\?\\.])([//s]+)").matcher(text);
            
            if(m.find()){
                text= text.replaceAll("\\.", "");
            t.setTermText(text);
            status=true;
             objTokenStream.index--;
              continue;
            }
            if (status == true) {
                objTokenStream.index--;
               continue;
            }

            /* Removing this for check  
             if ((text.matches("(\\w+)([\\$&+,:;=?@#|'<>.-^*()%!]*)(\\w*)([\\!]+)"))
             || (text.matches("(\\w+)([\\$&+,:;=?@#|'<>.-^*()%!]*)(\\w*)([\\.]+)"))
             || (text.matches("(\\w+)([\\$&+,:;=?@#|'<>.-^*()%!]*)(\\w*)([\\?]+)"))) {  
             text = text.substring(0, text.length() - 1);
             t.setTermText(text);
             status = true;
             //                if (tokenstream.index > 0) {
             //                    tokenstream.index--;
             //                }
             objTokenStream.index = objTokenStream.index--;

             }
        
        
        
             //Checks if the string ends with exclamation or fullstop or question mark
             Matcher m = Pattern.compile("([\\a-zA-Z+])([\\?]$*)([!]$*)([\\.]$*)").matcher(text);
             Matcher m1 = Pattern.compile(".").matcher(text);
             Matcher m2 = Pattern.compile("!").matcher(text);
        
             if(m.find()){
             if(text.contains("?")){
             text=text.replace("?","");
             //text.replaceAll("?", "");
             }
             else if(text.contains(".")){
             text = text.replace(m.group(3), "");
             }
             else if(text.contains("!")){
             text = text.replace("!", "");
             }
             }
        
             */
            //System.out.println("macthed  " + text);
          //  t.setTermText(text);

//All the letters such as I've are changed to I have and all the apostrophes are removed
            if (text.startsWith("'")) {

                text = text.replaceFirst("'", "");
                t.setTermText(text);
            }
            text=t.getTermText();
            if ((text.contains("'"))) {
                text = text.replace("I've", "I have");
                text = text.replace("isn't", "is not");
                text = text.replace("don't", "do not");
                text = text.replace("shan't", "shall not");

                text = text.replace("we're", "we are");
                text = text.replace("they're", "they are");

                text = text.replace("Should've", "Should have");
                text = text.replace("They'd", "They would");
                text = text.replace("She'll", "She will");
                text = text.replace("'em", " them");

                text = text.replace("won't", "will not");
                text = text.replace("can't", "can not");
                text = text.replace("did'nt", "did not");

                text = text.replace("I'm", "I am");
                text = text.replace("'s", "");
                text = text.replace("'", "");
                //text = text.replace("I've", "I fsn fmsdmfsmf have");
                //text = text.replaceAll("I've", "I fsn fmsdmfsmf have");
                //System.out.println("Output text 1 " + text);
                t.setTermText(text);
                //status = true;
            }
            if (text.contains("'")) {
                text = text.replaceAll("'", "");
                t.setTermText(text);

            }
            //All the hyphens are removed within letters
            if (text.matches("([a-zA-Z]+)(\\-)([a-zA-Z]+)")) {
                text = text.replaceAll("-", " ");
                t.setTermText(text);
                status = true;
            }

            if ((text.matches("--") || text.matches("-"))) {

                objTokenStream.remove();
            }
//            Matcher m1 = Pattern.compile("([-]+)([a-zA-Z]+)").matcher(text);
//            Matcher m2 = Pattern.compile("([a-zA-Z]+)([-]+)").matcher(text);

            if (text.matches("([-]+)([a-zA-Z]+)") || text.matches("([a-zA-Z]+)([-]+)")) {
                text = text.replaceAll("-", "");
                text = text.replaceAll("[(s)+]", " ");
                t.setTermText(text);
            }

//            
//            if(text.matches("([a-zA-Z]+)(\\-)([0-9]+)"))
//            {
//            status=true;
//            }
//            
//             if(text.matches("([0-9]+)(\\-)([a-zA-Z]+)"))
//            {
//            status=true;
//            }
//            
//             
//              if(text.matches("([0-9]+)(\\-)([0-9]+)"))
//            {
//            status=true;
//            }
////             
//            Matcher hyphens = Pattern.compile("([-]+)([-]+)").matcher(text);
//            if (hyphens.find()) {
//                //System.out.println("Remove this token" + text);
//                text = hyphens.replaceAll("");
//                t.setTermText(text);
//                if("".equals(t.getTermText())){
//                objTokenStream.remove();
//                }
//                objTokenStream.index=objTokenStream.index--;
//                status = true;
//            }
            if ("".equals(t.getTermText())) {
                objTokenStream.remove();
            }

           // System.out.println("objTokenStream.getnthToken(1) "+ objTokenStream.getnthToken(1));
            /* if(objTokenStream.getnthToken(1).contains("-") || objTokenStream.getnthToken(1).contains("--")){
             objTokenStream.removeNtokens(1);
             //  System.out.println("removed" + text);
             }
             */
            // System.out.println("Output text " + text);
            //  return true;
        }
        return false;

        //System.out.println("text " +text);
        // objTokenStream.index++;
    }
    @Override
    public TokenStream process(TokenStream objtokenstream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
