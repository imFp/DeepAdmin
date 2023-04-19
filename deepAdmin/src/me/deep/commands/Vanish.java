package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vanish implements CommandExecutor, Listener {

    private List<UUID> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("mod.vanish")) {
                if(vanished.contains(player.getUniqueId())) {
                    vanished.remove(player.getUniqueId());
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(player);
                        sender.sendMessage("§c§lVANISH §fVocê não está mais §cinvisível§f.");
                    }

                } else {
                    vanished.add(player.getUniqueId());
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.hidePlayer(player);
                        sender.sendMessage("§c§lVANISH §fAgora você está §cinvisível§f.");
                    }
                }

            } else {
                sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            }

        }

        return true;
    }
}
