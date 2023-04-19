package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.HashMap;
import java.util.UUID;

public class Ban implements CommandExecutor, Listener {

    public String staff;
    public String reason;
    public String proof = "";

    public static HashMap<UUID, String> bannedPlayers = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {

        if(sender.hasPermission("mod.ban")) {
            Player player = (Player) sender;

            if (args.length > 1) {

                staff = sender.getName();
                reason = "";
                for (int i = 1; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                if (Bukkit.getOfflinePlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        bannedPlayers.put(target.getUniqueId(), reason);
                        target.kickPlayer("§a§lDEEP SERVER \n \n §cVocê foi §lbanido §cdo nosso servidor. \n §cMotivo: §f" + bannedPlayers.get(target.getUniqueId()) + "\n \n §cAcha que foi banido §lincorretamente? \n §cFaça um §b§lappeal §cem nosso discord.");
                        sender.sendMessage("§c§lPUNISH §fO jogador " + target.getDisplayName() + " foi banido do servidor.");
                    } else {
                        sender.sendMessage("§c§lPUNISH §fO jogador não existe.");
                    }
                } else  {
                    sender.sendMessage("§c§lPUNISH §fO jogador não existe.");
                }

            } else {
                player.sendMessage("§c§lPUNISH §fVocê utilizou o comando errado. Utilize §c/ban <player> <motivo>");
            }
        } else {
            sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
        }

        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(bannedPlayers.containsKey(player.getUniqueId())) {
            e.getPlayer().kickPlayer("§a§lDEEP SERVER \n \n §cVocê foi banido do nosso servidor. \n §cMotivo: §f" + bannedPlayers.get(e.getPlayer().getUniqueId()) + "\n \n §cAcha que foi banido incorretamente? \n §cFaça um §b§lappeal em nosso discord.");
        } else {

        }
    }
}
