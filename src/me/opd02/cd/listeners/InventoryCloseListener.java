package me.opd02.cd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.cd.CraftingDisablerPlugin;
import me.opd02.cd.utils.JSONUtils;

public class InventoryCloseListener implements Listener {
	
	CraftingDisablerPlugin plugin;
	
	public InventoryCloseListener(CraftingDisablerPlugin plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerCloseInventory(InventoryCloseEvent e){
		
		if(e.getView().getTitle().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "Disabled Crafting Items")){
			
			CraftingDisablerPlugin.blockedRecipies.clear();
			for(ItemStack item : e.getInventory().getContents()){
				if(item==null){
					continue;
				}
				CraftingDisablerPlugin.blockedRecipies.add(item.getType().toString());
			}
			JSONUtils.saveDisableList(CraftingDisablerPlugin.blockedRecipies, plugin);
		}
	}
}
