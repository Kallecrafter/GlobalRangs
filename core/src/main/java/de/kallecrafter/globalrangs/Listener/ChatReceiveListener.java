package de.kallecrafter.globalrangs.Listener;

import de.kallecrafter.globalrangs.GlobalRangsMain;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.serializer.plain.PlainTextComponentSerializer;
import net.labymod.api.client.entity.player.tag.TagType;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatReceiveListener {

  private final GlobalRangsMain addon;

  public ChatReceiveListener(GlobalRangsMain addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    String playerRank = getPlayerrank(event.message());
    Component message = event.message();
    Component modifiedMessage = Component.empty();
    Component icon2 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/grdesigner.png"))).setHeight(12).setWidth(22);
    Component icon3 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/grdev.png"))).setHeight(12).setWidth(22);
    if (playerRank != null) {
      //if (Laby.references().serverController().getCurrentStorageServerData().getName())
      if (playerRank.equals("Owner")) {
        Component ownerIcon;
        if (!Laby.references().serverController().getCurrentStorageServerData().getName().toString().toLowerCase().contains("craftergang")) {
          ownerIcon = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerred.png"))).setHeight(12).setWidth(22);
        } else {
          ownerIcon = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerblue.png"))).setHeight(12).setWidth(22);
        } for (int i = 0; i < message.children().size(); i++) {
            Component c = message.children().get(i);
            TextComponent t = ((TextComponent) c);
            boolean space = t.content().endsWith(" ");
            if (!t.content().startsWith(playerRank))
              continue;
            Component n = ownerIcon.append(Component.text(space ? " " : ""));
            message.replace(i, n);
            modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
            break;
          }
      }
      else if (playerRank.startsWith("Admin")) {
        Component adminIcon = Component.icon(
            Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/admin.png"))
        ).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); i++) {
          Component c = message.children().get(i);
          TextComponent t = ((TextComponent) c);
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank))
            continue;
          Component n = adminIcon.append(Component.text(space ? " " : ""));
          message.replace(i, n);
          modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
          break;
        }
      }
        else if (playerRank.startsWith("Mod")) {
          Component modIcon = Component.icon(
              Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))
          ).setHeight(12).setWidth(22);
          for (int i = 0; i < message.children().size(); i++) {
            Component c = message.children().get(i);
            TextComponent t = ((TextComponent) c);
            boolean space = t.content().endsWith(" ");
            if (!t.content().startsWith(playerRank))
              continue;
            Component n = modIcon.append(Component.text(space ? " " : ""));
            message.replace(i, n);
            modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
            break;
          }
      }
      else if (playerRank.startsWith("Dev")) {
        Component modIcon = Component.icon(
            Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/dev.png"))
        ).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); i++) {
          Component c = message.children().get(i);
          TextComponent t = ((TextComponent) c);
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank))
            continue;
          Component n = modIcon.append(Component.text(space ? " " : ""));
          message.replace(i, n);
          modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
          break;
        }
      }
      else if (playerRank.startsWith("Freund/in")) {
        Component modIcon = Component.icon(
            Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/teamfreund.png"))
        ).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); i++) {
          Component c = message.children().get(i);
          TextComponent t = ((TextComponent) c);
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank))
            continue;
          Component n = modIcon.append(Component.text(space ? " " : ""));
          message.replace(i, n);
          modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
          break;
        }
      }
      else if (playerRank.startsWith("VIP")) {
        Component vipIcon = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/vip.png"))
        ).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); i++) {
          Component c = message.children().get(i);
          TextComponent t = ((TextComponent) c);
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank))
            continue;
          Component n = vipIcon.append(Component.text(space ? " " : ""));
          message.replace(i, n);
          modifiedMessage = modifiedMessage.append(Component.text(" ").append(message));
          break;
        }
      } else {
        modifiedMessage = modifiedMessage.append(message);
      }
    } else {
      modifiedMessage = modifiedMessage.append(message);
    }
    event.setMessage(modifiedMessage);
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
      else if (rangName.startsWith("VIP")) {
        int index = rangName.indexOf("VIP");

        if (index != -1) {
          String foundWord = rangName.substring(index, index + "VIP".length());
          return foundWord;
        }
      }
    }
    return null;
  }


}
