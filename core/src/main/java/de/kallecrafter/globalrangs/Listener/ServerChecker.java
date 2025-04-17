package de.kallecrafter.globalrangs.Listener;

import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerChecker {

  public static final List<String> allowedServers = new ArrayList<>(Arrays.asList(
          "craftergang",
          "gommehd"
  ));

  public static boolean isAllowed(String serverName) {
    return allowedServers.contains(serverName.toLowerCase());
  }

  public static List<String> getAllowedServers() {
    return allowedServers;
  }

}







