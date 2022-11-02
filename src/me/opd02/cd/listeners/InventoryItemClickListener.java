package me.opd02.cd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.opd02.cd.CraftingDisablerPlugin;

public class InventoryItemClickListener implements Listener {
	
	CraftingDisablerPlugin plugin;
	
	public InventoryItemClickListener(CraftingDisablerPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onItemClick(InventoryClickEvent e){
		
		if(!e.getInventory().getType().equals(InventoryType.SMITHING)) {return;}
		
		if(e.getWhoClicked().hasPermission("craftingdisabler.bypass")){
			return;
		}
		
		if(CraftingDisablerPlugin.blockedRecipies.contains(e.getCurrentItem().getType())){
			e.getWhoClicked().sendMessage(plugin.getConfig().getString("deny-recipe-message").replace('&', ChatColor.COLOR_CHAR));
			e.setCancelled(true);
		}
	}
}
