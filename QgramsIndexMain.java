// Copyright 2013, University of Freiburg,
// Chair of Algorithms and Data Structures.
// Author: Hannah Bast <bast@cs.uni-freiburg.de>.

import java.io.IOException;

/**
 * Print lengths of inverted lists.
 */
public class QgramsIndexMain {

  public static void main(String[] args) throws IOException {
    QgramIndex qi = new QgramIndex(3);
    if (args.length != 1) {
      System.out.println("Usage: java -jar InvertedIndexMain.jar <file>");
      System.exit(1);
    }
    qi.buildFromTextFile(args[0]);
    for (String word : qi.invertedLists.keySet()) {
      System.out.println(word + " "
          + qi.invertedLists.get(word).size());
    }
  }
}
