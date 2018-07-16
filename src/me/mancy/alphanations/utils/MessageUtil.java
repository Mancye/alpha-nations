package me.mancy.alphanations.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    private static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + ChatColor.BOLD.toString() + "P" + ChatColor.RED + ChatColor.BOLD.toString() + "A" + ChatColor.DARK_GRAY + ":" + ChatColor.GRAY + "Nations" + ChatColor.DARK_GRAY + "]";

    private static final String noPermissionMsg = ChatColor.RED + "Sorry, you don't have permission for this";
    private static final String invalidArgsMsg = ChatColor.GRAY + " Invalid Arguments, Use " + ChatColor.GREEN + "/nations help" + ChatColor.GRAY + " To View Available Commands";

    public static void sendNoPermissionMsg(Player p) {
        p.sendMessage(prefix + " " + noPermissionMsg);
    }

    public static void sendInvalidArgumentsMsg(Player p) {
        p.sendMessage(prefix + " " + invalidArgsMsg);
    }

    public static void sendMsgWithPrefix(Player p, String message) {
        p.sendMessage(prefix + " " + message);
    }

    public static String getPrefix() {
        return prefix;
    }

}
