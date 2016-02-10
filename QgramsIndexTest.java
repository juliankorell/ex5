// Copyright 2013, University of Freiburg,
// Chair of Algorithms and Data Structures.
// Author: Hannah Bast <bast@cs.uni-freiburg.de>.

import java.util.Arrays;
import java.util.TreeMap;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;

/**
 * One unit test for each non-trivial method in the InvertedIndex class.
 */
public class QgramsIndexTest {

  @Test
  public void buildFromTextFile() throws IOException {
    QgramIndex qi = new QgramIndex(3);
    qi.buildFromTextFile("test.txt");
    // Note 1: convert to tree map to have a deterministic order (order for hash
    // map may vary from machine to machine and even from run to run).
    // Note 2: we still use HashMap map in the main class, because it is more
    // efficient than TreeMap. For the unit test, efficiency does not matter.
    TreeMap<String, ArrayList<Integer>> invertedLists =
      new TreeMap<String, ArrayList<Integer>>(qi.invertedLists);
    Assert.assertEquals(
        "[$$h=[1, 3], $$t=[2], $hi=[1, 3], $th=[2], e$$=[2], "
                + "he$=[2], hi$=[1, 3], i$$=[1, 3], the=[2]]",
        invertedLists.entrySet().toString());
  }

  @Test
  public void qgrams() throws IOException {
    QgramIndex qi = new QgramIndex(3);
    ArrayList<String> list1 = new ArrayList<String>();
    list1.add("$$w");
    list1.add("$wo");
    list1.add("wor");
    list1.add("ord");
    list1.add("rd$");
    list1.add("d$$");
    ArrayList<String> qgrams1 = qi.qgrams("word");
    Assert.assertEquals("[$$w, $wo, wor, ord, rd$, d$$]",
            Arrays.toString(qgrams1.toArray()));
  }
}
