package dev.twme.gradientblock.util;

import com.fastasyncworldedit.core.Fawe;
import com.fastasyncworldedit.core.util.TextureUtil;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public class GradientUtil {
    public static Material getBlock(Material m1, Material m2, int maxComplexity) {

        BlockType b1, b2;
        int staticComplexity = 100;
        try {
            b1 = BlockTypes.get("minecraft:" + m1.name().toLowerCase());
            b2 = BlockTypes.get("minecraft:" + m2.name().toLowerCase());

            int color, c1, c2;
            if (maxComplexity == -1) {
                maxComplexity = 73;
            } else {
                staticComplexity = maxComplexity;
            }
            TextureUtil textureUtil = Fawe.instance().getCachedTextureUtil(true, 0, maxComplexity);


            c1 = textureUtil.getColor(b1);
            c2 = textureUtil.getColor(b2);

            if (c1 == 0) {
                BlockData blockData = m1.createBlockData();
                c1 = blockData.getMapColor().asARGB();
            }
            if (c2 == 0) {
                BlockData blockData = m2.createBlockData();
                c2 = blockData.getMapColor().asARGB();
            }

            staticComplexity = 100;

            color = TextureUtil.averageColor(c1, c2);

            BlockType blockType = textureUtil.getNearestBlock(color);
            return Material.getMaterial(blockType.getId().replaceAll("minecraft:", "").toUpperCase());
        } catch (Exception e) {
            return ConfigUtil.GUI_ITEM_TYPE;
        }
    }
}
