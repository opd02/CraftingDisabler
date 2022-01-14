package me.opd02.cd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.cd.CraftingDisablerPlugin;

public class PrepareItemCraftListener implements Listener {
	
	CraftingDisablerPlugin plugin;
	
	public PrepareItemCraftListener(CraftingDisablerPlugin plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerPrepareCrafting(PrepareItemCraftEvent e){
		
		Player p = (Player) e.getView().getPlayer();
		
		if(p.hasPermission("craftingdisabler.bypass")){
			return;
		}
		
		if(e.getInventory().getResult() == null){
			return;
		}
		
		ItemStack item = e.getRecipe().getResult();
		String type = item.getType().toString();
		
		if(CraftingDisablerPlugin.blockedRecipies.contains(type)){
			e.getInventory().setResult(new ItemStack(Material.AIR));
			p.sendMessage(plugin.getConfig().getString("deny-recipe-message").replace('&', ChatColor.COLOR_CHAR));
			return;
		}
	}
}
