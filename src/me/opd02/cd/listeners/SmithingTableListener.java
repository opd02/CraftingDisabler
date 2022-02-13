package me.opd02.cd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.cd.CraftingDisablerPlugin;

public class SmithingTableListener implements Listener {

	CraftingDisablerPlugin plugin;
	
	public SmithingTableListener(CraftingDisablerPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSmithingTablePrepare(PrepareSmithingEvent e){
		Player p = (Player) e.getView().getPlayer();
		
		if(p.hasPermission("craftingdisabler.bypass")){
			return;
		}
		
		if(e.getResult() == null){
			return;
		}
		
		ItemStack item = e.getResult();
		String type = item.getType().toString();
		
		if(CraftingDisablerPlugin.blockedRecipies.contains(type)){
			e.setResult(null);
			//p.closeInventory();
			p.getInventory().addItem(e.getInventory().getItem(0));
			e.getInventory().setItem(0, null);
			
			p.updateInventory();
			p.sendMessage(plugin.getConfig().getString("deny-recipe-message").replace('&', ChatColor.COLOR_CHAR));
			return;
		}
	}
//	@EventHandler
//	public void onItemClick(InventoryClickEvent e){
//		if(e.getCurrentItem() == null && e.getCurrentItem().getType().equals(Material.AIR)) return;
//		
//		if(!e.getInventory().getType().equals(InventoryType.SMITHING)) return;
//		
//		Player p = (Player) e.getWhoClicked();
//		
//		if(p.hasPermission("craftingdisabler.bypass")){
//		return;
//	}
//		if(CraftingDisablerPlugin.blockedRecipies.contains(e.getCurrentItem().getType())){
//			e.setCancelled(true);
//			p.sendMessage(plugin.getConfig().getString("deny-recipe-message").replace('&', ChatColor.COLOR_CHAR));
//		}
//	}
}
