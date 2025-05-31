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

import java.util.List;

public class ChatReceiveListener {

  private final GlobalRangsMain addon;

  public ChatReceiveListener(GlobalRangsMain addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    Component message = event.message();
    String playerRank = getPlayerRank(message);
    if (playerRank == null) return;

    var serverData = Laby.references().serverController().getCurrentStorageServerData();
    if (serverData == null || serverData.getName() == null) return;

    String server = serverData.getName().toLowerCase();
    if (!ServerChecker.allowedServers.contains(server)) return;

    Component icon = null;
    String rankLower = playerRank.toLowerCase();

    switch (rankLower) {
      case "owner":
        icon = createIcon(server.contains("craftergang")
            ? "globalrangs:textures/rangs/ownerblue.png"
            : "globalrangs:textures/rangs/ownerred.png");
        break;
      case "admin":
        icon = createIcon("globalrangs:textures/rangs/admin.png");
        break;
      case "mod":
        icon = createIcon("globalrangs:textures/rangs/mod.png");
        break;
      case "dev":
        icon = createIcon("globalrangs:textures/rangs/dev.png");
        break;
      case "freund/in":
      case "teamfreund":
        icon = createIcon("globalrangs:textures/rangs/teamfreund.png");
        break;
      case "vip":
        icon = createIcon("globalrangs:textures/rangs/vip.png");
        break;
      case "supremium":
      case "supreme":
        if (server.contains("gommehd")) {
          icon = createIcon("globalrangs:textures/rangs/supremium.png");
        }
        break;
      case "premium":
        if (server.contains("gommehd")) {
          icon = createIcon("globalrangs:textures/rangs/premium.png");
        }
        break;
      case "spieler":
        if (server.contains("craftergang") || server.contains("gommehd")) {
          icon = createIcon("globalrangs:textures/rangs/spieler.png");
        }
        break;
      default:
        break;
    }

    if (icon != null) {
      Component modifiedMessage = replaceRankWithIcon(message, playerRank, icon);
      event.setMessage(modifiedMessage);
    }
  }

  private Component createIcon(String fullTexturePath) {
    String[] parts = fullTexturePath.split(":", 2);
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid texture path format: " + fullTexturePath);
    }
    return Component.icon(Icon.texture(ResourceLocation.create(parts[0], parts[1])))
        .setHeight(12)
        .setWidth(22);
  }

  private Component replaceRankWithIcon(Component message, String rank, Component icon) {
    String rankLower = rank.toLowerCase();
    Component result = Component.empty();
    boolean replaced = false;

    for (Component child : message.children()) {
      if (replaced || !(child instanceof TextComponent text)) {
        result = result.append(child);
        continue;
      }

      String contentLower = text.content().toLowerCase();
      boolean endsWithSpace = text.content().endsWith(" ");

      if (contentLower.startsWith(rankLower)) {
        result = result.append(icon.append(Component.text(endsWithSpace ? " " : "")));
        replaced = true;
      } else {
        result = result.append(child);
      }
    }

    return result;
  }

  public static String getPlayerRank(Component component) {
    if (component == null) return null;

    String text = PlainTextComponentSerializer.plainText().serialize(component).toLowerCase();

    List<String> possibleRanks = List.of(
        "owner", "admin", "mod", "dev", "freund/in", "teamfreund",
        "vip", "supremium", "supreme", "premium", "spieler"
    );

    for (String rank : possibleRanks) {
      if (text.contains(rank)) {
        return rank;
      }
    }

    return null;
  }
}
