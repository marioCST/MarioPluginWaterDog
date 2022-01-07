package de.mariocst.wdpe.listener;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.event.defaults.PlayerDisconnectEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class DisconnectListener {
    private final MarioMain marioMain;

    public DisconnectListener(MarioMain marioMain) {
        this.marioMain = marioMain;
    }

    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        for (ProxiedPlayer online : this.marioMain.getProxy().getPlayers().values()) {
            online.sendMessage(this.marioMain.getPrefix() + "§cDer Spieler §6" + player.getName() + " §chat das Netzwerk verlassen!");
        }

        this.marioMain.log("§cDer Spieler §6" + player.getName() + " §chat das Netzwerk verlassen!");
    }
}
