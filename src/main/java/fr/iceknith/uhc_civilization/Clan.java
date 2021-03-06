package fr.iceknith.uhc_civilization;

import org.bukkit.ChatColor;
import org.bukkit.Location;

/**
 * Clan assigned to each player
 */
public enum Clan {
    /**
     * The bourgeois class will have equipment from the start, but who will not be able to farm. There will be less of them
     */
    BOURGEOIS,

    /**
     * The thieves clan will be inserted to every other clan, and who will have to betray it, by stealing as much equipment as possible from their respective clans. There will be one leader of the thieves who will be much better equipped, and who will have to coordinate these troops (he will have among other things a compass that will point to the different clans).
     */
    THIEF,

    /**
     * They will be able to move on the map, according to a herd of cows which will be their only source of food
     */
    NOMADIC,

    /**
     * They will have to give their crops to the rich, and who will only have tools as equipment, their only way to equip themselves will be to hide some of their crops from the rich
     */
    FARMER,

    /**
     * The spectators will have no influence on the gameplay, they will as their name indicates spectate
     */
    SPEC;

    public ChatColor getChatColor() {
        switch (this) {
            case BOURGEOIS:
                return ChatColor.BLUE;
            case THIEF:
                return ChatColor.DARK_RED;
            case NOMADIC:
                return ChatColor.GOLD;
            case FARMER:
                return ChatColor.GREEN;
            case SPEC:
                return ChatColor.GRAY;
            default:
                return ChatColor.RESET;
        }
    }
    public String toText() {
        switch (this) {
            case BOURGEOIS:
                return "Bourgeois";
            case THIEF:
                return "Thief";
            case NOMADIC:
                return "Nomadic";
            case FARMER:
                return "Farmer";
            case SPEC:
                return "Spectator";
            default:
                return "";
        }
    }
}
