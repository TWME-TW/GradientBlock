package dev.twme.gradientblock.listener;

import dev.twme.gradientblock.GradientBlock;
import dev.twme.gradientblock.util.GradientData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();


        UUID playerUUID = player.getUniqueId();
        if (!player.hasMetadata("gradientblock")) {
            return;
        }
        if (event.getAction() == InventoryAction.NOTHING && event.getSlot() == -999) {
            player.closeInventory();
            return;
        }
        if (event.getSlotType() != InventoryType.SlotType.CONTAINER) {
            return;
        }

        GradientData gradientData = new GradientData();

        if (GradientData.gradientDataHashMap.containsKey(player.getUniqueId())) {
            gradientData = GradientData.gradientDataHashMap.get(player.getUniqueId());
        }

        if (event.getSlot() >= 1 && event.getSlot() <= 7) {
            event.setCancelled(true);

            if (null == gradientData.getMaterialA() || null == gradientData.getMaterialB()) {
                return;
            }
            ItemStack[] itemStacks = gradientData.gradient();
            for (int i = 0; i <= 8; i++) {
                player.getInventory().setItem(i, itemStacks[i]);
            }
            GradientData.gradientDataHashMap.remove(playerUUID);
            player.updateInventory();
            player.closeInventory();
            return;
        }
        if (!(event.getAction() == InventoryAction.PLACE_ALL || event.getAction() == InventoryAction.PLACE_SOME || event.getAction() == InventoryAction.PLACE_ONE || event.getAction() == InventoryAction.SWAP_WITH_CURSOR)) {
            return;
        }

        if (!(event.getSlot() == 0 || event.getSlot() == 8)) {
            return;
        }
        if (!event.getCursor().getType().isBlock()) {
            return;
        }

        if (event.getSlot() == 0) {
            gradientData.setMaterialA(event.getCursor().getType());
            GradientData.gradientDataHashMap.put(playerUUID, gradientData);
        }
        if (event.getSlot() == 8) {
            gradientData.setMaterialB(event.getCursor().getType());
            GradientData.gradientDataHashMap.put(playerUUID, gradientData);
        }

        if (gradientData.getMaterialA() == null || gradientData.getMaterialB() == null) {
            return;
        }

        event.getInventory().setContents(gradientData.gradient());
        player.updateInventory();

        return;
    }

    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("gradientblock")) {
            player.removeMetadata("gradientblock", GradientBlock.getInstance());
            GradientData.gradientDataHashMap.remove(player.getUniqueId());
        }
    }
}
