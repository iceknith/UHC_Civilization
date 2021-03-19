package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static JavaPlugin instance;
    /**
     * The duration of the game
     */
    public static int Length;

    public static Timer timer;
    public static boolean isStarted = false;
    /**
     * The amount of players each team
     */
    public static Map<Clan, Integer> playerCount = new HashMap<>();

    /**
     * The social class associated to each player
     */
    public static Map<Player, Clan> playerdistribution = new HashMap<>();

    /**
     * Get the required amount of players based on what is set up with the /civ team
     * @return The required amount of players for this game
     */
    public static int totalPlayers() {
        int result = 0;
        for (Clan clan : playerCount.keySet()) {
            result += playerCount.get(clan);
        }
        return result;
    }

    /**
     * Method called at the start of the plugin
     */
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Objects.requireNonNull(getCommand("civ")).setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new UhcListener(), this);
    }

    /**
     * Method called at the shutdown of the plugin
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
