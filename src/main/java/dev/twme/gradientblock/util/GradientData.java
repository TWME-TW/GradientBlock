package dev.twme.gradientblock.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class GradientData {


    Material a = null, b = null;
    public static HashMap<UUID, GradientData> gradientDataHashMap = new HashMap<>();

    public void setMaterialA(Material materialA) {
        this.a = materialA;
    }

    public void setMaterialB(Material materialB) {
        this.b = materialB;
    }

    public ItemStack[] gradient() {
        ItemStack[] itemStacks = new ItemStack[9];

        itemStacks[0] = new ItemStack(a);
        itemStacks[8] = new ItemStack(b);

        itemStacks[4] = new ItemStack(GradientUtil.getBlock(itemStacks[0].getType(), itemStacks[8].getType()));
        itemStacks[2] = new ItemStack(GradientUtil.getBlock(itemStacks[0].getType(), itemStacks[4].getType()));
        itemStacks[1] = new ItemStack(GradientUtil.getBlock(itemStacks[0].getType(), itemStacks[2].getType()));

        itemStacks[3] = new ItemStack(GradientUtil.getBlock(itemStacks[2].getType(), itemStacks[4].getType()));
        itemStacks[6] = new ItemStack(GradientUtil.getBlock(itemStacks[4].getType(), itemStacks[8].getType()));
        itemStacks[5] = new ItemStack(GradientUtil.getBlock(itemStacks[4].getType(), itemStacks[6].getType()));

        itemStacks[7] = new ItemStack(GradientUtil.getBlock(itemStacks[6].getType(), itemStacks[8].getType()));

        return itemStacks;
    }

    public Material getMaterialA() {
        return a;
    }

    public Material getMaterialB() {
        return b;
    }

}
