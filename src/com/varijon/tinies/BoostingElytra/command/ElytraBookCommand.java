package com.varijon.tinies.BoostingElytra.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.yaml.snakeyaml.util.EnumUtils;

import com.varijon.tinies.BoostingElytra.BoostingElytra;
import com.varijon.tinies.BoostingElytra.key.NBTKeys;
import com.varijon.tinies.BoostingElytra.util.Util;

import net.md_5.bungee.api.ChatColor;

public class ElytraBookCommand implements CommandExecutor
{
	BoostingElytra plugin;
	public ElytraBookCommand(BoostingElytra plugin) 
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command var2, String label, String[] args) 
	{
		if(args.length == 0)
		{
			showCommandOptions(sender);
			return true;
		}
		if(args.length > 0)
		{
			List<Entity> targetEntities = sender.getServer().selectEntities(sender, args[0]);
			if(targetEntities.isEmpty())
			{
				sender.sendMessage(ChatColor.RED + "Player not found!");
				return true;
			}
			if(!(targetEntities.get(0) instanceof Player))
			{
				sender.sendMessage(ChatColor.RED + "Target is not a player!");
				return true;
			}
			Player targetPlayer = (Player) targetEntities.get(0);
			if(targetPlayer == null)
			{
				sender.sendMessage(ChatColor.RED + "Player not found!");
				return true;
			}
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.RED + "Missing arguments!");
				return true;
			}
			
			ItemStack elytraBook = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta bookMeta = elytraBook.getItemMeta();
			PersistentDataContainer dataContainer = bookMeta.getPersistentDataContainer();
			
			for(int x=1;x<args.length;x++)
			{
				String arg = args[x];
				String[] splitArg = arg.split(":");
				if(splitArg.length != 2)
				{
					sender.sendMessage(ChatColor.RED + "Invalid argument: " + arg + "!");
					return true;
				}
				NamespacedKey nbtKey = NamespacedKey.fromString(splitArg[0], plugin);
				if(nbtKey == null)
				{
					sender.sendMessage(ChatColor.RED + "Invalid NBT key: " + arg + "!");
					return true;					
				}
				
				dataContainer.set(NBTKeys.isElytraBook, PersistentDataType.INTEGER, 1);		
				
				if(splitArg[0].equals("returnsoldbook"))
				{
					try
					{
						int returnSoldBook = Integer.parseInt(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.INTEGER, returnSoldBook);					
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("flighttime"))
				{
					try
					{
						int flightTime = Integer.parseInt(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.INTEGER, flightTime);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("flightacceleration"))
				{
					try
					{
						float flightAcceleration = Float.parseFloat(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.FLOAT, flightAcceleration);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("flightrecovery"))
				{
					try
					{
						int flightRecovery = Integer.parseInt(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.INTEGER, flightRecovery);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("flightparticle"))
				{
					Particle particle = null;
					
					for(Particle particleEnum : Particle.values())
					{
						if(particleEnum.toString().equals(splitArg[1]))
						{
							particle = particleEnum;
							break;
						}
					}
					
					if(particle == null)
					{
						sender.sendMessage(ChatColor.RED + "Invalid particle: " + splitArg[1] + "!");
						return true;
					}
					dataContainer.set(nbtKey, PersistentDataType.STRING, particle.toString());						
				}
				if(splitArg[0].equals("particleoffset"))
				{
					try
					{
						double particleOffset = Double.parseDouble(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.DOUBLE, particleOffset);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("particlespeed"))
				{
					try
					{
						double particleSpeed = Double.parseDouble(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.DOUBLE, particleSpeed);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("particlecount"))
				{
					try
					{
						int particleCount = Integer.parseInt(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.INTEGER, particleCount);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
				if(splitArg[0].equals("extravalue"))
				{
					dataContainer.set(nbtKey, PersistentDataType.STRING, splitArg[1]);	
				}
				if(splitArg[0].equals("waterboost"))
				{
					try
					{
						int waterBoost = Integer.parseInt(splitArg[1]);
						dataContainer.set(nbtKey, PersistentDataType.INTEGER, waterBoost);						
					}
					catch(NumberFormatException ex)
					{
						sender.sendMessage(ChatColor.RED + "Invalid number: " + splitArg[1] + "!");
						return true;
					}
				}
			}
			if(!dataContainer.has(NBTKeys.extraValue, PersistentDataType.STRING) && dataContainer.has(NBTKeys.flightParticle, PersistentDataType.STRING))
			{
				dataContainer.set(NBTKeys.extraValue, PersistentDataType.STRING, "minecraft:air");				
			}
			if(!dataContainer.has(NBTKeys.returnsOldBook, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.returnsOldBook, PersistentDataType.INTEGER, 0);					
			}
			bookMeta.setDisplayName(ChatColor.GOLD + "Elytra Book");
			
			elytraBook.setItemMeta(bookMeta);
			elytraBook = Util.createElytraBookLore(elytraBook);
			if(!targetPlayer.getInventory().addItem(elytraBook).isEmpty())
			{
				targetPlayer.getWorld().dropItem(targetPlayer.getLocation(), elytraBook);
			}
			return true;
		}
		
		return false;
	}

	void showCommandOptions(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: /elytrabook <playername> <key:value> [key:value] etc.");		
	}

}
