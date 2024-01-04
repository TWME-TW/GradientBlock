package dev.twme.gradientblock.util;

import dev.twme.gradientblock.GradientBlock;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigUtil {
    public static String NO_PERMISSION;
    public static String NEED_PLAYER;
    public static String COMPLEXITY_NEED_INTEGER;
    public static String COMPLEXITY_RANGE;
    public static String RELOAD_MESSAGE;
    public static String GUI_TITLE;
    public static String GUI_ITEM_NAME;
    public static Material GUI_ITEM_TYPE;


    public static void loadConfig() {
        String path = "config.yml";

        GradientBlock instance = GradientBlock.getInstance();
        File configFile = new File(instance.getDataFolder(), path);

        if (!configFile.exists()) {
            instance.saveResource(path, false);
        }

        YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource(path), StandardCharsets.UTF_8));

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.addDefaults(defaultConfig);

        NO_PERMISSION = config.getString("commands.no-permission");
        NEED_PLAYER = config.getString("commands.need-player");
        GUI_TITLE = config.getString("gui.title");
        GUI_ITEM_NAME = config.getString("gui.item-name");
        GUI_ITEM_TYPE = Material.getMaterial(config.getString("gui.item-type"));
        COMPLEXITY_NEED_INTEGER = config.getString("commands.complexity-need-integer");
        COMPLEXITY_RANGE = config.getString("commands.complexity-range");
        RELOAD_MESSAGE = config.getString("commands.reload");
    }
}
