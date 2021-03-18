package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public final class Main extends JavaPlugin {

    /**
     * The duration of the game
     */
    public static int Length;

    /**
     * The amount of players each team
     */
    public static Map<Clan, Integer> playerCount = new HashMap<>();

    /**
     * The clan associated to each player
     */
    public static Map<Player, Clan> playerDistribution = new HashMap<>();

    /**
     * The location of every base associated with the clan it belongs to
     */
    public static Map<Clan, Location> baseLoc = new HashMap<>();

    /**
     * weather the game has started or not
     */
    public static boolean isStarted = false;

    /**
     * Get the required amount of players based on what is set up with the /civ team
     * @return The required amount of players for this game
     */
    public static int totalPlayers() {
        int result = 0;
        for(Clan sc : playerCount.keySet()){
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

        //reading the config file
        File config = new File("config.properties");
        try{
            FileReader reader = new FileReader(config);
            Properties props = new Properties();
            props.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called at the shutdown of the plugin
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
