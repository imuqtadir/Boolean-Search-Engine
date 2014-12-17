/**
 *
 */
package edu.buffalo.cse.irf14.index;

import edu.buffalo.cse.irf14.analysis.Dictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nikhillo Class that emulates reading data back from a written index
 */
public class IndexReader {

    public String IndexDir = "";
    public IndexType type = null;

    /**
     * Default constructor
     *
     * @param indexDir : The root directory from which the index is to be read.
     * This will be exactly the same directory as passed on IndexWriter. In case
     * you make subdirectories etc., you will have to handle it accordingly.
     * @param type The {@link IndexType} to read from
     */
    public IndexReader(String indexDir, IndexType type) {
        this.IndexDir = indexDir;
        this.type = type;
        //System.out.println("We have set them as " + this.IndexDir + this.type);
        //TODO
    }

    /**
     * Get total number of terms from the "key" dictionary associated with this
     * index. A postings list is always created against the "key" dictionary
     *
     * @return The total number of terms
     */
    public int getTotalKeyTerms() {

        int count = 0;
        String lineNumber = "";
        String filePath = "";
        BufferedReader br;
        String inputSearch = "\n";
        String line = "";

        if (this.type.equals(IndexType.TERM)) {
            //System.out.println("We are in term");
            filePath = IndexDir + File.separatorChar + "Term_Dictionary.txt";
        } else if (this.type.equals(IndexType.AUTHOR)) {
            filePath = IndexDir + File.separatorChar + "Author_Dictionary.txt";
        } else if (this.type.equals(IndexType.CATEGORY)) {
            filePath = IndexDir + File.separatorChar + "Category_Dictionary.txt";
        } else if (this.type.equals(IndexType.PLACE)) {
            filePath = IndexDir + File.separatorChar + "Place_Dictionary.txt";
        } else {
            //System.out.println("We are in 0");
            return 0;
        }
        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while ((line = br.readLine()) != null) {
                    count++;

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Get total number of terms from the "value" dictionary associated with
     * this index. A postings list is always created with the "value" dictionary
     *
     * @return The total number of terms
     */
    public int getTotalValueTerms() {

        int count = 0;
        String lineNumber = "";
        String filePath = "";
        BufferedReader br;
        String inputSearch = "\n";
        String line = "";

        if (this.type.equals(IndexType.TERM)) {
            filePath = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.AUTHOR)) {
            filePath = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.CATEGORY)) {
            filePath = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.PLACE)) {
            filePath = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else {
            return 0;
        }
        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while ((line = br.readLine()) != null) {
                    count++;

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return count;
        //TODO: YOU MUST IMPLEMENT THIS
        //return -1;
    }

    /**
     * Method to get the postings for a given term. You can assume that the raw
     * string that is used to query would be passed through the same Analyzer as
     * the original field would have been.
     *
     * @param term : The "analyzed" term to get postings for
     * @return A Map containing the corresponding fileid as the key and the
     * number of occurrences as values if the given term was found, null
     * otherwise.
     */
    public Map<String, Integer> getPostings(String term) {
        //System.out.println("term   " + term);
        int count = 0;
        String lineNumber = "";
        String filePath1 = "";
        String filePath2 = "";
        String filePath3 = "";
        BufferedReader br;
        String inputSearch = "\n";
        String line = "";
        String match = "";
        String sa[] = new String[2];
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.clear();

        if (this.type.equals(IndexType.TERM)) {
            // System.out.println("Hey a match 2");
            filePath1 = IndexDir + File.separatorChar + "Term_Dictionary.txt";
            filePath2 = IndexDir + File.separatorChar + "Term_index.txt";
            filePath3 = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.AUTHOR)) {
            // System.out.println("Hey a match 2");
            filePath1 = IndexDir + File.separatorChar + "Author_Dictionary.txt";
            filePath2 = IndexDir + File.separatorChar + "Author_index.txt";
            filePath3 = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.CATEGORY)) {
            // System.out.println("Hey a match 2");
            filePath1 = IndexDir + File.separatorChar + "Category_Dictionary.txt";
            filePath2 = IndexDir + File.separatorChar + "Category_index.txt";
            filePath3 = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        } else if (this.type.equals(IndexType.PLACE)) {
            // System.out.println("Hey a match 2");
            filePath1 = IndexDir + File.separatorChar + "Place_Dictionary.txt";
            filePath2 = IndexDir + File.separatorChar + "Place_index.txt";
            filePath3 = IndexDir + File.separatorChar + "Document_Dictionary.txt";
        }

        try {
            br = new BufferedReader(new FileReader(filePath1));
            try {
                while ((line = br.readLine()) != null) {
                    //System.out.println("line mathc"+line);
                    if (line.contains("#" + term + "@")) {

                        match = line;
                        break;
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (match == "" || match == "null" || match == null) {
            return null;
        } else {

            match = match.substring(match.lastIndexOf("@") + 1);
            match = match.trim();
        }

        try {
            br = new BufferedReader(new FileReader(filePath2));
            try {
                while ((line = br.readLine()) != null) {
                    if (line.contains("#" + match + "@")) {
                        //System.out.println(line);
                        match = line;
                        break;
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (match == "" || match == "null" || match == null) {
        } else {
            match = match.substring(match.lastIndexOf("@") + 1);
            match = match.trim();
        }
        //  System.out.println("THis is the match " +match);

        String[] arr = match.split(",");

        for (int i = 0; i < arr.length; i++) {
            //System.out.println("#"+ arr[i] + "@");
            try {
                br = new BufferedReader(new FileReader(filePath3));
                try {
                    while ((line = br.readLine()) != null) {
                        if (line.contains("@" + arr[i])) {
                            //System.out.println("lplplplp "+line);
                            sa[0] = line.substring(line.indexOf("#") + 1, line.indexOf("@"));
                            //System.out.println("sa"+sa[0]);
                            if (map.containsKey(sa[0])) {
                                int count1 = map.get(sa[0]);
                                count1 = count1 + 1;
                                map.put(term, count1);
                            } else {
                                map.put(sa[0], 1);
                            }
                        }

                    }
                    br.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        //  System.out.println("map "+ map.size());
//        Iterator it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it.next();
//            System.out.println(pairs.getKey() + " !!!= " + pairs.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//        
        //TODO:YOU MUST IMPLEMENT THIS
        return map;
    }

    /**
     * Method to get the top k terms from the index in terms of the total number
     * of occurrences.
     *
     * @param k : The number of terms to fetch
     * @return : An ordered list of results. Must be <=k fr valid k values null
     * for invalid k values
     */
    public List<String> getTopK(int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String keys = "";
        String value = "";
        String line = "";
        List<String> ls = new ArrayList<String>();
       // System.out.println("kaak "+Dictionary.tm.size());
        
        Iterator it = Dictionary.tm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            keys = (String) pairs.getKey();
            value = (String) pairs.getValue();
            String[] value_arr = value.split(",");
            map.put(keys, value_arr.length);
            //System.out.println(pairs.getKey() + " = " + pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            
        }
        
        
        
        Object[] a = map.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
                        ((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        
        int i = 0;
        for (Object e : a) {
            i++;
            //System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
            //        + ((Map.Entry<String, Integer>) e).getValue());
            ls.add(((Map.Entry<String, Integer>) e).getKey());
            if(i == k){
                break;
            }
        }
        //TODO YOU MUST IMPLEMENT THIS
        return ls;
    }

    /**
     * Method to implement a simple boolean AND query on the given index
     *
     * @param terms The ordered set of terms to AND, similar to getPostings()
     * the terms would be passed through the necessary Analyzer.
     * @return A Map (if all terms are found) containing FileId as the key and
     * number of occurrences as the value, the number of occurrences would be
     * the sum of occurrences for each participating term. return null if the
     * given term list returns no results BONUS ONLY
     */
    public Map<String, Integer> query(String... terms) {
        //TODO : BONUS ONLY
        return null;
    }
}
