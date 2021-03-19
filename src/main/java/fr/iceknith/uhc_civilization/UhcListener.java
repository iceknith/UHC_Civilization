package fr.iceknith.uhc_civilization;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * The listener class used to listen for events
 */
public class UhcListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent evt){
        if(Main.isStarted) {
            evt.setDeathMessage(evt.getEntity().getDisplayName() + ChatColor.GRAY+" died");
        }
    }
}
