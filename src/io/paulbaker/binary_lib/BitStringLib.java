package io.paulbaker.binary_lib;

/**
 * Created by paulbaker on 2/19/15.
 */
public class BitStringLib {

  private BitStringLib() {
  }

  public static String getBinaryString(long number) {
    return String.format("%" + Long.SIZE + "s", Long.toBinaryString(number)).replace(' ', '0');
  }

  public static String getBinaryString(int number) {
    return String.format("%" + Integer.SIZE + "s", Integer.toBinaryString(number)).replace(' ', '0');
  }

  /*public static String getBinaryString(short number) {
    return String.format("%" + Short.SIZE + "s", Short.toBinaryString(number)).replace(' ', '0');
  }

  public static String getBinaryString(char number) {
    return String.format("%" + Character.SIZE + "s", Character.toBinaryString(number)).replace(' ', '0');
  }*/

}
