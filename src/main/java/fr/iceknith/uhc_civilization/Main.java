package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    //the length of the game
    public static int Length;

    //the amount of player in the Bourgeois team
    public static int playerBourgeoisNum;
     public static List<Player> bourgeois = new ArrayList<Player>();

    //the amount of player in the Nomadic team
    public static int playerNomadicNum;
    public static List<Player> nomadic = new ArrayList<Player>();

    //the amount of player in the Thief team
    public static int playerThiefNum;
    public static List<Player> thief = new ArrayList<Player>();

    //the amount of player in the Farmer team
    public static int playerFarmerNum;
    public static List<Player> farmer = new ArrayList<Player>();

    //the amount of spectator
    public static int playerSpecNum = 0;
    public static List<Player> spec = new ArrayList<Player>();



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
