package io.paulbaker.binary_lib;

import junit.framework.TestCase;
import org.junit.Test;

public class BitStringLibTest extends TestCase {

  @Test
  public void testGetBinaryStringLong() throws Exception {
    String result = BitStringLib.getBinaryString(0l);
    assertEquals("0000000000000000000000000000000000000000000000000000000000000000", result);
    result = BitStringLib.getBinaryString(1l);
    assertEquals("0000000000000000000000000000000000000000000000000000000000000001", result);
    result = BitStringLib.getBinaryString(1l << 63);
    assertEquals("1000000000000000000000000000000000000000000000000000000000000000", result);
  }

  @Test
  public void testGetBinaryStringInt() throws Exception {
    String result = BitStringLib.getBinaryString(0);
    assertEquals("00000000000000000000000000000000", result);
    result = BitStringLib.getBinaryString(1);
    assertEquals("00000000000000000000000000000001", result);
    result = BitStringLib.getBinaryString(1 << 31);
    assertEquals("10000000000000000000000000000000", result);
  }
}

