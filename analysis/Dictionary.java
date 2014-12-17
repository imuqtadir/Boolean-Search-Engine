/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.buffalo.cse.irf14.analysis;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Imran Bijapuri
 */


/**
 *
 * @author Imran Bijapuri
 */
public class Dictionary {
    public static Map<String, Integer> Document_Dictionary = new LinkedHashMap<String, Integer>();
    public static TreeMap Category_Tree_Map =  new TreeMap();
    public static TreeMap Author_Tree_Map =  new TreeMap();
    public static TreeMap Place_Tree_Map =  new TreeMap();
    
    
    
    
    
    public static TreeMap tm = new TreeMap();
    
    
    public void addcategory(String Catname,int Docid){
        
        // System.out.println(Category_Tree_Map.size());
        if(Category_Tree_Map.containsKey(Catname)){
            Category_Tree_Map.put(Catname, Category_Tree_Map.get(Catname)+ "," +Docid);
        }else{
            Category_Tree_Map.put(Catname,Docid);
        }
        
        
        
        
//        if(Category_Dictionary.containsKey(Catname)){
//            int a = Category_Dictionary.get(Catname);
//            String b = Category_index.get(a);
//            b = b + Integer.toString(Docid)+",";
//            Category_index.put(a,b);
//        }else{
//            catid++;
//            Category_Dictionary.put(Catname, catid);
//            Category_index.put(catid,Integer.toString(Docid)+",");
//            
//        }
        
    }
    
   
    public void addplace(String place,int Docid){
        
        if(Place_Tree_Map.containsKey(place)){
            Place_Tree_Map.put(place, Place_Tree_Map.get(place)+ "," +Docid);
        }else{
            Place_Tree_Map.put(place,Docid);
        }
  }
    
  
    
    
    public void populate_treemap(TokenStream stream){
        Token t = null;
        String term = "";
        int x = stream.Tokenlist.size();
   
        for(int i = 0;i < x;i++){
            t = stream.Tokenlist.get(i);
            term = t.getTermText();
      
         if(tm.containsKey(term)){
            tm.put(term, tm.get(term)+ "," +t.isDocid());
        }else{
            tm.put(term,t.isDocid());
        }
        
        }
    }
    
     public void addauthor(TokenStream stream){
        
            Token t = null;
            String term = "";
            int x = stream.Tokenlist.size();
            
            for(int i = 0;i < x;i++){
                t = stream.Tokenlist.get(i);
                term = t.getTermText();

            if(Author_Tree_Map.containsKey(term)){
                Author_Tree_Map.put(term, Author_Tree_Map.get(term)+ "," +t.isDocid());
            }else{
                Author_Tree_Map.put(term,t.isDocid());
            }

            }
       }

}
