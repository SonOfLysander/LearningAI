package io.paulbaker.binary_lib;

/**
 * Created by paulbaker on 2/19/15.
 */
public class BitStringLib {

  private BitStringLib() {
  }

  public static String getBinaryString(Long number) {
    return String.format("%32", Long.toBinaryString(number)).replace(' ', '0');
  }

}
