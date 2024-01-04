package dev.twme.gradientblock;

import dev.twme.gradientblock.commands.MainCommand;
import dev.twme.gradientblock.listener.GuiListener;
import dev.twme.gradientblock.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradientBlock extends JavaPlugin {

    private static GradientBlock instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigUtil.loadConfig();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        this.getCommand("gradientblock").setTabCompleter(new MainCommand());
        this.getCommand("gradientblock").setExecutor(new MainCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
    }

    public static GradientBlock getInstance() {
        return instance;
    }

    public static void reload() {
        ConfigUtil.loadConfig();
    }
}
