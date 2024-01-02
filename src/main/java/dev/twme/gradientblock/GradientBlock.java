package dev.twme.gradientblock;

import dev.twme.gradientblock.commands.MainCommand;
import dev.twme.gradientblock.listener.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradientBlock extends JavaPlugin {

    private static GradientBlock instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("gradientblock").setExecutor(new MainCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
    }

    public static GradientBlock getInstance() {
        return instance;
    }
}
