package de.mariocst.wdpe.listener;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.event.defaults.PlayerLoginEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class LoginListener {
    private final MarioMain marioMain;

    public LoginListener(MarioMain marioMain) {
        this.marioMain = marioMain;
    }

    public void onLogin(PlayerLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        for (ProxiedPlayer online : this.marioMain.getProxy().getPlayers().values()) {
            online.sendMessage(this.marioMain.getPrefix() + "§aDer Spieler §6" + player.getName() + " §aist dem Netzwerk beigetreten!");
        }

        this.marioMain.log("§aDer Spieler §6" + player.getName() + " §aist dem Netzwerk beigetreten!");
    }
}
