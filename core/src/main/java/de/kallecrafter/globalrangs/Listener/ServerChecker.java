package de.kallecrafter.globalrangs.Listener;

import net.labymod.api.Laby;

public class ServerChecker {

  public static String serverchecker(String server) {
    if (Laby.references().serverController().getCurrentStorageServerData().getName().contains("craftergang") || Laby.references().serverController().getCurrentStorageServerData().getName().contains("gommehd")) {

    }
    return server;
  }
}







