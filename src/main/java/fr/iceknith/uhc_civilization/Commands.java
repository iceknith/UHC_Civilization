package fr.iceknith.uhc_civilization;

import org.bukkit.ChatColor;
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
                //Set the length
                switch (args[0]) {
                    case "length":
                        Main.Length = Integer.parseInt(args[1]);
                        commandSender.sendMessage("The duration of the game has been set to " + Main.Length + " minutes");
                        break;

                    //Set the proportion of player per team
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
                                for (Clan clan : Main.playerCount.keySet()) {
                                    for (int i = 0; i < Main.playerCount.get(clan); i++) {
                                        Main.playerdistribution.put(players.get(0), clan);
                                        ChatColor color = clan.getChatColor();
                                        String newName = String.valueOf(color) +
                                                ChatColor.BOLD +
                                                "[" +
                                                clan.toText() +
                                                "] " +
                                                ChatColor.RESET +
                                                players.get(0).getName() +
                                                ChatColor.RESET;
                                        if (clan == Clan.SPEC) {
                                            newName = String.valueOf(color) +
                                                    ChatColor.ITALIC +
                                                    "[" +
                                                    clan.toText() +
                                                    "] " +

                                                    players.get(0).getName() +
                                                    ChatColor.RESET;
                                        }
                                        players.get(0).setDisplayName(newName);
                                        players.get(0).setPlayerListName(newName);
                                        players.remove(0);
                                    }
                                }
                                commandSender.sendMessage("Starting game with following teams:");
                                printPlayerDispersion(commandSender);
                                //todo start the game
                            } else {
                                commandSender.sendMessage("The amount of online players needs to correspond to the total amount of players you assigned to each team");
                            }

                        }
                        break;
                    default:
                        commandSender.sendMessage("Invalid subcommand: '" + args[0] + "'");
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
        for (Clan clan : Main.playerCount.keySet()) {
            StringBuilder msg = new StringBuilder();
            msg.append(clan.getChatColor()).append(clan.toText()).append(ChatColor.RESET).append(": ");
            boolean x = false;
            for (Player player : Main.playerdistribution.keySet()) {
                if (clan.equals(Main.playerdistribution.get(player))) {
                    if (x)
                        msg.append(", ");
                    x = true;
                    msg.append(player.getName());

                }
            }
            sender.sendMessage(msg.toString());
        }
    }

    private void printPlayerCount(CommandSender sender) {
        sender.sendMessage("Team player counts:");
        for (Clan sClass : Main.playerCount.keySet()) {
            sender.sendMessage(sClass.getChatColor() + sClass.toText() + ChatColor.RESET + ": " + Main.playerCount.get(sClass));
        }
    }


}
