package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WLobbyCommand extends Command {
    private final MarioMain marioMain;

    public WLobbyCommand(MarioMain marioMain) {
        super("wlobby", CommandSettings.builder()
                .setDescription("Verbinde dich mit den Lobby Server!")
                .setAliases(new String[]{"wl"})
                .setPermission("mario.waterdog.wlobby")
                .build());

        this.marioMain = marioMain;
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(this.marioMain.getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.hasPermission("mario.waterdog.wlobby") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            if (player.getServerInfo().getServerName().equalsIgnoreCase(this.marioMain.getConfiguration().getLobbyServer())) {
                player.sendMessage(this.marioMain.getPrefix() + "Du bist bereits mit der Lobby verbunden!");
            }
            else {
                ServerInfo lobby = this.marioMain.getProxy().getServerInfo(this.marioMain.getConfiguration().getLobbyServer());
                player.connect(lobby);
                player.sendMessage(this.marioMain.getPrefix() + "Du wurdest nun mit der Lobby verbunden!");
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "Keine Rechte!");
        }
        return false;
    }
}
