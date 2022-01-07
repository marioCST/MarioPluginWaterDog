package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WOnlinePlayersCommand extends Command {
    private final MarioMain marioMain;

    public WOnlinePlayersCommand(MarioMain marioMain) {
        super("wonlineplayers", CommandSettings.builder()
                .setDescription("Siehe, welche Spieler online sind!")
                .setAliases(new String[]{"wop"})
                .setPermission("mario.waterdog.wonlineplayers")
                .build());

        this.marioMain = marioMain;
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            if (this.marioMain.getProxy().getPlayers().values().isEmpty()) {
                sender.sendMessage(this.marioMain.getPrefix() + "Es ist kein Spieler Online!");
                return true;
            }

            sender.sendMessage(this.marioMain.getPrefix() + "Online Spieler:");

            for (ProxiedPlayer online : this.marioMain.getProxy().getPlayers().values()) {
                sender.sendMessage(this.marioMain.getPrefix() + online.getName() + " [" + online.getServerInfo().getServerName() + "]");
            }
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.hasPermission("mario.waterdog.wonlineplayers") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            player.sendMessage(this.marioMain.getPrefix() + "Online Spieler:");

            for (ProxiedPlayer online : this.marioMain.getProxy().getPlayers().values()) {
                player.sendMessage(this.marioMain.getPrefix() + online.getName() + " [" + online.getServerInfo().getServerName() + "]");
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "Keine Rechte!");
        }
        return false;
    }
}
