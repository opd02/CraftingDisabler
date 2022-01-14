package me.opd02.cd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.opd02.cd.CraftingDisablerPlugin;

public class MenuCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if(cmd.getLabel().equalsIgnoreCase("cd")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only.");
				return true;
			}
			
			Player player = (Player) sender;
			
			if(!player.hasPermission("craftingdisabler.admincommand")){
				
				player.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
				return true;
				
			}
			
			Inventory inv = Bukkit.getServer().createInventory(null, 45, ChatColor.GRAY + "" + ChatColor.BOLD + "Disabled Crafting Items");
			
			for(String s : CraftingDisablerPlugin.blockedRecipies){
				if(s.equals(null) || s.equals("AIR")){
					continue;
				}
				ItemStack i = new ItemStack(Material.valueOf(s));
				inv.addItem(i);
			}
			player.openInventory(inv);
		}
		return false;
	}

}
