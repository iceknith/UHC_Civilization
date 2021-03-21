package fr.iceknith.uhc_civilization;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
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

    /**
     * The timer is used to hold and manage all events related to time
     */
    public static Timer timer;
    /**
     * Whether the game has started or not
     */
    public static boolean isStarted;
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

        FileConfiguration config = this.getConfig();
        this.saveDefaultConfig();
        baseLoc.put(Clan.BOURGEOIS, new Location(getServer().getWorlds().get(0), config.getInt("bourgeoisBase.x"),
                config.getInt("bourgeoisBase.y"), config.getInt("bourgeoisBase.z")));
        baseLoc.put(Clan.FARMER, new Location(getServer().getWorlds().get(0), config.getInt("farmerBase.x"),
                config.getInt("farmerBase.y"), config.getInt("farmerBase.z")));
        baseLoc.put(Clan.THIEF, new Location(getServer().getWorlds().get(0), config.getInt("thiefBase.x"),
                config.getInt("thiefBase.y"), config.getInt("thiefBase.z")));
        baseLoc.put(Clan.NOMADIC, new Location(getServer().getWorlds().get(0), config.getInt("nomadicBase.x"),
                config.getInt("nomadicBase.y"), config.getInt("nomadicBase.z")));
        baseLoc.put(Clan.SPEC, new Location(getServer().getWorlds().get(0), config.getInt("specBase.x"),
                config.getInt("specBase.y"), config.getInt("specBase.z")));
    }

    /**
     * Method called at the shutdown of the plugin
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
