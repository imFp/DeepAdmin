package me.deep;

import me.deep.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getCommand("SS").setExecutor(new SS());
        getCommand("V").setExecutor(new Vanish());
        getCommand("Vanish").setExecutor(new Vanish());
        getCommand("Kick").setExecutor(new Kick());
        getCommand("TempBan").setExecutor(new TempBan());
        getCommand("Ban").setExecutor(new Ban());
        getCommand("Unban").setExecutor(new Unban());
        getCommand("Gm").setExecutor(new Gamemode());
        getCommand("Gamemode").setExecutor(new Gamemode());
        getCommand("Report").setExecutor(new Report());
        getCommand("Fly").setExecutor(new Fly());

        Bukkit.getPluginManager().registerEvents(new Ban(), this);
        Bukkit.getPluginManager().registerEvents(new SS(), this);

        Bukkit.getConsoleSender().sendMessage("§aO plugin §lDeepAdmin §afoi carregado com sucesso.");
    }
    
}
