// Copyright 2013, University of Freiburg,
// Chair of Algorithms and Data Structures.
// Author: Hannah Bast <bast@cs.uni-freiburg.de>.


import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Implementation of a very simple inverted index.
 */
public class QgramIndex {

  /**
   * The inverted lists = a sorted list of document ids, one for each word that
   * occurs at least once in the given collection.
   */
  protected HashMap<String, ArrayList<Integer>> invertedLists;
  public int q;

  /**
   * Contructor creates empty index.
   */
  public QgramIndex(int qvalue) {
    invertedLists = new HashMap<String, ArrayList<Integer>>();
    q = qvalue;

  }

  /**
   * Build inverted lists from given text file.  expected format of the file is
   * one line per document with each line containing the text from the document
   * without newlines.
   */
  public void buildFromTextFile(String fileName) throws IOException {
    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    int documentId = 0;
    while (true) {
      String line = bufferedReader.readLine();
      if (line == null) { break; }
      documentId++;
      String[] words = line.split("\\W+"); 
      // System.out.println(Arrays.toString(words));
      for (String word : words) {
        word = word.toLowerCase();
        ArrayList<String> qgrams = qgrams(word);
        for (String qgram : qgrams) {
          if (!invertedLists.containsKey(qgram)) {
            invertedLists.put(qgram, new ArrayList<Integer>());
          }
          invertedLists.get(qgram).add(documentId);
        }
      }
    }
  }

  ArrayList<String> qgrams(String word) {
    ArrayList<String> qgrams = new ArrayList<>();
    String pad = "$";
    pad = new String(new char[q - 1]).replace("\0", pad);
    word = pad + word + pad;
    for (int i = 0; i < word.length() - q + 1; i++) {
      qgrams.add(word.substring(i, i + q));
    }
    return qgrams;
  }



}
