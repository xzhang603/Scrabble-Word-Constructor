// Name: Xin Zhang
// USC NetID: xzhang55    4998-6225-16
// CS 455 PA4
// Spring 2021

import java.io.IOException;

/**
   This class reports problems with the dictionary file.
 */
public class IllegalDictionaryException extends IOException {
   public IllegalDictionaryException() {}
   public IllegalDictionaryException(String message)
   {
      super(message);
   }
}
