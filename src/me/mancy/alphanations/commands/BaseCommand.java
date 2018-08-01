package me.mancy.alphanations.commands;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
                if (p.hasPermission("nations.select") || p.hasPermission("nations.*")) {
                    if (NationManager.getPlayersNation(p) == null) {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Select a nation to join");
                        p.openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                        return true;
                    } else {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "Sorry you are already in a nation");
                        return true;
                    }

                } else {
                    MessageUtil.sendNoPermissionMsg(p);
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                        MessageUtil.sendMsgWithPrefix(p,
                                ChatColor.RED + "Please use the following format to edit nations: " +
                                        ChatColor.GRAY + ChatColor.ITALIC.toString() + "/nations (add/remove) (name)");
                        return true;
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }

                } else if (args[0].equalsIgnoreCase("list")) {
                    if (p.hasPermission("nations.list") || p.hasPermission("nations.*")) {
                        if (NationManager.getNationList().isEmpty()) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "No nations found");
                            return true;
                        }
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nations:");
                        for (Nation nation : NationManager.getNationList()) {
                            p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + nation.getName());
                        }
                        return true;
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("edit")) {
                    if (p.hasPermission("nations.admin") || p.hasPermission("nations.*")) {
                        p.openInventory(NationSelectionGUI.getAdminNationSelectionInventory());
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("capital")) {
                    if (p.hasPermission("nations.capital") || p.hasPermission("nations.*")) {
                        if (NationManager.getPlayersNation(p) != null) {
                            p.teleport(NationManager.getPlayersNation(p).getCapital());
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Teleported to your nation's capital!");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "You must join a nation first!");
                            p.openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                            return true;
                        }
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (p.hasPermission("nations.help") || p.hasPermission("nations.*")) {
                        String helpPrefix = ChatColor.GRAY + "-";
                        p.sendMessage(" ");
                        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + ChatColor.BOLD.toString() + "P" + ChatColor.RED + ChatColor.BOLD.toString() + "A" + ChatColor.DARK_GRAY + ":" + ChatColor.GRAY + "Nations" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Nations" + ChatColor.GRAY + " help page:");
                        ;
                        p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations" + ChatColor.GRAY + " Opens the nation selection menu if a nation is not already selected");

                        if (p.hasPermission("nations.capital") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations capital" + ChatColor.GRAY + " To travel to your nation's capital");
                        }
                        if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations edit" + ChatColor.GRAY + " To open the editor GUI");
                        }
                        if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations add/remove (name)" + ChatColor.GRAY + " To add/remove a nation with the specified name");
                        }
                        if (p.hasPermission("nations.setcapital") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations setcapital (nation name)" + ChatColor.GRAY + " To set the capital location of a specified nation to your current location");
                        }
                        if (p.hasPermission("nations.list") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations list" + ChatColor.GRAY + " To list nations");
                        }
                    }
                } else {
                    MessageUtil.sendInvalidArgumentsMsg(p);
                    return true;
                }
            } else if (args.length == 2) {
                    /*
                        Make sure no other nations with the same name exist
                        Make su
                     */
                if (sender.hasPermission("nations.edit") || sender.hasPermission("nations.*")) {
                    String name = args[1];

                    if (args[0].equalsIgnoreCase("add")) {

                        NationManager.addNation(new Nation(name, null, p.getWorld().getHighestBlockAt(p.getLocation()).getLocation()));
                        if (NationManager.doesNationExist(name)) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GREEN + name + ChatColor.GRAY + " Nation Has Been Created Successfully");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "An error occurred while creating this nation!");
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        NationManager.removeNation(name);
                        if (!NationManager.doesNationExist(name)) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + name + ChatColor.GRAY + " Nation Removed Successfully!");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, "An error occurred while removing this nation! This nation already exists!");
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("setcapital")) {
                        if (NationManager.getNation(args[1]) != null) {
                            Location capital = p.getWorld().getHighestBlockAt(p.getLocation()).getLocation();
                            NationManager.getNation(args[1]).setCapital(capital);
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Successfully set nation's capital at your current location");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nation not found, see a list of available nations with " + ChatColor.GREEN + "/nations list");
                            return true;
                        }
                    } else {
                        MessageUtil.sendInvalidArgumentsMsg(p);
                        return true;
                    }

                } else {
                    MessageUtil.sendNoPermissionMsg(p);
                    return true;
                }

            } else {
                MessageUtil.sendInvalidArgumentsMsg(p);
                return true;
            }
        }

        return false;
    }
}

