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

public class NameTagListener {

  private final GlobalRangsMain addon;

  public NameTagListener(GlobalRangsMain addon) {
    this.addon = addon;
  }


  @Subscribe
  public void onChatReceive(PlayerNameTagRenderEvent event) {
    String playerRank = getPlayerrank(event.getPlayerInfo().getTeam().getPrefix());
    Component modifiedMessage = Component.empty();
    Component icon1 = null;
    if (playerRank != null) {
      //if (Laby.references().serverController().getCurrentStorageServerData().getName())
      if (playerRank.equals("Owner")) {
        if (!Laby.references().serverController().getCurrentStorageServerData().getName().toString().toLowerCase().contains("craftergang")) {
          icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerred.png"))).setHeight(8).setWidth(18);
        } else {
          icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerblue.png"))).setHeight(8).setWidth(18);
        }
      }
      else if (playerRank.startsWith("Admin")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/admin.png"))).setHeight(8).setWidth(18);
      }
      else if (playerRank.startsWith("Mod")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))
        ).setHeight(8).setWidth(18);
      }
      else if (playerRank.startsWith("Dev")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/dev.png"))
        ).setHeight(8).setWidth(18);
      }
      else if (playerRank.startsWith("TeamFreund") || playerRank.startsWith("TeamFreund+") || playerRank.startsWith("TF") || playerRank.startsWith("TF+")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/teamfreund.png"))).setHeight(12).setWidth(22);
      }
      else if (playerRank.startsWith("VIP")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/vip.png"))).setHeight(12).setWidth(22);
      }
      else if (playerRank.startsWith("Spieler")) {
        icon1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/spieler.png"))).setHeight(12).setWidth(22);
      }

      event.setNameTag(icon1.append(Component.text(" ")).append(Component.text("§7" + event.getPlayerInfo().profile().getUsername())));
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
      else if (rangName.startsWith("TeamFreund")) {
        int index = rangName.indexOf("TeamFreund");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "TeamFreund".length());
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
      else if (rangName.startsWith("vip")) {
        int index = rangName.indexOf("vip");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "vip".length());
          return foundWord;
        }
      }
      else if (rangName.startsWith("Spieler")) {
        int index = rangName.indexOf("Spieler");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Spieler".length());
          return foundWord;
        }
      }
    }
    return null;
  }


}
