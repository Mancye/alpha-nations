package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class AdminEditLeadershipGUI {

    public static Inventory getMenu() {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED.toString() + "Select Leadership Type");

        ItemStack monarchy = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta monarchyMeta = (PotionMeta) monarchy.getItemMeta();
        monarchyMeta.setBasePotionData(new PotionData(PotionType.SPEED));
        monarchyMeta.setDisplayName(ChatColor.AQUA + "Monarchy");
        monarchy.setItemMeta(monarchyMeta);
        inv.setItem(11, monarchy);

        ItemStack diplomacy = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta diplomacyMeta = (PotionMeta) diplomacy.getItemMeta();
        diplomacyMeta.setBasePotionData(new PotionData(PotionType.JUMP));
        diplomacyMeta.setDisplayName(ChatColor.GREEN + "Diplomacy");
        diplomacy.setItemMeta(diplomacyMeta);
        inv.setItem(13, diplomacy);

        ItemStack tyranny = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta tyrannyMeta = (PotionMeta) tyranny.getItemMeta();
        tyrannyMeta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
        tyrannyMeta.setDisplayName(ChatColor.RED + "Tyranny");
        tyranny.setItemMeta(tyrannyMeta);
        inv.setItem(15, tyranny);

        InventoryUtil.addButton(inv, 18, Material.ARROW, ChatColor.RED + "Back");
        InventoryUtil.fillEmptySlots(inv);

        return inv;
    }

}
