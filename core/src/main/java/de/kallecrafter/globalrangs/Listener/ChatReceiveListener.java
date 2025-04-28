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
    String playerRank = getPlayerRank(event.message());
    Component message = event.message();
    Component modifiedMessage = Component.empty();
    String server = Laby.references().serverController().getCurrentStorageServerData().getName().toLowerCase();

    if (playerRank != null && ServerChecker.allowedServers.contains(server)) {
      String rankLower = playerRank.toLowerCase();
      Component icon = null;

      switch (rankLower) {
        case "owner" -> {
          String texture = server.contains("craftergang")
              ? "globalrangs:textures/rangs/ownerblue.png"
              : "globalrangs:textures/rangs/ownerred.png";
          icon = createIcon(texture);
        }
        case "admin" -> icon = createIcon("globalrangs:textures/rangs/admin.png");
        case "mod" -> icon = createIcon("globalrangs:textures/rangs/mod.png");
        case "dev" -> icon = createIcon("globalrangs:textures/rangs/dev.png");
        case "freund/in", "teamfreund" -> icon = createIcon("globalrangs:textures/rangs/teamfreund.png");
        case "vip" -> icon = createIcon("globalrangs:textures/rangs/vip.png");
        case "supremium", "supreme" -> {
          if (server.contains("gommehd")) {
            icon = createIcon("globalrangs:textures/rangs/supremium.png");
          }
        }
        case "premium" -> {
          if (server.contains("gommehd")) {
            icon = createIcon("globalrangs:textures/rangs/premium.png");
          }
        }
        case "spieler" -> {
          if (server.contains("craftergang") || server.contains("gommehd")) {
            icon = createIcon("globalrangs:textures/rangs/spieler.png");
          }
        }
        default -> {
        }
      }

      if (icon != null) {
        modifiedMessage = replacePrefixWithIcon(message, playerRank, icon);
      } else {
        modifiedMessage = message;
      }
    } else {
      modifiedMessage = message;
    }

    event.setMessage(modifiedMessage);
  }

  private Component createIcon(String fullTexturePath) {
    String[] parts = fullTexturePath.split(":", 2);
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid texture path format: " + fullTexturePath);
    }
    return Component.icon(
        Icon.texture(ResourceLocation.create(parts[0], parts[1]))
    ).setHeight(12).setWidth(22);
  }

  private Component replacePrefixWithIcon(Component message, String prefix, Component icon) {
    Component result = Component.empty();
    boolean replaced = false;
    String lowerPrefix = prefix.toLowerCase();

    for (Component c : message.children()) {
      if (replaced || !(c instanceof TextComponent t)) {
        result = result.append(c);
        continue;
      }
      String contentLower = t.content().toLowerCase();
      boolean space = t.content().endsWith(" ");
      if (contentLower.startsWith(lowerPrefix)) {
        result = result.append(icon.append(Component.text(space ? " " : "")));
        replaced = true;
      } else {
        result = result.append(c);
      }
    }
    return result;
  }

  public static String getPlayerRank(Component component) {
    if (component == null) return null;

    String text = component.toString().toLowerCase();

    String[] possibleRanks = {
        "owner", "admin", "mod", "dev", "freund/in", "teamfreund",
        "vip", "supremium", "supreme", "premium", "spieler"
    };

    for (String rank : possibleRanks) {
      if (text.contains(rank)) {
        return rank;
      }
    }

    return null;
  }
}
