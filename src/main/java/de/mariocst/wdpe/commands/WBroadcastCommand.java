package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WBroadcastCommand extends Command {
    private final MarioMain marioMain;

    public WBroadcastCommand(MarioMain marioMain) {
        super("wbroadcast", CommandSettings.builder()
                .setDescription("Schicke eine Nachricht an alle Spieler, die mit dem Proxy verbunden sind!")
                .setAliases(new String[]{"wbc"})
                .setPermission("mario.waterdog.wbroadcast")
                .build());

        this.marioMain = marioMain;
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                for (ProxiedPlayer proxiedPlayer : this.marioMain.getProxy().getPlayers().values()) {
                    proxiedPlayer.sendMessage(this.marioMain.getPrefix() + msg.toString().replaceAll("&", "§"));
                }

                sender.sendMessage(this.marioMain.getPrefix() + msg.toString().replaceAll("&", "§"));
            }
            else {
                sender.sendMessage(this.marioMain.getPrefix() + "/wbroadcast <Nachricht>");
            }
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.hasPermission("mario.waterdog.wbroadcast") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                for (ProxiedPlayer proxiedPlayer : this.marioMain.getProxy().getPlayers().values()) {
                    proxiedPlayer.sendMessage(this.marioMain.getPrefix() + msg.toString().replaceAll("&", "§"));
                }

                this.marioMain.log(msg.toString().replaceAll("&", "§"));
            }
            else {
                player.sendMessage(this.marioMain.getPrefix() + "/wbroadcast <Nachricht>");
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "Keine Rechte!");
        }
        return false;
    }
}
