package me.pastelrobots.usefulfilter;

import com.tchristofferson.configupdater.ConfigUpdater;
import me.pastelrobots.usefulfilter.commands.ReloadCommand;
import me.pastelrobots.usefulfilter.listeners.AdListener;
import me.pastelrobots.usefulfilter.listeners.CapsListener;
import me.pastelrobots.usefulfilter.listeners.SwearListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public final class UsefulFilter extends JavaPlugin {
    private File customConfigFile;
    private FileConfiguration customConfig;
    public static Plugin plugin;
    private Set<String> commands = this.getDescription().getCommands().keySet();

    @Override
    public void onEnable() {
        plugin = this;
        createCustomConfig();
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(plugin, "config.yml", configFile, Arrays.asList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();
        int pluginId = 12522;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        getServer().getPluginManager().registerEvents(new SwearListener(), this);
        getServer().getPluginManager().registerEvents(new CapsListener(), this);
        getServer().getPluginManager().registerEvents(new AdListener(), this);
        Utils.logInfo("Debug mode is enabled!");
        if (plugin.getConfig().getBoolean("console.enabled-msg")) {
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
            Bukkit.getLogger().info(ChatColor.GREEN + "UsefulFilter has been turned on!");
            Bukkit.getLogger().info(ChatColor.GREEN + "If you need help or support join the" + ChatColor.BLUE + " discord.");
            Bukkit.getLogger().info(ChatColor.BLUE + "discord.gg/VtgcZRnmMR");
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
        }
        for (String c : this.commands) {
            Utils.logInfo("Registering the " + c + " command.");
            switch (c) {
                case "reload": {
                    getCommand(c).setExecutor(new ReloadCommand());
                    break;
                }
            }
        }
    }

            @Override
            public void onDisable () {
                if (plugin.getConfig().getBoolean("console.enabled-msg")) {
                    Bukkit.getLogger().info(ChatColor.RED + "=============================================");
                    Bukkit.getLogger().info(ChatColor.BLUE + "UsefulFilter has been turned off!");
                    Bukkit.getLogger().info(ChatColor.BLUE + "If you need help or support join the" + ChatColor.BLUE + " discord.");
                    Bukkit.getLogger().info(ChatColor.DARK_BLUE + "discord.gg/VtgcZRnmMR");
                    Bukkit.getLogger().info(ChatColor.BLUE + "Bye-bye!");
                    Bukkit.getLogger().info(ChatColor.RED + "=============================================");
                }
            }

            public FileConfiguration getCustomConfig() {
                return this.customConfig;
            }

            private void createCustomConfig () {
                customConfigFile = new File(getDataFolder(), "config.yml");
                if (!customConfigFile.exists()) {
                    customConfigFile.getParentFile().mkdirs();
                    saveResource("config.yml", false);
                }

                customConfig = new YamlConfiguration();
                try {
                    customConfig.load(customConfigFile);
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
            }
}
