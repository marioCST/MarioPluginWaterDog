package de.mariocst.wdpe.config;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.utils.config.YamlConfig;
import lombok.Getter;

public class Config {
    private final YamlConfig configuration;
    @Getter
    private final String prefix;
    @Getter
    private final String lobbyServer;
    @Getter
    private final String survivalServer;

    public Config(MarioMain marioMain, YamlConfig config) {
        this.configuration = config;

        if (this.configuration.exists("prefix")) {
            this.prefix = this.configuration.getString("prefix");
        }
        else {
            this.prefix = "§8[§6marioCST.de§8] | §f";
        }

        marioMain.setPrefix(this.prefix);

        if (this.configuration.exists("lobbyServer")) {
            this.lobbyServer = this.configuration.getString("lobbyServer");
        }
        else {
            this.lobbyServer = "lobby";
        }

        if (this.configuration.exists("survivalServer")) {
            this.survivalServer = this.configuration.getString("survivalServer");
        }
        else {
            this.survivalServer = "survival";
        }

        this.save();
    }

    public void save() {
        this.configuration.setString("prefix", this.prefix);
        this.configuration.setString("lobbyServer", this.lobbyServer);
        this.configuration.setString("survivalServer", this.survivalServer);

        this.configuration.save();
    }
}
