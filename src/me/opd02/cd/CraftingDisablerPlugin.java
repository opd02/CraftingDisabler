package me.opd02.cd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.opd02.cd.commands.MenuCommand;
import me.opd02.cd.listeners.InventoryCloseListener;
import me.opd02.cd.listeners.PrepareItemCraftListener;
import me.opd02.cd.utils.JSONUtils;

public class CraftingDisablerPlugin extends JavaPlugin{

	public static ArrayList<String> blockedRecipies;
	
	public void onEnable(){
		
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PrepareItemCraftListener(this), this);
		
		CraftingDisablerPlugin.blockedRecipies = new ArrayList<String>();
		
		JSONUtils.makeFile(this);
		
		blockedRecipies = JSONUtils.readSavedList(this);
		
		Bukkit.getServer().getPluginCommand("cd").setExecutor(new MenuCommand());
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	public void onDisable(){
		
		JSONUtils.saveDisableList(blockedRecipies, this);
		
	}
	
}
