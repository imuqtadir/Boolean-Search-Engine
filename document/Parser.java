/**
 *
 */
package edu.buffalo.cse.irf14.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author nikhillo Class that parses a given file into a Document
 */
public class Parser {

    /**
     * Static method to parse the given file into the Document object
     *
     * @param filename : The fully qualified filename to be parsed
     * @return The parsed and fully loaded Document object
     * @throws ParserException In case any error occurs during parsing
     */
    public static Document parse(String filename) throws ParserException {
		// TODO Auto-generated method stub

        //File f = new File(filename);
        if (filename == null) {
            //System.out.println("We are throwing an exceeeeeeeeeeeeeeeeeeeeeee");
            throw new ParserException();
        } else if (!new File(filename).exists()) {
            //System.out.println("We are throwing an iiiiiiiiiiiii " + filename);
            throw new ParserException();

        } else {
            //Object and variable declarations
            Document d = new Document();
            StringBuilder builder = new StringBuilder();
            String temp = "";
            String[] tempArr = new String[3];
            String title = "";
            String author_org = "";
            String author = "";
            String org = "";
            String place = "";
            String date = "";
            String content = "";
            String remaining_string = "";
            String file = "";
            String category = "";
            String trimmedfilename = "";
            String place_date = "";

            //Extract filename and category
            try {
                file = filename.substring(filename.lastIndexOf(File.separatorChar) + 1, filename.length());
                trimmedfilename = filename.substring(0, filename.lastIndexOf(File.separatorChar));
                category = trimmedfilename.substring(trimmedfilename.lastIndexOf(File.separatorChar) + 1, trimmedfilename.length());
            } catch (Exception e) {
            }
        //System.out.println(category);
            //System.out.println(filename);

            try {
                // open for reading purpose.
                BufferedReader br = new BufferedReader(new FileReader(filename));
                while ((temp = br.readLine()) != null) {
                    builder.append(temp).append("\n");
                }

            } catch (Exception e) {
                //System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }

            remaining_string = builder.toString();
            remaining_string = remaining_string.trim();
        ////System.out.println(remaining_string);

            try {
                tempArr = remaining_string.split("\n", 2);
                title = tempArr[0];
                remaining_string = tempArr[1];
            } catch (Exception ex) {
                title =null;
                ex.printStackTrace();
            }
            remaining_string = remaining_string.trim();
        //System.out.println("Title" + "\t" + title);

            //Extracting the Author
            if (remaining_string.contains("<AUTHOR>")) {
                try {
                    tempArr = remaining_string.split("<\\S{0,1}AUTHOR>");
                    author_org = tempArr[1];
                    remaining_string = tempArr[2];
                    author_org = author_org.replaceAll("(?i)BY", "").trim();
                //System.out.println("author_org" + "\t" + author_org);
                    ////System.out.println(remaining_string.substring(1, 100));

                    if (author_org.indexOf(",") != -1) {

                        tempArr = author_org.split(",", 2);
                        author = tempArr[0].trim();
                        org = tempArr[1].trim();
                    //System.out.println("Author" + "\t" + author);
                        //System.out.println("Company" + "\t" + org);

                    } else {

                        author = author_org.trim();
                        org = null;
                    //System.out.println(author);
                        //System.out.println("Company name unavailable");

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                author = null;
                org = null;
            //System.out.println("Author" + "\t" + " name unavailable");
                //System.out.println("Company" + "\t" + " name unavailable");
            }

            try {
                tempArr = remaining_string.split("-", 2);
                place_date = tempArr[0];
                remaining_string = tempArr[1];
            } catch (Exception Ex) {
           // Ex.printStackTrace();
                //System.out.println(Ex.getLocalizedMessage());
            }

            if (place_date != "" || place_date != null || place_date != "null") {
                try {
                    if (place_date.indexOf(",") != -1) {
                        place = place_date.substring(0, place_date.lastIndexOf(",")).trim();
                        //System.out.println("Place " + "\t" + place);
                        date = place_date.substring(place_date.lastIndexOf(",") + 1, place_date.length()).trim();
                        //System.out.println("Date " + "\t" + date);
                    }
                } catch (Exception e) {
                //System.out.println("This is error message" + filename);
                    //e.printStackTrace();
                }
            }else {
                place = null;
                date = null;
            }

            //Extracting the content
            content = remaining_string.replaceAll("\r\n|\r|\n", " ");
            content = content.replace("  ", "");
            content = content.trim();
        //System.out.println("Content " + content);

//
//        //Populating the document object
            d.setField(FieldNames.TITLE, title);
            d.setField(FieldNames.AUTHOR, author);
            d.setField(FieldNames.AUTHORORG, org);
            d.setField(FieldNames.PLACE, place);
            d.setField(FieldNames.NEWSDATE, date);
            d.setField(FieldNames.CONTENT, content);
            d.setField(FieldNames.CATEGORY, category);
            d.setField(FieldNames.FILEID, file);
            return d;
        }

    }
}
