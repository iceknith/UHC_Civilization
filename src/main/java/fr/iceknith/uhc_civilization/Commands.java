package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                            switch (args[1]) {
                                case "bourgeois":
                                    if (args.length == 3){
                                        Main.playerBourgeoisNum = Integer.parseInt(args[2]);
                                        commandSender.sendMessage("The Bourgeois player amount has been set to " + Main.playerBourgeoisNum);
                                    }else{
                                        commandSender.sendMessage("you need to specify the amount of player you want to put in this class");
                                    }
                                    break;
                                case "nomadic":
                                    if (args.length == 3){
                                        Main.playerNomadicNum = Integer.parseInt(args[2]);
                                        commandSender.sendMessage("The Nomadic player amount has been set to " + Main.playerNomadicNum);
                                    }else{
                                        commandSender.sendMessage("you need to specify the amount of player you want to put in this class");
                                    }
                                    break;
                                case "farmer":
                                    if (args.length == 3){
                                        Main.playerFarmerNum = Integer.parseInt(args[2]);
                                        commandSender.sendMessage("The Farmer player amount has been set to " + Main.playerFarmerNum);
                                    }else{
                                        commandSender.sendMessage("you need to specify the amount of player you want to put in this class");
                                    }
                                    break;
                                case "thief":
                                    if (args.length == 3){
                                        Main.playerThiefNum = Integer.parseInt(args[2]);
                                        commandSender.sendMessage("The Thief player amount has been set to " + Main.playerThiefNum);
                                    }else{
                                        commandSender.sendMessage("you need to specify the amount of player you want to put in this class");
                                    }
                                    break;
                                case "spectator":
                                    if (args.length == 3){
                                        Main.playerSpecNum = Integer.parseInt(args[2]);
                                        commandSender.sendMessage("The Spectator player amount has been set to " + Main.playerSpecNum);
                                    }else{
                                        commandSender.sendMessage("you need to specify the amount of player you want to put in this class");
                                    }

                                    break;
                                default:
                                    commandSender.sendMessage("Couldn't find that specific class, please try again");
                                    break;
                            }
                        }
                        break;

                    //startig the game
                    case "start":
                        if (commandSender instanceof Player) {
                            int playerNum = ((Player) commandSender).getWorld().getPlayers().size();
                            if (playerNum == Main.playerBourgeoisNum + Main.playerNomadicNum + Main.playerFarmerNum + Main.playerThiefNum + Main.playerSpecNum) {
                                List<Player> players = new ArrayList<Player>();
                                int i = -1;
                                while (((Player) commandSender).getWorld().getPlayers().size() > players.size()) {
                                    i++;
                                    players.add(((Player) commandSender).getWorld().getPlayers().get(i));
                                }
                                i = -1;
                                Random rand = new Random();
                                int rand_int;
                                while (i < Main.playerThiefNum) {
                                    i++;
                                    rand_int = rand.nextInt(players.size());
                                    Main.thief.add(players.get(rand_int));
                                    players.remove(rand_int);
                                }
                                while (i < Main.playerNomadicNum) {
                                    i++;
                                    rand_int = rand.nextInt(players.size());
                                    Main.nomadic.add(players.get(rand_int));
                                    players.remove(rand_int);
                                }
                                while (i < Main.playerBourgeoisNum) {
                                    i++;
                                    rand_int = rand.nextInt(players.size());
                                    Main.bourgeois.add(players.get(rand_int));
                                    players.remove(rand_int);
                                }
                                while (i < Main.playerFarmerNum) {
                                    i++;
                                    rand_int = rand.nextInt(players.size());
                                    Main.farmer.add(players.get(rand_int));
                                    players.remove(rand_int);
                                }
                                while (i < Main.playerSpecNum) {
                                    i++;
                                    rand_int = rand.nextInt(players.size());
                                    Main.spec.add(players.get(rand_int));
                                    players.remove(rand_int);
                                }
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
            commandSender.sendMessage("an error has occured");
            commandSender.sendMessage("usage /civ <args>");

        }
        return true;

    }


}
