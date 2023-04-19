package me.deep.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.ArrayList;

public class SS implements CommandExecutor, Listener {

    private static ArrayList<Player> frozenPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("perm.GC")) {
                    if(args.length == 1) {
                        Player target = (Player) Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            sender.sendMessage("§cO jogador que você tentou botar em uma screenshare, não está ativo.");
                        }
                        assert target != null;
                        if (!frozenPlayers.contains(target)) {
                            frozenPlayers.add(target);
                            player.sendMessage("§c§lSS §fO jogador §a " + target.getDisplayName() + "§f está no modo §cSS§f.");
                            target.sendMessage("§c§lSS §cVocê foi colocado no modo SS. Sair do servidor resultará em um banimento permanente imediato.");
                        }
                        else {
                            frozenPlayers.remove(target);
                            player.sendMessage("§c§lSS §fVocê liberou o jogador §a" + target.getDisplayName() + "§f do modo §cSS");
                            target.sendMessage("§c§lSS §fVocê foi liberado do modo §cSS §fpelo staff §a" + player.getDisplayName());
                        }

                    }
                    else {
                        player.sendMessage("§c§lSS §fVocê utilizou o comando errado. Utilize §c/ss <player>");
                    }

                }
                else {
                    sender.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
                }

            }


        return true;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        if(frozenPlayers.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onPlayerLeft(PlayerQuitEvent event) {
        if(frozenPlayers.contains(event.getPlayer())) {
            Bukkit.getServer().broadcastMessage("§c§lSS §fO jogador " + event.getPlayer().getDisplayName() + " saiu durante a SS");
        }
    }
}
