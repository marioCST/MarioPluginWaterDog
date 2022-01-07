package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WSurvivalCommand extends Command {
    private final MarioMain marioMain;

    public WSurvivalCommand(MarioMain marioMain) {
        super("wsurvival", CommandSettings.builder()
                .setDescription("Verbinde dich mit den Survival Server!")
                .setAliases(new String[]{"ws"})
                .setPermission("mario.waterdog.wsurvival")
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

        if (player.hasPermission("mario.waterdog.wsurvival") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            if (player.getServerInfo().getServerName().equalsIgnoreCase(this.marioMain.getConfiguration().getSurvivalServer())) {
                player.sendMessage(this.marioMain.getPrefix() + "Du bist bereits mit der Survival verbunden!");
            }
            else {
                ServerInfo survival = this.marioMain.getProxy().getServerInfo(this.marioMain.getConfiguration().getSurvivalServer());
                player.connect(survival);
                player.sendMessage(this.marioMain.getPrefix() + "Du wurdest nun mit der Survival verbunden!");
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "Keine Rechte!");
        }
        return false;
    }
}
