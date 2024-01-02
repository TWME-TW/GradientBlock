package dev.twme.gradientblock.util;

import com.fastasyncworldedit.core.Fawe;
import com.fastasyncworldedit.core.util.TextureUtil;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Material;

public class GradientUtil {
    public static Material getBlock(Material m1, Material m2, int maxComplexity) {

        BlockType b1, b2;
        try {
            b1 = BlockTypes.get("minecraft:" + m1.name().toLowerCase());
            b2 = BlockTypes.get("minecraft:" + m2.name().toLowerCase());

            int color, c1, c2;
            if (maxComplexity == -1){
                maxComplexity = 73;
            }
            TextureUtil textureUtil = Fawe.instance().getCachedTextureUtil(true, 0, maxComplexity);
            TextureUtil t1 = Fawe.instance().getCachedTextureUtil(true, 0, 100);

            c1 = textureUtil.getColor(b1);
            c2 = textureUtil.getColor(b2);

            if (c1 == 0) {
                c1 = t1.getColor(b1);
            }
            if (c2 == 0) {
                c2 = t1.getColor(b2);
            }

            color = TextureUtil.averageColor(c1, c2);

            BlockType blockType = textureUtil.getNearestBlock(color);
            return Material.getMaterial(blockType.getId().replaceAll("minecraft:", "").toUpperCase());
        } catch (Exception e) {
            return ConfigUtil.GUI_ITEM_TYPE;
        }
    }
}
