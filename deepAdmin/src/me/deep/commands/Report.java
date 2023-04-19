package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length >= 2) {
                String reason = "";

                for (int i = 1; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        player.sendMessage("§c§lREPORT §fA sua denúncia pelo jogador " + target.getDisplayName() + " foi enviada com sucesso.");

                        for(Player player1 : Bukkit.getOnlinePlayers()) {
                            if(player1.hasPermission("mod.reportCheck")) {
                                player1.sendMessage("§a \n     §c§lREPORT  §c\n§a \n §fAcusado: §7" + target.getDisplayName() + "\n §fAcusador: §7" + player.getDisplayName() + "\n §fMotivo: §7" + reason + "\n §f");
                            }
                        }
                    } else {
                        player.sendMessage("§c§lREPORT §fO jogador não está online.");
                    }
                } else {
                    player.sendMessage("§c§lREPORT §fO jogador não está online.");
                }
            } else {
                player.sendMessage("§c§lREPORT §fVocê utilizou o comando errado. §cUtilize /report <player> <motivo>");
            }
        }

        return true;
    }
}
