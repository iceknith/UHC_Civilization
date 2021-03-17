package fr.iceknith.uhc_civilization;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            if (args.length >= 1) {
                //determine the length
                switch (args[0]) {
                    case "length":
                        Main.Length = Integer.parseInt(args[1]);
                        commandSender.sendMessage("The length has been correctly set to " + Main.Length + "minutes");
                        break;

                    //determine the proportion of player per team
                    case "team":
                        if (args.length >= 2) {
                            SocialClass sClass;
                            switch (args[1]) {
                                case "bourgeois":
                                    sClass = SocialClass.BOURGEOIS;
                                    break;
                                case "nomadic":
                                    sClass = SocialClass.NOMADIC;
                                    break;
                                case "farmer":
                                    sClass = SocialClass.FARMER;
                                    break;
                                case "thief":
                                    sClass = SocialClass.THIEF;
                                    break;
                                case "spectator":
                                    sClass = SocialClass.SPEC;
                                    break;
                                default:
                                    commandSender.sendMessage("Couldn't find the '" + args[1] + "' class, please try again");
                                    return true;
                            }
                            Main.playerCount.put(sClass, Integer.parseInt(args[2]));
                            printPlayerCount(commandSender);
                        }
                        break;

                    //startig the game
                    case "start":
                        if (commandSender instanceof Player) {
                            int playerNum = ((Player) commandSender).getWorld().getPlayers().size();
                            if (playerNum == Main.totalPlayersFromMap()) {
                                List<Player> players = ((Player)commandSender).getWorld().getPlayers();
                                Collections.shuffle(players);
                                for (SocialClass sClass : Main.playerCount.keySet()) {
                                    for(int i = 0 ; i < Main.playerCount.get(sClass);i++){
                                        if(players.size() == 0){
                                            break;
                                        }
                                        Main.playerdistribution.put(players.get(0),sClass);
                                        players.remove(0);
                                    }
                                }
                                printPlayerDispersion(commandSender);
                            } else {
                                commandSender.sendMessage("you must have as many players as people online in this world");
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
            sender.sendMessage(player.getDisplayName() + "\t:\t" + String.valueOf(Main.playerdistribution.get(player)));
        }
    }

    private void printPlayerCount(CommandSender sender) {
        sender.sendMessage("Team player counts:");
        for (SocialClass sClass : Main.playerCount.keySet()) {
            sender.sendMessage(sClass.toString() + "\t:\t" + String.valueOf(Main.playerCount.get(sClass)));
        }
    }


}
