package io.paulbaker.binary_lib;

import io.paulbaker.binary_lib.BitStringLib;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BitStringLibTest extends TestCase {

  public void testGetBinaryStringLong() throws Exception {
    String result = BitStringLib.getBinaryString(0l);
    Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000000", result);
    result = BitStringLib.getBinaryString(1l);
    Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000001", result);
    result = BitStringLib.getBinaryString(1l << 63);
    Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000001", result);
  }

  public void testGetBinaryStringInt() throws Exception {
    String result = BitStringLib.getBinaryString(0);
    Assert.assertEquals("00000000000000000000000000000000", result);
    result = BitStringLib.getBinaryString(1);
    Assert.assertEquals("00000000000000000000000000000001", result);
    result = BitStringLib.getBinaryString(1 << 31);
    Assert.assertEquals("10000000000000000000000000000000", result);
  }
}
