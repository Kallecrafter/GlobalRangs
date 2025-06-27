package de.kallecrafter.globalrangs.Listener;

import de.kallecrafter.globalrangs.GlobalRangsMain;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.IconComponent;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class ChatReceiveListener {
  private final GlobalRangsMain addon;

  public ChatReceiveListener(GlobalRangsMain addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    Component component1 = null;
    String playerRank = getPlayerrank(event.message());
    Component message = event.message();
    TextComponent textComponent = Component.empty();
    IconComponent iconComponent1 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/grdesigner.png"))).setHeight(12).setWidth(22);
    IconComponent iconComponent2 = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/grdev.png"))).setHeight(12).setWidth(22);
    if (playerRank != null) {
      if (playerRank.equals("Owner")) {
        IconComponent iconComponent;
        if (!Laby.references().serverController().getCurrentStorageServerData().getName().toString().toLowerCase().contains("craftergang")) {
          iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerred.png"))).setHeight(12).setWidth(22);
        } else {
          iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/ownerblue.png"))).setHeight(12).setWidth(22);
        }
        for (int i = 0; i < message.children().size(); ) {
          Component c = message.children().get(i);
          TextComponent t = (TextComponent)c;
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank)) {
            i++;
            continue;
          }
          Component n = iconComponent.append((Component)Component.text(space ? " " : ""));
          message.replace(i, n);
          component1 = textComponent.append(Component.text(" ").append(message));
        }
      } else if (playerRank.startsWith("Admin")) {
        IconComponent iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/admin.png"))).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); ) {
          Component c = message.children().get(i);
          TextComponent t = (TextComponent)c;
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank)) {
            i++;
            continue;
          }
          Component n = iconComponent.append((Component)Component.text(space ? " " : ""));
          message.replace(i, n);
          component1 = component1.append(Component.text(" ").append(message));
        }
      } else if (playerRank.startsWith("Mod")) {
        IconComponent iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/mod.png"))).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); ) {
          Component c = message.children().get(i);
          TextComponent t = (TextComponent)c;
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank)) {
            i++;
            continue;
          }
          Component n = iconComponent.append((Component)Component.text(space ? " " : ""));
          message.replace(i, n);
          component1 = component1.append(Component.text(" ").append(message));
        }
      } else if (playerRank.startsWith("VIP")) {
        IconComponent iconComponent = Component.icon(Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/vip.png"))).setHeight(12).setWidth(22);
        for (int i = 0; i < message.children().size(); ) {
          Component c = message.children().get(i);
          TextComponent t = (TextComponent)c;
          boolean space = t.content().endsWith(" ");
          if (!t.content().startsWith(playerRank)) {
            i++;
            continue;
          }
          Component n = iconComponent.append((Component)Component.text(space ? " " : ""));
          message.replace(i, n);
          component1 = component1.append(Component.text(" ").append(message));
        }
      } else {
        component1 = component1.append(message);
      }
    } else {
      component1 = component1.append(message);
    }
    event.setMessage(component1);
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
