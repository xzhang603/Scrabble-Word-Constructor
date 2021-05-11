// Name: Xin Zhang
// USC NetID: xzhang55    4998-6225-16
// CS 455 PA4
// Spring 2021

import java.io.*;
import java.util.*;


public class ScoreTable {
   
   private HashMap<String, Integer> score_Map;
   
   //Build a Map, and hardcode all letter into Map
   //Key: each Letter
   //Value: Letter Corresponding Score
   //Upper and Lower case letter has same value;
   /*
   (1 point)-A, E, I, O, U, L, N, S, T, R
   (2 points)-D, G
   (3 points)-B, C, M, P
   (4 points)-F, H, V, W, Y
   (5 points)-K
   (8 points)- J, X
   (10 points)-Q, Z
   */
   public ScoreTable(){
      score_Map = new HashMap<String, Integer>();
      //1 Point
      score_Map.put("a",1);
      score_Map.put("e",1);
      score_Map.put("i",1);
      score_Map.put("o",1);
      score_Map.put("u",1);
      score_Map.put("l",1);
      score_Map.put("n",1);
      score_Map.put("s",1);
      score_Map.put("t",1);
      score_Map.put("r",1);
      
      score_Map.put("A",1);
      score_Map.put("E",1);
      score_Map.put("I",1);
      score_Map.put("O",1);
      score_Map.put("U",1);
      score_Map.put("L",1);
      score_Map.put("N",1);
      score_Map.put("S",1);
      score_Map.put("T",1);
      score_Map.put("R",1);
      
      //2 Points
      score_Map.put("d",2);
      score_Map.put("g",2);
      
      score_Map.put("D",2);
      score_Map.put("G",2);
      
      //3 Points
      score_Map.put("b",3);
      score_Map.put("c",3);
      score_Map.put("m",3);
      score_Map.put("p",3);
      
      score_Map.put("B",3);
      score_Map.put("C",3);
      score_Map.put("M",3);
      score_Map.put("P",3);
      
      //4 Points
      score_Map.put("f",4);
      score_Map.put("h",4);
      score_Map.put("v",4);
      score_Map.put("w",4);
      score_Map.put("y",4);
      
      score_Map.put("F",4);
      score_Map.put("H",4);
      score_Map.put("V",4);
      score_Map.put("W",4);
      score_Map.put("Y",4);
      
      //5 Points
      score_Map.put("k",5);
      
      score_Map.put("K",5);

      //8 Points
      score_Map.put("j",8);
      score_Map.put("x",8);

      score_Map.put("J",8);
      score_Map.put("X",8);
      
      //10 Points
      score_Map.put("q",10);
      score_Map.put("z",10);

      score_Map.put("Q",10);
      score_Map.put("Z",10);
   }
   
   //return the value of letter
   public int getVal(String s){
      return score_Map.get(s);
   }
   
   //return the score table
   public HashMap<String, Integer> getTable(){
      return score_Map;
   }
   
   
}
