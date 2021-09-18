package me.pastelrobots.usefulfilter;

import com.tchristofferson.configupdater.ConfigUpdater;
import me.pastelrobots.usefulfilter.commands.BroadcastCommand;
import me.pastelrobots.usefulfilter.commands.MessageCommand;
import me.pastelrobots.usefulfilter.commands.ReloadCommand;
import me.pastelrobots.usefulfilter.listeners.*;
import me.pastelrobots.usefulfilter.utils.ConfigFile;
import me.pastelrobots.usefulfilter.utils.Metrics;
import me.pastelrobots.usefulfilter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public final class UsefulChat extends JavaPlugin {
    private File customConfigFile;
    private FileConfiguration customConfig;
    public static Plugin plugin;
    private Set<String> commands = this.getDescription().getCommands().keySet();

    @Override
    public void onEnable() {
        plugin = this;
        onConfigNotExisting();
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(plugin, "config.yml", configFile, Collections.emptyList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();
        int pluginId = 12522;
        Metrics metrics = new Metrics(this, pluginId);

        getServer().getPluginManager().registerEvents(new SwearListener(), this);
        getServer().getPluginManager().registerEvents(new CapsListener(), this);
        getServer().getPluginManager().registerEvents(new LinkListener(), this);
        getServer().getPluginManager().registerEvents(new SpamListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new BotListener(), this);
        getServer().getPluginManager().registerEvents(new UnicodeListener(), this);

        Utils.logInfo("Debug mode is enabled!");
        if (plugin.getConfig().getBoolean("console.enabled-msg")) {
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
            Bukkit.getLogger().info(ChatColor.GREEN + "UsefulChat has been turned on!");
            Bukkit.getLogger().info(ChatColor.GREEN + "If you need help or support join the" + ChatColor.BLUE + " discord.");
            Bukkit.getLogger().info(ChatColor.BLUE + "discord.gg/VtgcZRnmMR");
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
        }
        for (String c : this.commands) {
            Utils.logInfo("Registering the " + c + " command.");
            switch (c) {
                case "reload" -> {
                    getCommand(c).setExecutor(new ReloadCommand());
                }
                case "message" -> {
                    getCommand(c).setExecutor(new MessageCommand());
                }
                case "broadcast" -> {
                    getCommand(c).setExecutor(new BroadcastCommand());
                }
            }
        }
    }

            @Override
            public void onDisable () {
                if (plugin.getConfig().getBoolean("console.enabled-msg")) {
                    Bukkit.getLogger().info(ChatColor.RED + "=============================================");
                    Bukkit.getLogger().info(ChatColor.BLUE + "UsefulChat has been turned off!");
                    Bukkit.getLogger().info(ChatColor.BLUE + "If you need help or support join the" + ChatColor.BLUE + " discord.");
                    Bukkit.getLogger().info(ChatColor.DARK_BLUE + "discord.gg/VtgcZRnmMR");
                    Bukkit.getLogger().info(ChatColor.BLUE + "Bye-bye!");
                    Bukkit.getLogger().info(ChatColor.RED + "=============================================");
                }
            }


    public void onConfigNotExisting() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            ConfigFile.setup();
            ConfigFile.save();
            ConfigFile.reload();
        }
    }
}
