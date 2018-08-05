package me.mancy.alphanations.commands;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
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
                if (NationManager.getPlayersNation(p) == null) {
                    if (NationManager.getAmountNations() <= 0) {
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "Error: No nations found");
                        return true;
                    }
                    p.openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                    return true;
                }
                Nation nation = NationManager.getPlayersNation(p);
                ChatColor color = nation.getColor();
                p.sendMessage(ChatColor.GRAY + ">> " + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "---------+-| "
                                + ChatColor.RESET + color.toString() + "Nation of " + nation.getName() + " " + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "|-+---------" + ChatColor.RESET);
                p.sendMessage(ChatColor.GRAY + ">> Capital City" + ChatColor.DARK_GRAY + ":" + color + " " + nation.getCapitalName());
                StringBuilder sb = new StringBuilder();
                for (int t = 0; t < nation.getTowns().size(); t++) {
                    String town = nation.getTowns().get(t).getName();
                    sb.append(town);
                    if (nation.getTowns().size() > 1 && t < nation.getTowns().size() - 1) {
                        sb.append(", ");
                    }

                }
                p.sendMessage(ChatColor.GRAY + ">> Allied Towns" + ChatColor.DARK_GRAY + " [" + color + nation.getTowns().size() + ChatColor.DARK_GRAY + "]: " + ChatColor.WHITE + sb.toString());
                ChatColor typeColor = ChatColor.WHITE;
                if (nation.getLeadershipType().equalsIgnoreCase("monarchy")) {
                    typeColor = ChatColor.AQUA;
                } else if (nation.getLeadershipType().equalsIgnoreCase("diplomacy")) {
                    typeColor = ChatColor.GREEN;
                } else if (nation.getLeadershipType().equalsIgnoreCase("tyranny")) {
                    typeColor = ChatColor.RED;
                }
                p.sendMessage(ChatColor.GRAY + ">> Leadership" + ChatColor.DARK_GRAY + ": " + typeColor + nation.getLeadershipType() + ChatColor.DARK_GRAY + " | " + color + nation.getLeaderName());
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
                        if (NationManager.getAmountNations() <= 0) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "No nations found!");
                            return true;
                        }
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
                            if (NationManager.getAmountNations() <= 0) {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "Error: No nations created!");
                                return true;
                            }
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

                        p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations" + ChatColor.GRAY + " To view information on your nation");
                        p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations select" + ChatColor.GRAY + " To select a nation");
                        if (p.hasPermission("nations.capital") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations capital" + ChatColor.GRAY + " To travel to your nation's capital");
                        }
                        if (p.hasPermission("nations.leave") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations leave" + ChatColor.GRAY + " To leave your nation");
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
                        if (p.hasPermission("nations.setcapital") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations setchooselocation" + ChatColor.GRAY + " To set the location that players will be at when choosing a nation");
                        }
                        if (p.hasPermission("nations.list") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations list" + ChatColor.GRAY + " To list nations");
                        }
                        if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations setcapitalname (nation name) (capital name)" + ChatColor.GRAY + " To set the capital name of the specified nation");
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations setleadership (nation name) (monarchy/tyranny/diplomacy)" + ChatColor.GRAY + " To set the leadership type of the specified nation");
                            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /nations setleader (nation name) (name)" + ChatColor.GRAY + " To set the leaders name of the specified nation");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("select")) {
                    if (p.hasPermission("nations.select") || p.hasPermission("nations.*")) {
                        if (NationManager.getAmountNations() <= 0) {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "No nations found!");
                            return true;
                        }
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
                } else if (args[0].equalsIgnoreCase("leave")) {
                    if (p.hasPermission("nations.leave") || p.hasPermission("nations.*")) {

                        if (NationManager.getPlayersNation(p) != null) {
                            NationManager.getPlayersNation(p).removeMember(p);
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "You have left your nation!");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "You are not in a nation!");
                            return true;
                        }

                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("setchooselocation")) {
                    if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                        Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getWorld().getHighestBlockYAt(p.getLocation()), p.getLocation().getBlockZ());
                        Main.chooseLocation = loc;
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Set choose location to your current position");
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else {
                    MessageUtil.sendInvalidArgumentsMsg(p);
                    return true;
                }
            } else if (args.length == 2) {
                if (sender.hasPermission("nations.edit") || sender.hasPermission("nations.*")) {
                    String name = args[1];

                    if (args[0].equalsIgnoreCase("add")) {


                        if (!NationManager.doesNationExist(name)) {
                            NationManager.addNation(new Nation(name, null, p.getWorld().getHighestBlockAt(p.getLocation()).getLocation()));
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GREEN + name + ChatColor.GRAY + " Nation Has Been Created Successfully");
                            return true;
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "A nation with this same name already exists!");
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {

                        if (NationManager.doesNationExist(name)) {
                            NationManager.removeNation(name);
                            if (NationManager.doesNationExist(name)) {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "An error occurred while removing this nation! This nation still exists!");
                                return true;
                            } else {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + name + ChatColor.GRAY + " Nation Removed Successfully!");
                                return true;
                            }
                        } else {
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nation not found, see a list of available nations with " + ChatColor.GREEN + "/nations list");
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

            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("setcapitalname")) {
                    if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                        if (!args[1].equalsIgnoreCase("") && !args[2].equalsIgnoreCase("")) {
                            if (NationManager.getNation(args[1]) != null) {
                                NationManager.getNation(args[1]).setCapitalName(ChatColor.translateAlternateColorCodes('&', args[2]));
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Set capital name of " +
                                        ChatColor.GREEN + NationManager.getNation(args[1]).getName() +
                                        ChatColor.GRAY + " To " + ChatColor.GREEN + NationManager.getNation(args[1]).getCapitalName());
                            } else {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nation not found, see a list of available nations with " + ChatColor.GREEN + "/nations list");
                            }
                        } else {
                            MessageUtil.sendInvalidArgumentsMsg(p);
                            return true;
                        }
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("setleader")) {
                    if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                        if (!args[1].equalsIgnoreCase("") && !args[2].equalsIgnoreCase("")) {
                            if (NationManager.getNation(args[1]) != null) {
                                NationManager.getNation(args[1]).setLeaderName(args[2]);
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Set leader of " +
                                                                            ChatColor.GREEN + NationManager.getNation(args[1]).getName() +
                                        ChatColor.GRAY + " To " + ChatColor.GREEN + NationManager.getNation(args[1]).getLeaderName());
                            } else {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nation not found, see a list of available nations with " + ChatColor.GREEN + "/nations list");
                            }
                        } else {
                            MessageUtil.sendInvalidArgumentsMsg(p);
                            return true;
                        }
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("setleadership")) {
                    if (p.hasPermission("nations.edit") || p.hasPermission("nations.*")) {
                        if (!args[1].equalsIgnoreCase("") && !args[2].equalsIgnoreCase("")) {
                            if (NationManager.getNation(args[1]) != null) {
                                if (!(args[2].equalsIgnoreCase("tyranny") || args[2].equalsIgnoreCase("monarchy") || args[2].equalsIgnoreCase("diplomacy"))) {
                                    MessageUtil.sendInvalidArgumentsMsg(p);
                                    return true;
                                }
                                String type = args[2];
                                String formattedType = type.substring(0, 1).toUpperCase();
                                for (int x = 1; x < type.length(); x++) {
                                    formattedType += type.substring(x, x + 1).toLowerCase();
                                }

                                Nation n = NationManager.getNation(args[1]);
                                n.setLeadershipType(formattedType);
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Set leadership of " +
                                        ChatColor.GREEN + n.getName() +
                                        ChatColor.GRAY + " To " + ChatColor.GREEN + n.getLeadershipType());
                                ChatColor typeColor = ChatColor.WHITE;
                                if (formattedType.equalsIgnoreCase("tyranny")) {
                                    typeColor = ChatColor.RED;
                                } else if (formattedType.equalsIgnoreCase("monarchy")) {
                                    typeColor = ChatColor.AQUA;
                                } else {
                                    typeColor = ChatColor.GREEN;
                                }

                                n.broadcast(ChatColor.GRAY + "The leadership of your nation has changed to a " + typeColor + formattedType);
                            } else {
                                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Nation not found, see a list of available nations with " + ChatColor.GREEN + "/nations list");
                            }
                        } else {
                            MessageUtil.sendInvalidArgumentsMsg(p);
                            return true;
                        }
                    } else {
                        MessageUtil.sendNoPermissionMsg(p);
                        return true;
                    }
                }
            } else {
                MessageUtil.sendInvalidArgumentsMsg(p);
                return true;
            }
        }

        return false;
    }
}

