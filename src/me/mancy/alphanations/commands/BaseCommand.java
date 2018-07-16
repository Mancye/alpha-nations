package me.mancy.alphanations.commands;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        if (label.equalsIgnoreCase("nations")) {
            if (args.length == 0) {
                if (sender.hasPermission("nations.")) {
                    if (NationManager.getPlayersNation(p) == null) {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Select a nation to join");
                        p.openInventory(NationSelectionGUI.getAdminNationSelectionInventory());
                        return true;
                    } else {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "Sorry you are already in a nation");
                        return false;
                    }

                } else {
                    MessageUtil.sendNoPermissionMsg(p);
                    return false;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    if (sender.hasPermission("nations.edit") || sender.hasPermission("nations.*")) {
                        MessageUtil.sendMsgWithPrefix(p,
                                ChatColor.RED + "Please use the following format to edit nations: " +
                                        ChatColor.GRAY + ChatColor.ITALIC.toString() + "/nations (add/remove) (name)");
                        return false;
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return false;
                    }

                } else if (args[0].equalsIgnoreCase("list")) {
                    if (sender.hasPermission("nations.list") || sender.hasPermission("nations.*")) {
                        if (NationManager.getNationList().isEmpty()) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "No nations found");
                            return true;
                        }
                        for (Nation nation : NationManager.getNationList()) {
                            sender.sendMessage("- " + nation.getName());
                        }
                        return true;
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("admin")) {
                    if (sender.hasPermission("nations.admin") || sender.hasPermission("nations.*")) {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Select a nation to edit");
                        p.openInventory(NationSelectionGUI.getAdminNationSelectionInventory());
                        return true;
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
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GREEN + name + ChatColor.GRAY + " Nation Has Been Created Successfully");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p,ChatColor.RED + "An error occurred while creating this nation!");
                            return false;
                        }


                    } else if (args[0].equalsIgnoreCase("remove")) {
                        NationManager.removeNation(name);
                        if (!NationManager.doesNationExist(name)) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + name + ChatColor.GRAY + " Nation Removed Successfully!");
                            return  true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, "An error occurred while removing this nation! This nation already exists!");
                            return false;
                        }
                    }

                } else {
                    MessageUtil.sendNoPermissionMsg(p);
                    return false;
                }

            }
        }

        return false;
    }

}
