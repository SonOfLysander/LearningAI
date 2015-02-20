package io.paulbaker.binary_lib;

import io.paulbaker.binary_lib.BitStringLib;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BitStringLibTest extends TestCase {

  /*
  TODO: figure out why intellij can't find my class from here...
   */
  public void testGetBinaryString() throws Exception {
    String result = BitStringLib.getBinaryString(0);
    Assert.assertEquals("0000000000000000000000000000000", result);
    System.out.println(result);
  }
}
