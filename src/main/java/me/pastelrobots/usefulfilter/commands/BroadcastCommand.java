package me.pastelrobots.usefulfilter.commands;

import me.pastelrobots.usefulfilter.UsefulChat;
import me.pastelrobots.usefulfilter.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if(!Config.getBoolean("commands.broadcast-command.enabled")) return true;
        if(!s.hasPermission("usefulchat.broadcast")) {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("commands.broadcast-command.no-permission")));
            return true;
        }
        StringBuilder message = new StringBuilder();
        for(int i = 0; i != args.length; i++) {
            assert false;
            message.append(args[i]).append(" ");
        }

        s.sendMessage(ChatColor.GREEN + "Successfully broadcasted the message!");
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("commands.broadcast-command.message").replace("%message%", ChatColor.translateAlternateColorCodes('&', message.toString()))));
        }
        UsefulChat.plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', Config.getString("commands.broadcast-command.message").replace("%message%", ChatColor.translateAlternateColorCodes('&', message.toString()))));
        return true;
    }
}
