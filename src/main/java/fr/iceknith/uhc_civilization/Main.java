package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {

    /**
     * The duration of the game
     */
    public static int Length;

    /**
     * The amount of players each team
     */
    public static Map<SocialClass, Integer> playerCount = new HashMap<>();

    /**
     * The social class associated to each player
     */
    public static Map<Player, SocialClass> playerdistribution = new HashMap<>();

    /**
     * Get the required amount of players based on what is set up with the /civ team
     * @return The required amount of players for this game
     */
    public static int totalPlayers() {
        int result = 0;
        for(SocialClass sc : playerCount.keySet()){
            result += playerCount.get(sc);
        }
        return result;
    }

    /**
     * Method called at the start of the plugin
     */
    @Override
    public void onEnable(){
        // Plugin startup logic
        Objects.requireNonNull(getCommand("civ")).setExecutor(new Commands());
        Bukkit.getServer().getPluginManager().registerEvents(new UhcListener(), this);
    }

    /**
     * Method called at the shutdown of the plugin
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
