package de.kallecrafter.globalrangs.Listener;

import de.kallecrafter.globalrangs.GlobalRangsMain;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.serializer.plain.PlainTextComponentSerializer;
import net.labymod.api.client.entity.player.tag.event.NameTagBackgroundRenderEvent;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.event.client.render.PlayerNameTagRenderEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.kallecrafter.globalrangs.Listener.ServerChecker.allowedServers;

public class NameTagListener {

  private final GlobalRangsMain addon;

  public NameTagListener(GlobalRangsMain addon) {
    this.addon = addon;
  }



  @Subscribe
  public void onNameTagReceive(PlayerNameTagRenderEvent event) {
    String playerRank = getPlayerrank(event.getPlayerInfo().getTeam().getPrefix());
    Component modifiedMessage = Component.empty();
    String server = Laby.references().serverController().getCurrentStorageServerData().getName().toString().toLowerCase();
    Component icon1 = null;
    if (playerRank != null) {
      if (ServerChecker.allowedServers.contains(server)) {
        if (playerRank.equals("Owner")) {
          if (!server.contains("craftergang")) {
            icon1 = Component.icon(
                    Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerred.png")))
                .setHeight(8).setWidth(18);
          } else {
            icon1 = Component.icon(Icon.texture(
                    ResourceLocation.create("globalrangs", "textures/rangs/ownerblue.png")))
                .setHeight(8).setWidth(18);
          }
        } else if (playerRank.startsWith("Admin")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/admin.png")))
              .setHeight(8).setWidth(18);
        } else if (playerRank.startsWith("Mod")) {
          icon1 = Component.icon(
              Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))
          ).setHeight(8).setWidth(18);
        } else if (playerRank.startsWith("Dev")) {
          icon1 = Component.icon(
              Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/dev.png"))
          ).setHeight(8).setWidth(18);
        } else if (playerRank.startsWith("TeamFreund") || playerRank.startsWith("TeamFreund+")
            || playerRank.startsWith("TF") || playerRank.startsWith("TF+")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/teamfreund.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("VIP") && server.contains("gommehd")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/vip.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("Build") || playerRank.startsWith("Builder")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/builder.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("Supremium") || playerRank.startsWith("Supreme") || playerRank.startsWith("§b") && server.contains("gommehd")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/supremium.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("Premium") || playerRank.startsWith("§6") && server.contains("gommehd")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/supremium.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("S") && server.contains("craftergang")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/spieler.png")))
              .setHeight(12).setWidth(22);
        } else if (playerRank.startsWith("§a") && server.contains("gommehd")) {
          icon1 = Component.icon(
                  Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/spieler.png")))
              .setHeight(12).setWidth(22);
        }
        event.setNameTag(icon1.append(Component.text("§7" + event.getPlayerInfo().profile().getUsername())));
      }
    }
  }



  public static String getPlayerrank(Component rang) {
    String rangName = null;
    if (rang != null) {
      rangName = rang.toString();
      if (rangName.contains("Owner")) {
        int index = rangName.indexOf("Owner");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Owner".length());
          return foundWord;
        }
      } else if (rangName.contains("Admin")) {
        int index = rangName.indexOf("Admin");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Admin".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("Mod")) {
        int index = rangName.indexOf("Mod");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Mod".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("Dev")) {
        int index = rangName.indexOf("Dev");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Dev".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("Freund/in")) {
        int index = rangName.indexOf("Freund/in");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Freund/in".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("TF")) {
        int index = rangName.indexOf("TF");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "TF".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("VIP")) {
        int index = rangName.indexOf("VIP");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "VIP".length());
          return foundWord;
        }
      }
      else if (rangName.equals("§b")) {
        int index = rangName.indexOf("§b");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "§b".length());
          return foundWord;
        }
      }
      else if (rangName.equals("Supremium")) {
        int index = rangName.indexOf("Supremium");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Supremium".length());
          return foundWord;
        }
      }
      else if (rangName.equals("Suprem")) {
        int index = rangName.indexOf("Suprem");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Suprem".length());
          return foundWord;
        }
      }
      else if (rangName.equals("Supreme")) {
        int index = rangName.indexOf("Supreme");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Supreme".length());
          return foundWord;
        }
      }
      else if (rangName.equals("§6")) {
        int index = rangName.indexOf("§6");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "§6".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("Premium")) {
        int index = rangName.indexOf("Premium");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Premium".length());
          return foundWord;
        }
      }
      else if (rangName.equals("§a")) {
        int index = rangName.indexOf("§a");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "§a".length());
          return foundWord;
        }
      }
      else if (rangName.equals("S")) {
        int index = rangName.indexOf("S");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "S".length());
          return foundWord;
        }
      }
    }
    return null;
  }


}
