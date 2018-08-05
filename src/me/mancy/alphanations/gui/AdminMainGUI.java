package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdminMainGUI {


    public static Inventory getAdminEditGUIPageOne(Nation nationToEdit) {
        /*
         10           12              14           16
         Edit Block   Edit Name   Edit Color   Delete Nation
         Grass Block   Nametag    White wool   Barrier
         */
        final Inventory mainAdminGUI = Bukkit.createInventory(null, 27, ChatColor.RED + ChatColor.ITALIC.toString() + "Editing Nation (Page 1): " + nationToEdit.getName());
        List<String> blockLore = new ArrayList<>();
        blockLore.add(ChatColor.GRAY + "Edit the block which will represent");
        blockLore.add(ChatColor.GRAY + "the nation during selection.");
        InventoryUtil.addButton(mainAdminGUI, 10, Material.GRASS, ChatColor.GREEN + "Edit block", blockLore);

        List<String> nameLore = new ArrayList<>();
        nameLore.add(ChatColor.GRAY + "Change the name of the nation.");
        InventoryUtil.addButton(mainAdminGUI, 12, Material.NAME_TAG, ChatColor.YELLOW + "Edit name", nameLore);

        List<String> colorLore = new ArrayList<>();
        colorLore.add(ChatColor.GRAY + "Set the nation's main color.");
        InventoryUtil.addButton(mainAdminGUI, 14, Material.WHITE_WOOL, ChatColor.DARK_AQUA + "Edit color", colorLore);

        List<String> deleteLore = new ArrayList<>();
        deleteLore.add(ChatColor.GRAY + "Delete this nation.");
        InventoryUtil.addButton(mainAdminGUI, 16, Material.BARRIER, ChatColor.RED + "Delete nation", deleteLore);

        InventoryUtil.addButton(mainAdminGUI, 26, Material.ARROW, ChatColor.RED + "Next");

        InventoryUtil.fillEmptySlots(mainAdminGUI);

        return mainAdminGUI;

    }

    public static Inventory getAdminEditGUIPageTwo(Nation nationToEdit) {
        /*
         11           13              15
         Edit Desc   Set Leader   Set leadership
         Book   Iron sword    Diamond sword
         */
        final Inventory mainAdminGUI = Bukkit.createInventory(null, 27, ChatColor.RED + ChatColor.ITALIC.toString() + "Editing Nation (Page 2): " + nationToEdit.getName());
        List<String> descLore = new ArrayList<>();
        descLore.add(ChatColor.GRAY + "Change the nation description.");
        InventoryUtil.addButton(mainAdminGUI, 10, Material.BOOK, ChatColor.GOLD + "Edit description", descLore);

        List<String> leaderLore = new ArrayList<>();
        leaderLore.add(ChatColor.GRAY + "Pick the nation leader.");
        InventoryUtil.addButton(mainAdminGUI, 12, Material.IRON_SWORD, ChatColor.WHITE + "Edit leader", leaderLore);

        List<String> leadershipLore = new ArrayList<>();
        leadershipLore.add(ChatColor.GRAY + "Pick the leadership type.");
        InventoryUtil.addButton(mainAdminGUI, 14, Material.DIAMOND_SWORD, ChatColor.AQUA + "Edit leadership", leadershipLore);

        List<String> capitallore = new ArrayList<>();
        capitallore.add(ChatColor.GRAY + "Pick the capital name.");
        InventoryUtil.addButton(mainAdminGUI, 16, Material.FEATHER, ChatColor.LIGHT_PURPLE + "Edit capital", capitallore);

        InventoryUtil.addButton(mainAdminGUI, 18, Material.ARROW, ChatColor.RED + "Back");

        InventoryUtil.fillEmptySlots(mainAdminGUI);

        return mainAdminGUI;

    }




}
