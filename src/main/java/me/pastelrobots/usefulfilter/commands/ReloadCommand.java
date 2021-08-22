package me.pastelrobots.usefulfilter.commands;

import me.pastelrobots.usefulfilter.UsefulFilter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if(s instanceof Player p) {
            if(p.hasPermission("usefulfilter.reload")) {
                UsefulFilter.plugin.reloadConfig();
                UsefulFilter.plugin.getPluginLoader().disablePlugin(UsefulFilter.plugin);
                UsefulFilter.plugin.getPluginLoader().enablePlugin(UsefulFilter.plugin);
                p.sendMessage(ChatColor.GREEN + "Config reloaded!");
                Bukkit.getLogger().info(ChatColor.GREEN + "Config reloaded!");
            }
            } else {
            UsefulFilter.plugin.reloadConfig();
            UsefulFilter.plugin.getPluginLoader().disablePlugin(UsefulFilter.plugin);
            UsefulFilter.plugin.getPluginLoader().enablePlugin(UsefulFilter.plugin);
            Bukkit.getLogger().info(ChatColor.GREEN + "Config reloaded!");
        }
        return true;
    }
}
