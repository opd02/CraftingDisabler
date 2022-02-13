package me.opd02.cd.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.cd.CraftingDisablerPlugin;

public class FurnacePrepareListener implements Listener {
	
	CraftingDisablerPlugin plugin;
	
	public FurnacePrepareListener(CraftingDisablerPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onFurnacePrepare(FurnaceStartSmeltEvent e){
		
		if(e.getRecipe().getResult() == null){
			return;
		}
		
		ItemStack item = e.getRecipe().getResult();
		String type = item.getType().toString();
		
		if(CraftingDisablerPlugin.blockedRecipies.contains(type)){
			e.setTotalCookTime(-1);
			return;
		}
	}
}
