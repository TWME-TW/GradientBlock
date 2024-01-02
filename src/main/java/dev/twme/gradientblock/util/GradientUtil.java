package dev.twme.gradientblock.util;

import com.fastasyncworldedit.core.Fawe;
import com.fastasyncworldedit.core.util.TextureUtil;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Material;

public class GradientUtil {
    public static Material getBlock(Material m1, Material m2) {

        BlockType b1, b2;
        try {
            b1 = BlockTypes.get("minecraft:" + m1.name().toLowerCase());
            b2 = BlockTypes.get("minecraft:" + m2.name().toLowerCase());

            int color;
            TextureUtil textureUtil = Fawe.instance().getCachedTextureUtil(true, 0, 73);

            color = TextureUtil.averageColor(textureUtil.getColor(b1), textureUtil.getColor(b2));

            BlockType blockType = textureUtil.getNearestBlock(color);
            return Material.getMaterial(blockType.getId().replaceAll("minecraft:", "").toUpperCase());
        } catch (Exception e) {
            return Material.BLACK_STAINED_GLASS;
        }
    }
}
