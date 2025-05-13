package com.varijon.tinies.BoostingElytra.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.varijon.tinies.BoostingElytra.BoostingElytra;
import com.varijon.tinies.BoostingElytra.util.Util;

import net.md_5.bungee.api.ChatColor;


public class ElytraBookListener implements Listener
{
	BoostingElytra plugin;
	public ElytraBookListener(BoostingElytra plugin) 
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onItemClick(PlayerInteractEvent event)
	{
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR)
		{
			return;
		}
		if(event.getHand() != EquipmentSlot.HAND)
		{
			return;
		}
		ItemStack book = Util.checkIfParticleBook(event.getItem());
		if(book == null)
		{
			return;
		}
		ItemStack elytra = Util.getAndCompleteBoostingElytra(event.getPlayer().getEquipment().getChestplate(), plugin);
		if(elytra == null)
		{
			event.getPlayer().sendMessage(ChatColor.RED + "Not wearing a valid Elytra!");
			return;
		}
		ItemStack returnedBook = Util.applyElytraBook(elytra, book);
		event.getPlayer().sendMessage(ChatColor.GREEN + "Elytra Book applied!");
		event.getPlayer().getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
		if(returnedBook == null)
		{
			return;
		}
		returnedBook = Util.createElytraBookLore(returnedBook);
		if(!event.getPlayer().getInventory().addItem(returnedBook).isEmpty())
		{
			event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), returnedBook);
		}
	}
}
