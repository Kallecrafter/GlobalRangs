package de.kallecrafter.globalrangs.Listener;

import de.kallecrafter.globalrangs.GlobalRangsMain;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.IconComponent;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.PlayerNameTagRenderEvent;

public class NameTagListener {
  private final GlobalRangsMain addon;

  public NameTagListener(GlobalRangsMain addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onChatReceived(PlayerNameTagRenderEvent event) {
    String playerRank = getPlayerrank(event.getPlayerInfo().getTeam().getPrefix());
    TextComponent textComponent = Component.empty();
    Component icon1 = null;
    if (playerRank != null) {
      IconComponent iconComponent = null;
      if (playerRank.equals("Owner")) {
        if (!Laby.references().serverController().getCurrentStorageServerData().getName().toString().toLowerCase().contains("craftergang")) {
          iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerred.png"))).setHeight(8).setWidth(18);
        } else {
          iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerblue.png"))).setHeight(8).setWidth(18);
        }
      } else if (playerRank.startsWith("Admin")) {
        iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/admin.png"))).setHeight(8).setWidth(18);
      } else if (playerRank.startsWith("Mod")) {
        iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))).setHeight(8).setWidth(18);
      } else if (playerRank.startsWith("Dev")) {
        iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))).setHeight(8).setWidth(18);
      } else if (playerRank.startsWith("VIP")) {
        iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/vip.png"))).setHeight(12).setWidth(22);
      }
      event.setNameTag(iconComponent.append((Component)Component.text(" ")).append((Component)Component.text("§7" + event.getPlayerInfo().profile().getUsername())));
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
      } else if (rangName.startsWith("Mod")) {
        int index = rangName.indexOf("Mod");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "Mod".length());
          return foundWord;
        }
      } else if (rangName.startsWith("vip")) {
        int index = rangName.indexOf("vip");
        if (index != -1) {
          String foundWord = rangName.substring(index, index + "vip".length());
          return foundWord;
        }
      }
    }
    return null;
  }
}
