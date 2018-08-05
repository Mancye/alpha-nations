package me.mancy.alphanations.managers;

import me.mancy.alphanations.main.Nation;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NationEditorManager {

    private static final Map<Player, Nation> nationEditing = new HashMap<>();
    public static final Map<Player, String> playersEditType = new HashMap<>();
    public static final Map<Nation, ItemStack> blockChanges = new HashMap<>();
    public static final Map<Nation, ChatColor> colorChanges = new HashMap<>();
    public static final Map<Nation, String> nameChanges = new HashMap<>();
    public static final Map<Nation, String> leadernameChanges = new HashMap<>();
    public static final Map<Nation, String> leadershiptypeChanges = new HashMap<>();
    public static final Map<Nation, String> capitalnameChanges = new HashMap<>();
    public static final Map<Nation, String> descriptionChanges = new HashMap<>();
    public static final List<Nation> descriptionsToClear = new ArrayList<>();
    public static final List<Nation> nationsToDelete = new ArrayList<>();

    public static Nation getPlayersNation(Player p) {
        if (nationEditing.containsKey(p)) {
            return nationEditing.get(p);
        }
        return null;
    }

    public static void setPlayersNation(Player p, Nation nation) {
        if (nationEditing.containsKey(p)) {
            nationEditing.remove(p);
        }
        if (nation != null)
        nationEditing.put(p, nation);
    }

}
