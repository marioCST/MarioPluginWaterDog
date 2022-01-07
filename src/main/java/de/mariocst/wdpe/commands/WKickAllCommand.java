package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WKickAllCommand extends Command {
    private final MarioMain marioMain;

    public WKickAllCommand(MarioMain marioMain) {
        super("wkickall", CommandSettings.builder()
                .setDescription("Kicke alle Spieler, die mit dem Proxy verbunden sind!")
                .setAliases(new String[]{"wka"})
                .setPermission("mario.waterdog.wkickall")
                .build());

        this.marioMain = marioMain;
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            for (ProxiedPlayer t : this.marioMain.getProxy().getPlayers().values()) {
                if (t.hasPermission("mario.waterdog.wkickall.bypass") || t.hasPermission("mario.waterdog.*") || t.hasPermission("*") || t.isAdmin()) continue;

                if (args.length == 0) {
                    t.disconnect("Kicked by Admin");
                }
                else {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    t.disconnect(msg.toString().replaceAll("&", "§"));
                }
            }
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.hasPermission("mario.waterdog.wkickall") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            for (ProxiedPlayer t : this.marioMain.getProxy().getPlayers().values()) {
                if (t == player || t.hasPermission("mario.waterdog.wkickall.bypass") || t.hasPermission("mario.waterdog.*") || t.hasPermission("*") || t.isAdmin()) continue;

                if (args.length == 0) {
                    t.disconnect("Kicked by Admin");
                }
                else {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    t.disconnect(msg.toString().replaceAll("&", "§"));
                }
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "/kickall <Nachricht>");
        }
        return false;
    }
}
