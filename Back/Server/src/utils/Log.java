package utils;

import java.util.Date;

public class Log {
  public static void info(String clientInfo,  String serverInfo) {
    System.out.println("");
    System.out.println("################################################################################################################");
    System.out.println(getHoursDate() + "INFO |  --> " + clientInfo);
    System.out.println(getHoursDate() + "INFO |  --> ServerOnly : " + serverInfo);
    System.out.println("################################################################################################################");
    System.out.println("");
  }

  public static void error(String clientInfo, String serverInfo) {
    System.out.println("");
    System.out.println("################################################################################################################");
    System.out.println(getHoursDate() + "ERROR |  /!\\  " + clientInfo);
    System.out.println(getHoursDate() + "ERROR |  /!\\  ServerOnly : " + serverInfo);
    System.out.println("################################################################################################################");
    System.out.println("");
  }

  public static String getHoursDate() {
    return new Date().toString() + " | ";
  }

  public static void title(String text) {
    System.out.println("############################\n\t" + text + "\n############################");
  }
}
