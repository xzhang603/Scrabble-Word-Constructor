// Name: Xin Zhang
// USC NetID: xzhang55    4998-6225-16
// CS 455 PA4
// Spring 2021

import java.io.*;
import java.util.*;




/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   
   private Map<String, ArrayList<String>> dic_Map;
   private Set<String> Check_duo = new HashSet<String>();

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {
      dic_Map = new HashMap<String, ArrayList<String>>();
      File inFile = new File(fileName);
      try (Scanner in = new Scanner(inFile))
      {
         readData(in,dic_Map);
      }  
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      ArrayList<String> res = new ArrayList<String>();
      if(dic_Map.containsKey(s)){
         res = dic_Map.get(s);     //return the Arraylist of all combination of anagram word that we stored in readData
      }
      return res; 
   }
   
   
   /*
   Test whether the Map we created is correct create all element in map
   */
   public Map<String, ArrayList<String>> testMap(){
      return dic_Map;
   }
   
   
   
   /*
   Key: alphebtical order word
   Value-ArrayList: word in dictionary that has same alphebtical order string with key.
   For each word in dictionary, I first sort the word in alphebtical order. Then, I check that if the sorted word is in the Map.
   If in the Map, we add the word into the value-ArrayList and update the Map. Otherwise, I create a new Arraylist and add the word into the Arraylist, and put sorted
   word and ArrayList into Map.
   */
   private void readData(Scanner in, Map<String, ArrayList<String>> dic_Map) throws IllegalDictionaryException{
      while(in.hasNext()){
         //Find new word in sorted version
         String new_word = in.next();
         if(Check_duo.contains(new_word)){
            throw new IllegalDictionaryException(new_word);}
         else{
            Check_duo.add(new_word);}
         
         char[] chars = new_word.toCharArray();
         Arrays.sort(chars);
         String new_word_sorted = new String(chars);
         
         //Load data in to different subset
         if(!dic_Map.containsKey(new_word_sorted)){ //If not contain, create a new set and put word into set, key is sort word
            ArrayList<String> s = new ArrayList<String>();
            s.add(new_word);
            dic_Map.put(new_word_sorted, s); 
         }
         else{                                      //If contains, update the word into the set
            ArrayList<String> s = dic_Map.get(new_word_sorted);
            s.add(new_word);
            dic_Map.put(new_word_sorted, s);
         }
      }
      
   }
   
}
