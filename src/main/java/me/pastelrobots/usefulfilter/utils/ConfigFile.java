package me.pastelrobots.usefulfilter.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private static File file;
    private static FileConfiguration config;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("TrollCommandsDeluxe").getDataFolder(), "config.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return config;
    }

    public static void save(){
        try{
            config.save(file);
        }catch (IOException e){
            Bukkit.getLogger().info("Couldn't save file");
        }
    }

    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
