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
import java.awt.*;

public class NameTagListener {

  private final GlobalRangsMain addon;

  public NameTagListener(GlobalRangsMain addon) {
    this.addon = addon;
  }



  @Subscribe
  public void onNameTagReceive(PlayerNameTagRenderEvent event) {
    Component prefix = event.getPlayerInfo().getTeam().getPrefix();
    String playerRank = getPlayerRank(prefix);
    String server = Laby.references().serverController().getCurrentStorageServerData().getName().toLowerCase();
    if (playerRank == null || !ServerChecker.allowedServers.contains(server)) {
      return;
    }
    Component icon = getRankIcon(playerRank, server);
    if (icon == null) return;

    event.setNameTag(icon.append(Component.text("§7" + event.getPlayerInfo().profile().getUsername())));
  }

  private Component getRankIcon(String playerRank, String server) {
    String rank = playerRank.toLowerCase();

    if (rank.equals("owner")) {
      String texture = server.contains("craftergang") ? "ownerblue.png" : "ownerred.png";
      return createIcon(texture, 8, 18);
    } else if (rank.startsWith("admin")) {
      return createIcon("admin.png", 8, 18);
    } else if (rank.startsWith("mod")) {
      return createIcon("mod.png", 8, 18);
    } else if (rank.startsWith("dev")) {
      return createIcon("dev.png", 8, 18);
    } else if (rank.startsWith("teamfreund") || rank.startsWith("tf")) {
      return createIcon("teamfreund.png", 12, 22);
    } else if (rank.startsWith("vip") && server.contains("gommehd")) {
      return createIcon("vip.png", 12, 22);
    } else if (rank.startsWith("build") || rank.startsWith("builder")) {
      return createIcon("builder.png", 12, 22);
    } else if ((rank.startsWith("supremium") || rank.startsWith("supreme") || rank.startsWith("§b")) && server.contains("gommehd")) {
      return createIcon("supremium.png", 12, 22);
    } else if ((rank.startsWith("premium") || rank.startsWith("§6")) && server.contains("gommehd")) {
      return createIcon("supremium.png", 12, 22);
    } else if ((rank.equals("s") && server.contains("craftergang")) || (rank.startsWith(Color.GREEN.toString()) && server.contains("gommehd"))) {
      return createIcon("spieler.png", 12, 22);
    }

    return null;
  }

  private Component createIcon(String textureName, int height, int width) {
    return Component.icon(
        Icon.texture(ResourceLocation.create("globalrangs", "textures/rangs/" + textureName))
    ).setHeight(height).setWidth(width);
  }

  private String getPlayerRank(Component prefix) {
    if (prefix == null) return null;

    String raw = prefix.toString().toLowerCase();

    String[] knownRanks = {
        "owner", "admin", "mod", "dev", "teamfreund", "teamfreund+", "tf", "tf+",
        "vip", "build", "builder", "supremium", "supreme", "premium",
        "§b", "§6", "§a", "s", "spieler"
    };

    for (String rank : knownRanks) {
      if (raw.contains(rank)) {
        return rank;
      }
    }

    return null;
  }



}
