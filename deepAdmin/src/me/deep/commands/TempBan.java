package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static me.deep.commands.Ban.bannedPlayers;

public class TempBan implements CommandExecutor, Listener {

    public String staff;
    public String reason;
    public String time;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender.hasPermission("mod.tempban")) {
            Player player = (Player) sender;

            if(args.length > 2) {
                staff = sender.getName();
                reason = "";
                time = args[1];
                for (int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                if (Bukkit.getOfflinePlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(!bannedPlayers.containsKey(target.getUniqueId())) {
                        bannedPlayers.put(target.getUniqueId(), reason);
                        target.kickPlayer("§a§lDEEP SERVER \n \n §cVocê foi §lbanido §ctemporariamente do nosso servidor. \n §cMotivo: §f" + reason + "\n §cTempo: §f" + time + "\n §cPor: §f"+ staff + "\n \n §cAcha que foi banido §lincorretamente? \n §cFaça um §b§lappeal §cem nosso discord.");
                        sender.sendMessage("§c§lPUNISH §fO jogador " + target.getDisplayName() + " foi banido do servidor.");
                    }
                } else  {
                    sender.sendMessage("§cO jogador não existe.");
                }
            }
        } else {
            sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
        }

        return true;
    }
}
