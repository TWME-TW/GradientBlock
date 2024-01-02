package dev.twme.gradientblock.commands;

import dev.twme.gradientblock.GradientBlock;
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
            commandSender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("gradientblock.use")) {
            player.sendMessage("You do not have permission to use this command!");
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 9, "漸層方塊產生器");

        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("請將兩個方塊分別放置在兩邊的格子中");
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
