// Name: Xin Zhang
// USC NetID: xzhang55    4998-6225-16
// CS 455 PA4
// Spring 2021

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.*;
import java.io.FileNotFoundException;



public class WordFinder{
   
   public static final String main_file = "./sowpods.txt";  //If we dont input a file name, the file would use sowpods.txt, otherwise, we use the one we input into args.
   public static final String tiny_lo_file = "./testFiles/tinyDictionary.txt";//For testing
   public static final String tiny_up_file = "./testFiles/tinyDictionaryUpper.txt";//For testing
   public static String file_name = "";
      
   /*
   Main function that would catch both FileNotFound and IlleagalDictionary excpetion
   If we a new line is ".", then we would exist.
   If input args has a dictionary name, we would use args. Otherwise, use the one we input into args.
   Read each word and return the combination a each word
   */
   public static void main(String[] args){
      if(args.length!=0){
         file_name = args[0];
      }
      else{file_name = main_file;}     
      try{
         AnagramDictionary new_dic = new AnagramDictionary(file_name);
         System.out.println("Type . to quit.");
         System.out.print("Rack? ");
         Scanner in = new Scanner(System.in);
         while(in.hasNext()){
            String word = in.next();
            if(!word.equals(".")){
               print_Word(each_word(word, new_dic), word);
               System.out.print("Rack? ");}
            else{return;}
         }
      }
      catch (FileNotFoundException exception){
         System.out.println("Error: Dictionary file "+'"'+file_name+'"'+" does not exist.");
         System.out.println("Exiting program.");
         return;
      }
      catch (IllegalDictionaryException exception){
         System.out.println("Error: Illegal dictionary: dictionary file has a duplicate word: "+ exception.getMessage());
         System.out.println("Exiting program.");
         return;
      }
   }
   
   /*
   Function: each_word(---)
   Input: the word we want to find Scrabble; Dictionary from input.
   Return: All valid combination of input word.
   Process: we first sort the word. Then, find all unique single letter appears in that word.
            Also, we would find the frequency of each letter by function later.
            After that, we feed all the word into rack function get all combination of that word(may contains invalid word)
            Finally, we put the ArrayList of Rack into AnagramDictionary to get all valid combination of word.
   */
   public static HashSet<String> each_word(String word, AnagramDictionary new_dic){
      Rack RA = new Rack();
      ArrayList<String> RackList = new ArrayList<String>();
      //Sort String
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      String word_sorted = new String(chars);
      //Use function to get input of Rack.allSubsets
      String inde_word = word_diff(word_sorted);
      int[] freq_word = word_freq(word_sorted,inde_word);
      RackList = RA.allSubsets(inde_word, freq_word, 0);
      HashSet<String> comb = new HashSet<String>();
      //Get all combnation of each Rack and put all of them into a HashSet
      for(int i=0; i<RackList.size(); i++){ //For each Rack
         String cur = RackList.get(i);
         ArrayList<String> cur_array = new ArrayList<String>();
            
         cur_array = new_dic.getAnagramsOf(cur);
         for(int j=0; j<cur_array.size(); j++){//Save all combination of that Rack
            comb.add(cur_array.get(j));}
      }
      return comb;
   }   
   
   
   /*
   Function: print_Word(---)
   Input: all valid combination of each word;  the original version of word
   Print: score of each combination from high to low. note: if two word have same socre, it would be alphbetic order. 
   After we got all valid combination of each word. We found the score of each combination, and print them in order above. 
   Use TreeSet and TreeMap to sort the value
   */
   public static void print_Word(HashSet<String> comb, String word){
      ScoreTable st = new ScoreTable();
      Map<Integer, TreeSet<String>> score_word_set = new TreeMap<Integer, TreeSet<String>>(Collections.reverseOrder());
      Iterator<String> iter = comb.iterator();
      while(iter.hasNext()){ //While there is an element in combnation set
         String str = iter.next();
         int score = 0;
         for(int i=0; i<str.length();i++){ //get value of each combnation
            char c = str.charAt(i);
            score += st.getVal(Character.toString(c));}
         if(!score_word_set.containsKey(score)){ //Put that score(key), word(TreeSet) into a TreeMap
            TreeSet<String> word_set_val = new TreeSet<String>();
            word_set_val.add(str);
            score_word_set.put(score, word_set_val); }
         else{
            TreeSet<String> word_set_val = score_word_set.get(score);
            word_set_val.add(str);
            score_word_set.put(score, word_set_val);}
      }
      int len = comb.size();
      word = '"'+word+'"';        //Print them with correct version  (score: word)
      System.out.println("We can make "+len+" words from "+word);
      if(len>0){System.out.println("All of the words with their scores (sorted by score):");}
      for(Map.Entry<Integer, TreeSet<String>> curr:score_word_set.entrySet()){
         for(String curr_word:curr.getValue()){
            System.out.println(curr.getKey()+": "+curr_word);}
      }
   }
   
   /*
   Function: word_freq(----)
   Input: Sorted Word; Unique Letter of that word
   Return: Frequency of each unique letter.
   */
   public static int[] word_freq(String sort_word, String inde_word){
      int[] freq = new int[inde_word.length()];
      for(int i=0; i<sort_word.length();i++){
         for(int j=0; j<inde_word.length();j++){
            if(sort_word.charAt(i)==inde_word.charAt(j)){
               freq[j] += 1;
            }
         }
      }
      return freq;
   }
   
   
   /*
   Function: word_diff(--)
   Input: Sorted word
   Return: Unique Letter of that word
   */
   public static String word_diff(String sort_word){
      HashSet<Character> word_set = new HashSet<Character>();
      String inde_word = "";
      for(int i=0; i<sort_word.length();i++){
         char w = sort_word.charAt(i);
         if(!word_set.contains(w)){
            word_set.add(w);
            inde_word += w;
         }
      }
      return inde_word;
   }
}

