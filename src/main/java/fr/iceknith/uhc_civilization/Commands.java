package fr.iceknith.uhc_civilization;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            if (args.length >= 1) {
                //determine the length
                switch (args[0]) {
                    case "length":
                        Main.Length = Integer.parseInt(args[1]);
                        commandSender.sendMessage("The length has been set to " + Main.Length + " minutes");
                        break;

                    //determine the proportion of player per team
                    case "team":
                        if (args.length >= 2) {
                            Clan clan;
                            switch (args[1]) {
                                case "bourgeois":
                                    clan = Clan.BOURGEOIS;
                                    break;
                                case "nomadic":
                                    clan = Clan.NOMADIC;
                                    break;
                                case "farmer":
                                    clan = Clan.FARMER;
                                    break;
                                case "thief":
                                    clan = Clan.THIEF;
                                    break;
                                case "spectator":
                                    clan = Clan.SPEC;
                                    break;
                                default:
                                    commandSender.sendMessage("Couldn't find the '" + args[1] + "' class, please try again");
                                    return true;
                            }
                            Main.playerCount.put(clan, Integer.parseInt(args[2]));
                            printPlayerCount(commandSender);
                        }
                        break;

                    //Start the game
                    case "start":
                        if (commandSender instanceof Player) {
                            int playerNum = ((Player) commandSender).getWorld().getPlayers().size();
                            if (playerNum == Main.totalPlayers()) {
                                List<Player> players = ((Player) commandSender).getWorld().getPlayers();
                                Collections.shuffle(players);
                                //Scoreboard s = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
                                //Scoreboard s = sm.getNewScoreboard();
                                //s.registerNewTeam(Clan.BOURGEOIS.toString()).setPrefix(ChatColor.BLUE + "");
                                //s.registerNewTeam(Clan.FARMER.toString()).setPrefix(ChatColor.GREEN + "");
                                //s.registerNewTeam(Clan.NOMADIC.toString()).setPrefix(ChatColor.GOLD + "");
                                //s.registerNewTeam(Clan.THIEF.toString()).setPrefix(ChatColor.BLACK + "");
                                //s.registerNewTeam(Clan.SPEC.toString()).setPrefix(ChatColor.GRAY + "");
                                for (Clan clan : Main.playerCount.keySet()) {
                                    for (int i = 0; i < Main.playerCount.get(clan); i++) {
                                        Main.playerdistribution.put(players.get(0), clan);
                                        //Objects.requireNonNull(s.getTeam(String.valueOf(clan))).addEntry(String.valueOf(players.get(0)));
                                        players.get(0).setDisplayName(ChatColor.BLUE+players.get(0).getName());
                                        players.remove(0);
                                    }
                                }
                                printPlayerDispersion(commandSender);
                                //starting the game ...
                            } else {
                                commandSender.sendMessage("The amount of online players needs to correspond to the total amount of players you assigned to each team");
                            }

                        }
                        break;
                    default:
                        commandSender.sendMessage("couldn't find the specific argument you are looking for");
                }
            }

        } catch (
                ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /civ <args>");
        } catch (
                Exception e) {
            commandSender.sendMessage("An error occurred, see console");
            e.printStackTrace();
        }
        return true;

    }

    private void printPlayerDispersion(CommandSender sender) {
        sender.sendMessage("Team player teams:");
        for (Player player : Main.playerdistribution.keySet()) {
            sender.sendMessage(player.getDisplayName() + "\t:\t" + Main.playerdistribution.get(player));
        }
    }

    private void printPlayerCount(CommandSender sender) {
        sender.sendMessage("Team player counts:");
        for (Clan sClass : Main.playerCount.keySet()) {
            sender.sendMessage(sClass.toString() + "\t:\t" + Main.playerCount.get(sClass));
        }
    }


}
