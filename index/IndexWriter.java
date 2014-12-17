/**
 *
 */
package edu.buffalo.cse.irf14.index;

import edu.buffalo.cse.irf14.analysis.Analyzer;
import edu.buffalo.cse.irf14.analysis.AnalyzerFactory;
import edu.buffalo.cse.irf14.analysis.Dictionary;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;
import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author nikhillo Class responsible for writing indexes to disk
 */
public class IndexWriter {

    public static int docid = 0;
    public String indexdir = "";

    /**
     * Default constructor
     *
     * @param indexDir : The root directory to be sued for indexing
     */
    public IndexWriter(String indexDir) {
        this.indexdir = indexDir;
        //TODO : YOU MUST IMPLEMENT THIS
    }

    /**
     * Method to add the given Document to the index This method should take
     * care of reading the filed values, passing them through corresponding
     * analyzers and then indexing the results for each indexable field within
     * the document.
     *
     * @param d : The Document to be added
     * @throws IndexerException : In case any error occurs
     */
    public void addDocument(Document d) throws IndexerException {
       // System.out.println(d.getField(FieldNames.FILEID)[0]);

        docid++;
        TokenStream objTokenStream = new TokenStream();
        Tokenizer objTokenizer = new Tokenizer();
        Dictionary objDictionary = new Dictionary();
        Analyzer analyzerForField = null;

        //Creating Document_Dictionary;
        if (d.getField(FieldNames.FILEID) != null) {
            Dictionary.Document_Dictionary.put(d.getField(FieldNames.FILEID)[0], docid);
        }

        if (d.getField(FieldNames.CATEGORY) != null) {
            //Creating Category dictionary
           // System.out.println("in Cat");
            objDictionary.addcategory(d.getField(FieldNames.CATEGORY)[0], docid);
        }

        if (d.getField(FieldNames.TITLE) != null) {
            try {
                objTokenStream = objTokenizer.consume(d.getField(FieldNames.TITLE)[0]);
                analyzerForField = AnalyzerFactory.getInstance().getAnalyzerForField(FieldNames.TITLE, objTokenStream);
                analyzerForField.increment();
                objTokenStream = analyzerForField.getStream();
                //System.out.println("This is size "+objTokenStream.Tokenlist.size());
                objDictionary.populate_treemap(objTokenStream);
                objTokenStream.Emptytokenlist();
            } catch (TokenizerException e) {

            }
        }

        //Creating Author dictionary
        if (d.getField(FieldNames.AUTHOR) != null) {

            //System.out.println(d.getField(FieldNames.AUTHOR)[0] + docid);
            if (d.getField(FieldNames.AUTHOR)[0] != null) {
                try {
                    objTokenStream = objTokenizer.consume(d.getField(FieldNames.AUTHOR)[0]);
                    analyzerForField = AnalyzerFactory.getInstance().getAnalyzerForField(FieldNames.AUTHOR, objTokenStream);
                    analyzerForField.increment();
                    objTokenStream = analyzerForField.getStream();
                    objDictionary.addauthor(objTokenStream);
                    objTokenStream.Emptytokenlist();
                } catch (TokenizerException e) {

                }
            }
            //    System.out.println(d.getField(FieldNames.AUTHOR)[0] + docid);

        }
//
        if (d.getField(FieldNames.AUTHORORG) != null) {
            if (d.getField(FieldNames.AUTHORORG)[0] != null) {
                try {
                    objTokenStream = objTokenizer.consume(d.getField(FieldNames.AUTHORORG)[0]);
                    analyzerForField = AnalyzerFactory.getInstance().getAnalyzerForField(FieldNames.AUTHOR, objTokenStream);
                    analyzerForField.increment();
                    //objTokenStream.append_Tokenlist_proc_term(objTokenStream);
                    objDictionary.addauthor(objTokenStream);
                    objTokenStream.Emptytokenlist();
                } catch (TokenizerException e) {

                }
            }
        }
//                
        if (d.getField(FieldNames.PLACE) != null) {
            objDictionary.addplace(d.getField(FieldNames.PLACE)[0], docid);
        }
//                
//                if (d.getField(FieldNames.NEWSDATE) != null) {
//                    objTokenStream = objTokenizer.consume(d.getField(FieldNames.NEWSDATE)[0]);
//                    analyzerForField = AnalyzerFactory.getInstance().getAnalyzerForField(FieldNames.NEWSDATE, objTokenStream);
//                    objTokenStream = analyzerForField.process(objTokenStream);
//                    objDictionary.populate_treemap(objTokenStream);
//                    objTokenStream.Emptytokenlist();
//                
//                
//                }
//
        if (d.getField(FieldNames.CONTENT) != null) {
            try {
                objTokenStream = objTokenizer.consume(d.getField(FieldNames.CONTENT)[0]);
                analyzerForField = AnalyzerFactory.getInstance().getAnalyzerForField(FieldNames.CONTENT, objTokenStream);
                analyzerForField.increment();
                objTokenStream = analyzerForField.getStream();
                //System.out.println("This is size CONTENT "+objTokenStream.Tokenlist.size());
                objDictionary.populate_treemap(objTokenStream);
                objTokenStream.Emptytokenlist();
            } catch (TokenizerException e) {

            }

        }
        //Dictionary objDictionary = new Dictionary();
        //objDictionary.prepare_term_dictionary(objTokenStream);

//            System.out.println("\n\n\n");
    }

    /**
     * Method that indicates that all open resources must be closed and cleaned
     * and that the entire indexing operation has been completed.
     *
     * @throws IndexerException : In case any error occurs
     */
    public void close() {
        System.out.println("Closed down");
        try {
            File file = null;
            FileWriter fw = null;
            BufferedWriter bw = null;
            Set<Integer> keys = null;
            Set<String> keys1 = null;
            int x = 0;
            Set set = null;
            Iterator i = null;

            file = new File(this.indexdir + File.separator + "Document_Dictionary.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            keys1 = Dictionary.Document_Dictionary.keySet();
            for (String k : keys1) {
                // System.out.println(k+" -- "+Dictionary.Document_Dictionary.get(k));
                bw.write("#" + k + "@" + Dictionary.Document_Dictionary.get(k));
                bw.write("\n");
            }

            bw.close();

            File file1 = null;
            File file2 = null;
            FileWriter fw1 = null;
            FileWriter fw2 = null;
            BufferedWriter bw1 = null;
            BufferedWriter bw2 = null;

            file1 = new File(this.indexdir + File.separator + "Term_Dictionary.txt");
            file2 = new File(this.indexdir + File.separator + "Term_index.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fw1 = new FileWriter(file1.getAbsoluteFile());
            bw1 = new BufferedWriter(fw1);
            fw2 = new FileWriter(file2.getAbsoluteFile());
            bw2 = new BufferedWriter(fw2);
            System.out.println("The size of tm" + Dictionary.tm.size());
            set = Dictionary.tm.entrySet();
            // Get an iterator
            i = set.iterator();
            x = 0;
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
//         System.out.print(me.getKey() + ": ");
//         System.out.println(me.getValue());
                bw1.write("#" + me.getKey() + "@" + x);
                bw1.write("\n");
                bw2.write("#" + x + "@" + me.getValue());
                bw2.write("\n");
                x++;
            }

            bw1.close();
            bw2.close();

            file1 = new File(this.indexdir + File.separator + "Author_Dictionary.txt");
            file2 = new File(this.indexdir + File.separator + "Author_index.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fw1 = new FileWriter(file1.getAbsoluteFile());
            bw1 = new BufferedWriter(fw1);
            fw2 = new FileWriter(file2.getAbsoluteFile());
            bw2 = new BufferedWriter(fw2);

            set = Dictionary.Author_Tree_Map.entrySet();
            // Get an iterator
            i = set.iterator();
            x = 0;
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
//         System.out.print(me.getKey() + ": ");
//         System.out.println(me.getValue());
                bw1.write("#" + me.getKey() + "@" + x);
                bw1.write("\n");
                bw2.write("#" + x + "@" + me.getValue());
                bw2.write("\n");
                x++;
            }

            bw1.close();
            bw2.close();
//
//            
//            
//            
            file1 = new File(this.indexdir + File.separator + "Category_Dictionary.txt");
            file2 = new File(this.indexdir + File.separator + "Category_index.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fw1 = new FileWriter(file1.getAbsoluteFile());
            bw1 = new BufferedWriter(fw1);
            fw2 = new FileWriter(file2.getAbsoluteFile());
            bw2 = new BufferedWriter(fw2);

            set = Dictionary.Category_Tree_Map.entrySet();
            System.out.println(Dictionary.Category_Tree_Map.size());
            // Get an iterator
            i = set.iterator();
            x = 0;
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
                bw1.write("#" + me.getKey() + "@" + x);
                bw1.write("\n");
                bw2.write("#" + x + "@" + me.getValue());
                bw2.write("\n");
                x++;
            }

            bw1.close();
            bw2.close();

            file1 = new File(this.indexdir + File.separator + "Place_Dictionary.txt");
            file2 = new File(this.indexdir + File.separator + "Place_index.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fw1 = new FileWriter(file1.getAbsoluteFile());
            bw1 = new BufferedWriter(fw1);
            fw2 = new FileWriter(file2.getAbsoluteFile());
            bw2 = new BufferedWriter(fw2);

            set = Dictionary.Place_Tree_Map.entrySet();
            // Get an iterator
            i = set.iterator();
            x = 0;
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
//         System.out.print(me.getKey() + ": ");
//         System.out.println(me.getValue());
                bw1.write("#" + me.getKey() + "@" + x);
                bw1.write("\n");
                bw2.write("#" + x + "@" + me.getValue());
                bw2.write("\n");
                x++;
            }

            bw1.close();
            bw2.close();

//        Iterator it = Dictionary.tm.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it.next();
//            System.out.println(pairs.getKey() + " = " + pairs.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
            //TODO
        } catch (Exception e) {

        }
    }

}
