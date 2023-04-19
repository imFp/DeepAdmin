package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import static me.deep.commands.Ban.bannedPlayers;

public class Unban implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender.hasPermission("mod.unban")) {
            Player player = (Player) sender;

            if(args.length > 0 ) {

                if (Bukkit.getOfflinePlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if(bannedPlayers.containsKey(target.getUniqueId())) {
                            bannedPlayers.remove(target.getUniqueId());
                            player.sendMessage("§c§lPUNISH §fO jogador §a" + target.getDisplayName() + "§f foi desbanido com sucesso.");
                        } else {
                            player.sendMessage("§c§lPUNISH §fO jogador não está banido.");
                        }
                    } else {
                        player.sendMessage("§c§lPUNISH §fO jogador não está banido.");
                    }
                } else {
                    player.sendMessage("§c§lPUNISH §fO jogador não está banido.");
                }
            } else {
                player.sendMessage("§c§lPUNISH §fVocê utilizou o comando errado. Utilize §c/unban <player>");
            }
        } else {
            sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
        }

        return true;
    }
}
