package dev.twme.gradientblock.commands;

import dev.twme.gradientblock.GradientBlock;
import dev.twme.gradientblock.util.ConfigUtil;
import dev.twme.gradientblock.util.GradientData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigUtil.NEED_PLAYER);
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("gradientblock.use")) {
            player.sendMessage(ConfigUtil.NO_PERMISSION);
            return true;
        }
        if (strings.length == 1) {
            if ("reload".equals(strings[0].toLowerCase())) {
                if (!player.hasPermission("gradientblock.reload")){
                    player.sendMessage(ConfigUtil.NO_PERMISSION);
                    return true;
                }
                GradientBlock.reload();
                player.sendMessage(ConfigUtil.RELOAD_MESSAGE);
                return true;
            }
            try {
                int complexity = Integer.parseInt(strings[0]);
                GradientData gradientData = new GradientData();
                if (complexity > 100 || complexity < 0) {
                    player.sendMessage(ConfigUtil.COMPLEXITY_RANGE);
                    return true;
                }
                gradientData.setMaxComplexity(complexity);
            } catch (NumberFormatException e) {
                player.sendMessage(ConfigUtil.COMPLEXITY_NEED_INTEGER);
                return false;
            }
        }

        Inventory inventory = Bukkit.createInventory(null, 9, ConfigUtil.GUI_TITLE);

        ItemStack itemStack = new ItemStack(ConfigUtil.GUI_ITEM_TYPE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ConfigUtil.GUI_ITEM_NAME);
        itemStack.setItemMeta(itemMeta);

        inventory.setItem(1, itemStack);
        inventory.setItem(2, itemStack);
        inventory.setItem(3, itemStack);
        inventory.setItem(4, itemStack);
        inventory.setItem(5, itemStack);
        inventory.setItem(6, itemStack);
        inventory.setItem(7, itemStack);

        player.openInventory(inventory);

        player.setMetadata("gradientblock", new FixedMetadataValue(GradientBlock.getInstance(), "gradientblock"));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            return null;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("gradientblock.use")) {
            return null;
        }
        if (strings.length > 1) {
            return null;
        }

        List<String> tab = new ArrayList<>();
        tab.add("73");
        if (player.hasPermission("gradientblock.reload")) {
            tab.add("reload");
        }
        try {
            if (Integer.valueOf(strings[0]) < 10 && Integer.valueOf(strings[0]) != 0) {
                for (int i = 0; i <= 9; i++) {
                    tab.add(strings[0] + i);
                }
            }
            if (Integer.valueOf(strings[0]) == 10) {
                tab.add("100");
            }
        } catch (NumberFormatException e) {
            for (int i = 0; i <= 9; i++) {
                tab.add(String.valueOf(i));
            }
        }
        return tab;
    }
}
