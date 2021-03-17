package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    /**
     * The duration of the game
     */
    public static int Length;

    /**
     * The amount of players each team
     */
    public static Map<SocialClass, Integer> playerCount = new HashMap<>();

    public static Map<Player, SocialClass> playerdistribution = new HashMap<>();

    public static int totalPlayersFromMap() {
        int result = 0;
        for(SocialClass sc : playerCount.keySet()){
            result += playerCount.get(sc);
        }
        return result;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("civ").setExecutor(new Commands());
        Bukkit.getServer().getPluginManager().registerEvents(new UhcListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
