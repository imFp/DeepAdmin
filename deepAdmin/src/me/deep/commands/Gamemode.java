package me.deep.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("mod.gamemode")) {
                if(args.length >= 1) {
                    String mode = args[0];

                    if(mode.equals("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§a§lGM §fAgora você está no modo §b§lCRIATIVO");
                    } else if (mode.equals("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§a§lGM §fAgora você está no modo §b§lSURVIVAL");
                    } else if (mode.equals("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§a§lGM §fAgora você está no modo §b§lSPECTATOR");
                    } else if (mode.equals("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§a§lGM §fAgora você está no modo §b§lADVENTURE");
                    }
                } else {
                    player.sendMessage("§a§lGM §fVocê utilizou o comando errado. §cUtilize /gm <modo>");
                }
            } else {
                player.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            }

        }

        return true;
    }
}
