package me.mancy.alphanations.commands;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String noPermission = ChatColor.RED + "Sorry, you don't have permission for this";
        final String enterNationName = ChatColor.RED + "You must enter the name of the nation to add/remove";
        if (label.equalsIgnoreCase("nations")) {
            if (args.length == 0) {
                if (sender.hasPermission("nations.")) {
                    Player p = (Player) sender;
                    p.openInventory(NationSelectionGUI.getSelectionInventory());
                    return true;
                } else {
                    sender.sendMessage(noPermission);
                    return false;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    if (sender.hasPermission("nations.edit") || sender.hasPermission("nations.*")) {
                        sender.sendMessage(enterNationName);
                        return false;
                    } else {
                        sender.sendMessage(noPermission);
                        return false;
                    }

                } else if (args[0].equalsIgnoreCase("list")) {
                    if (sender.hasPermission("nations.list")) {
                        for (Nation nation : NationManager.nationList) {
                            sender.sendMessage("- " + nation.getName());
                        }
                        return true;
                    } else {
                        sender.sendMessage(noPermission);
                        return false;
                    }
                }
            } else if (args.length == 2) {
                    /*
                        Make sure no other nations with the same name exist
                        Make su
                     */
                if (sender.hasPermission("nations.edit") || sender.hasPermission("nations.*")) {
                    String name = args[1];

                    if (args[0].equalsIgnoreCase("add")) {

                        NationManager.addNation(new Nation(name, null));
                        if (NationManager.doesNationExist(name)) {
                            sender.sendMessage(name + " Nation Has Been Created Successfully");
                            return true;
                        } else {
                            sender.sendMessage("An error occurred while creating this nation!");
                            return false;
                        }


                    } else if (args[0].equalsIgnoreCase("remove")) {
                        NationManager.removeNation(name);
                        if (!NationManager.doesNationExist(name)) {
                            sender.sendMessage(name + " Nation Removed Successfully!");
                            return  true;
                        } else {
                            sender.sendMessage("An error occurred while removing this nation!");
                        }
                    }

                } else {
                    sender.sendMessage(noPermission);
                }

            }
        }

        return false;
    }

}
