package me.deep.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly implements CommandExecutor {

    private static ArrayList<Player> flyingPlayers = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("mod.fly")) {
                if(!flyingPlayers.contains(player)) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    flyingPlayers.add(player);
                    player.sendMessage("§a§lFLY §fVocê agora pode voar.");
                }  else {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    flyingPlayers.remove(player);
                    player.sendMessage("§a§lFLY §fVocê agora não pode mais voar.");
                }
            } else {
                player.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            }
        }

        return true;
    }
}
