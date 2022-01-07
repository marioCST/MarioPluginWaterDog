package de.mariocst.wdpe;

import de.mariocst.wdpe.commands.*;
import de.mariocst.wdpe.config.Config;
import de.mariocst.wdpe.listener.DisconnectListener;
import de.mariocst.wdpe.listener.LoginListener;
import dev.waterdog.waterdogpe.command.CommandMap;
import dev.waterdog.waterdogpe.event.defaults.PlayerDisconnectEvent;
import dev.waterdog.waterdogpe.event.defaults.PlayerLoginEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;

public class MarioMain extends Plugin {
    private String prefix;

    private Config config;

    @Override
    public void onEnable() {
        this.register();

        this.loadConfiguration();

        this.log("MarioPlugin WaterDog aktiviert!");
    }

    @Override
    public void onDisable() {
        this.saveConfiguration();

        this.log("MarioPlugin WaterDog deaktiviert!");
    }

    public void log(String msg) {
        this.getProxy().getLogger().info(this.prefix + msg);
    }

    public void loadConfiguration() {
        YamlConfig c = new YamlConfig(this.getDataFolder() + "/config.yml");
        this.config = new Config(this, c);
    }

    public void saveConfiguration() {
        this.config.save();
    }

    private void register() {
        CommandMap map = this.getProxy().getCommandMap();

        map.registerCommand(new WBroadcastCommand(this));
        map.registerCommand(new WConfigCommand(this));
        map.registerCommand(new WKickAllCommand(this));
        map.registerCommand(new WLobbyCommand(this));
        map.registerCommand(new WOnlinePlayersCommand(this));
        map.registerCommand(new WSurvivalCommand(this));

        DisconnectListener disconnectListener = new DisconnectListener(this);
        LoginListener loginListener = new LoginListener(this);

        this.getProxy().getEventManager().subscribe(PlayerDisconnectEvent.class, disconnectListener::onDisconnect);
        this.getProxy().getEventManager().subscribe(PlayerLoginEvent.class, loginListener::onLogin);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Config getConfiguration() {
        return config;
    }
}
