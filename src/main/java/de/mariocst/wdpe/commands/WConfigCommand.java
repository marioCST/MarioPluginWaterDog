package de.mariocst.wdpe.commands;

import de.mariocst.wdpe.MarioMain;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WConfigCommand extends Command {
    private final MarioMain marioMain;

    public WConfigCommand(MarioMain marioMain) {
        super("wconfig", CommandSettings.builder()
                .setDescription("Speichere die Config oder lade sie neu!")
                .setAliases(new String[]{"wc"})
                .setPermission("mario.waterdog.config")
                .build());

        this.marioMain = marioMain;
    }

    @Override
    public boolean onExecute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "save": {
                        this.marioMain.saveConfiguration();
                        sender.sendMessage(this.marioMain.getPrefix() + "Configs gespeichert!");
                        break;
                    }
                    case "reload": {
                        this.marioMain.loadConfiguration();
                        sender.sendMessage(this.marioMain.getPrefix() + "Configs neu geladen!");
                        break;
                    }
                    default: {
                        sender.sendMessage(this.marioMain.getPrefix() + "/config <save|reload>");
                        break;
                    }
                }
            }
            else {
                sender.sendMessage(this.marioMain.getPrefix() + "/config <save|reload>");
            }
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.hasPermission("mario.waterdog.wconfig") || player.hasPermission("mario.waterdog.*") || player.hasPermission("*") || player.isAdmin()) {
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "save": {
                        this.marioMain.saveConfiguration();
                        player.sendMessage(this.marioMain.getPrefix() + "Configs gespeichert!");
                        break;
                    }
                    case "reload": {
                        this.marioMain.loadConfiguration();
                        player.sendMessage(this.marioMain.getPrefix() + "Configs neu geladen!");
                        break;
                    }
                    default: {
                        player.sendMessage(this.marioMain.getPrefix() + "/config <save|reload>");
                        break;
                    }
                }
            }
            else {
                player.sendMessage(this.marioMain.getPrefix() + "/config <save|reload>");
            }
        }
        else {
            player.sendMessage(this.marioMain.getPrefix() + "Keine Rechte!");
        }
        return false;
    }
}
