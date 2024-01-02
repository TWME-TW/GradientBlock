package dev.twme.gradientblock.commands;

import dev.twme.gradientblock.GradientBlock;
import dev.twme.gradientblock.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
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
}
