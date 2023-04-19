package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Kick implements CommandExecutor, Listener {

    public String staff = "";
    public String reason = "";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender.hasPermission("mod.kick")) {
            Player player = (Player) sender;

            if(args.length > 0) {

                for (int i = 1; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                staff = ((Player) sender).getDisplayName();

                if (Bukkit.getOfflinePlayer(args[0]) != null) {

                    Player target = Bukkit.getPlayer(args[0]);
                    target.kickPlayer("§a§lDEEP SERVER \n \n §cVocê foi §lkickado §cdo nosso servidor. \n §cMotivo: §f" + reason + "\n §cPor: §f"+ staff);

                } else  {
                    sender.sendMessage("§c§lPUNISH §cO jogador não existe.");
                }

            } else {
                player.sendMessage("§c§lPUNISH §fVocê utilizou o comando errado. Utilize §c/kick <player> <motivo>");
            }
        } else {
            sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
        }



        return true;
    }
}
