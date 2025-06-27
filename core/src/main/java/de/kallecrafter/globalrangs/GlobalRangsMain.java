package de.kallecrafter.globalrangs;

import de.kallecrafter.globalrangs.Listener.ChatReceiveListener;
import de.kallecrafter.globalrangs.Listener.NameTagListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class GlobalRangsMain extends LabyAddon<Config> {

  private static GlobalRangsMain instance;

  @Override
  protected void enable() {
    instance = this;
    this.registerSettingCategory();
    this.registerListener(new ChatReceiveListener(instance));
    this.registerListener(new NameTagListener(instance));
    this.labyAPI().minecraft().chatExecutor().displayClientMessage(
        Component.text()
            .append(Component.text("[", NamedTextColor.GRAY))
            .append(Component.text("GlobalRanks", NamedTextColor.GOLD))
            .append(Component.text("] ", NamedTextColor.GRAY))
            .append(Component.text("Addon geladen. Ränge werden auf unterstützten Servern angezeigt.", NamedTextColor.GREEN))
            .build()
    );
  }


  private Config loadConfig() {
    return configuration();
  }

  @Override
  protected Class<? extends Config> configurationClass() {
    return Config.class;
  }

  public static GlobalRangsMain getInstance() {
    return instance;
  }



}
