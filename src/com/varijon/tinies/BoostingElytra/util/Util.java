package com.varijon.tinies.BoostingElytra.util;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import com.varijon.tinies.BoostingElytra.BoostingElytra;
import com.varijon.tinies.BoostingElytra.config.ConfigurationManager;
import com.varijon.tinies.BoostingElytra.key.NBTKeys;

public class Util 
{
	public static ItemStack getAndCompleteBoostingElytra(ItemStack item, BoostingElytra plugin)
	{
		ConfigurationManager configManager = plugin.getConfigurationManager();
		if(item != null)
		{
			if(item.getType() != Material.ELYTRA)
			{
				return null;
			}
			if(!item.hasItemMeta())
			{
				return null;
			}
			ItemMeta elytraMeta = item.getItemMeta();
			PersistentDataContainer dataContainer = elytraMeta.getPersistentDataContainer();
			if(!dataContainer.has(NBTKeys.isBoostingElytra, PersistentDataType.INTEGER))
			{
				return null;
			}
			if(!dataContainer.has(NBTKeys.flightTime, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.flightTime, PersistentDataType.INTEGER, configManager.getDefaultFlightTime());
			}
			if(!dataContainer.has(NBTKeys.flightAcceleration, PersistentDataType.FLOAT))
			{
				dataContainer.set(NBTKeys.flightAcceleration, PersistentDataType.FLOAT, configManager.getDefaultFlightAcceleration());
			}
			if(!dataContainer.has(NBTKeys.flightRecovery, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.flightRecovery, PersistentDataType.INTEGER, configManager.getDefaultFlightRecovery());													
			}
			if(!dataContainer.has(NBTKeys.flightParticle, PersistentDataType.STRING))
			{
				dataContainer.set(NBTKeys.flightParticle, PersistentDataType.STRING, configManager.getDefaultFlightParticle());							
			}
			if(!dataContainer.has(NBTKeys.flightDuration, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.flightDuration, PersistentDataType.INTEGER, configManager.getDefaultFlightTime());						
			}
			if(!dataContainer.has(NBTKeys.particleOffset, PersistentDataType.DOUBLE))
			{
				dataContainer.set(NBTKeys.particleOffset, PersistentDataType.DOUBLE, configManager.getDefaultParticleOffset());													
			}
			if(!dataContainer.has(NBTKeys.particleSpeed, PersistentDataType.DOUBLE))
			{
				dataContainer.set(NBTKeys.particleSpeed, PersistentDataType.DOUBLE, configManager.getDefaultParticleSpeed());						
			}
			if(!dataContainer.has(NBTKeys.particleCount, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.particleCount, PersistentDataType.INTEGER, configManager.getDefaultParticleCount());						
			}
			if(!dataContainer.has(NBTKeys.waterBoost, PersistentDataType.INTEGER))
			{
				dataContainer.set(NBTKeys.waterBoost, PersistentDataType.INTEGER, configManager.getDefaultWaterBoost());						
			}
			if(!dataContainer.has(NBTKeys.extraValue, PersistentDataType.STRING))
			{
				dataContainer.set(NBTKeys.extraValue, PersistentDataType.STRING, configManager.getDefaultExtraValue());						
			}
			
			item.setItemMeta(elytraMeta);
			elytraMeta = item.getItemMeta();
			if(!elytraMeta.hasDisplayName())
			{
				elytraMeta.setDisplayName(configManager.getDefaultElytraName());	
			}
			if(!elytraMeta.hasLore())
			{
				ArrayList<String> loreList = getElytraToolTip(dataContainer);

				elytraMeta.setLore(loreList);
			}
			item.setItemMeta(elytraMeta);
			return item;
		}
		return null;
	}
	
	public static ItemStack checkIfParticleBook(ItemStack book)
	{
		if(book != null)
		{
			if(book.getType() != Material.ENCHANTED_BOOK)
			{
				return null;
			}
			if(!book.hasItemMeta())
			{
				return null;
			}
			ItemMeta bookMeta = book.getItemMeta();
			PersistentDataContainer dataContainer = bookMeta.getPersistentDataContainer();
			if(dataContainer.has(NBTKeys.isElytraBook, PersistentDataType.INTEGER))
			{
				return book;
			}
		}
		return null;
	}
	
	public static ItemStack applyElytraBook(ItemStack elytra, ItemStack book)
	{
		ItemMeta bookMeta = book.getItemMeta();
		PersistentDataContainer dataContainerBook = bookMeta.getPersistentDataContainer();

		ItemMeta elytraMeta = elytra.getItemMeta();
		PersistentDataContainer dataContainerElytra = elytraMeta.getPersistentDataContainer();
		
		ItemStack returnedBook = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta returnedBookMeta = returnedBook.getItemMeta();
		PersistentDataContainer dataContainerReturnedBook = returnedBookMeta.getPersistentDataContainer();
		boolean returnBook = dataContainerBook.get(NBTKeys.returnsOldBook, PersistentDataType.INTEGER) == 1 ? true : false;
		returnedBookMeta.setDisplayName(ChatColor.GOLD + "Elytra Book");
		
		dataContainerReturnedBook.set(NBTKeys.isElytraBook, PersistentDataType.INTEGER, 1);	
		
		if(dataContainerBook.has(NBTKeys.flightAcceleration, PersistentDataType.FLOAT))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.flightAcceleration, PersistentDataType.FLOAT, dataContainerElytra.get(NBTKeys.flightAcceleration, PersistentDataType.FLOAT));
			}
			dataContainerElytra.set(NBTKeys.flightAcceleration, PersistentDataType.FLOAT, dataContainerBook.get(NBTKeys.flightAcceleration, PersistentDataType.FLOAT));
		}
		if(dataContainerBook.has(NBTKeys.flightRecovery, PersistentDataType.INTEGER))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.flightRecovery, PersistentDataType.INTEGER, dataContainerElytra.get(NBTKeys.flightRecovery, PersistentDataType.INTEGER));									
			}
			dataContainerElytra.set(NBTKeys.flightRecovery, PersistentDataType.INTEGER, dataContainerBook.get(NBTKeys.flightRecovery, PersistentDataType.INTEGER));												
		}
		if(dataContainerBook.has(NBTKeys.flightParticle, PersistentDataType.STRING))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.flightParticle, PersistentDataType.STRING, dataContainerElytra.get(NBTKeys.flightParticle, PersistentDataType.STRING));
			}
			dataContainerElytra.set(NBTKeys.flightParticle, PersistentDataType.STRING, dataContainerBook.get(NBTKeys.flightParticle, PersistentDataType.STRING));							
		}
		if(dataContainerBook.has(NBTKeys.flightTime, PersistentDataType.INTEGER))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.flightTime, PersistentDataType.INTEGER, dataContainerElytra.get(NBTKeys.flightTime, PersistentDataType.INTEGER));	
			}
			dataContainerElytra.set(NBTKeys.flightTime, PersistentDataType.INTEGER, dataContainerBook.get(NBTKeys.flightTime, PersistentDataType.INTEGER));	
			dataContainerElytra.set(NBTKeys.flightDuration, PersistentDataType.INTEGER, dataContainerBook.get(NBTKeys.flightTime, PersistentDataType.INTEGER));					
		}
		if(dataContainerBook.has(NBTKeys.particleOffset, PersistentDataType.DOUBLE))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.particleOffset, PersistentDataType.DOUBLE, dataContainerElytra.get(NBTKeys.particleOffset, PersistentDataType.DOUBLE));
			}
			dataContainerElytra.set(NBTKeys.particleOffset, PersistentDataType.DOUBLE, dataContainerBook.get(NBTKeys.particleOffset, PersistentDataType.DOUBLE));												
		}
		if(dataContainerBook.has(NBTKeys.particleSpeed, PersistentDataType.DOUBLE))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.particleSpeed, PersistentDataType.DOUBLE, dataContainerElytra.get(NBTKeys.particleSpeed, PersistentDataType.DOUBLE));	
			}
			dataContainerElytra.set(NBTKeys.particleSpeed, PersistentDataType.DOUBLE, dataContainerBook.get(NBTKeys.particleSpeed, PersistentDataType.DOUBLE));				
		}
		if(dataContainerBook.has(NBTKeys.particleCount, PersistentDataType.INTEGER))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.particleCount, PersistentDataType.INTEGER, dataContainerElytra.get(NBTKeys.particleCount, PersistentDataType.INTEGER));	
			}
			dataContainerElytra.set(NBTKeys.particleCount, PersistentDataType.INTEGER, dataContainerBook.get(NBTKeys.particleCount, PersistentDataType.INTEGER));				
		}
		if(dataContainerBook.has(NBTKeys.waterBoost, PersistentDataType.INTEGER))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.waterBoost, PersistentDataType.INTEGER, dataContainerElytra.get(NBTKeys.waterBoost, PersistentDataType.INTEGER));	
			}
			dataContainerElytra.set(NBTKeys.waterBoost, PersistentDataType.INTEGER, dataContainerBook.get(NBTKeys.waterBoost, PersistentDataType.INTEGER));						
		}
		if(dataContainerBook.has(NBTKeys.extraValue, PersistentDataType.STRING))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.extraValue, PersistentDataType.STRING, dataContainerElytra.get(NBTKeys.extraValue, PersistentDataType.STRING));		
			}
			dataContainerElytra.set(NBTKeys.extraValue, PersistentDataType.STRING, dataContainerBook.get(NBTKeys.extraValue, PersistentDataType.STRING));							
		}
		if(dataContainerBook.has(NBTKeys.returnsOldBook, PersistentDataType.INTEGER))
		{
			if(returnBook)
			{
				dataContainerReturnedBook.set(NBTKeys.returnsOldBook, PersistentDataType.INTEGER, 1);
			}
		}
		
		if(elytraMeta.hasLore())
		{
			if(elytraMeta.getLore().get(0).contains("Flight Time:"))
			{
				elytraMeta.setLore(getElytraToolTip(dataContainerElytra));
			}
		}
		
		elytra.setItemMeta(elytraMeta);
		returnedBook.setItemMeta(returnedBookMeta);
		
		if(returnBook)
		{
			return returnedBook;
		}
		else
		{
			return null;
		}
		
	}
	
	public static ItemStack createElytraBookLore(ItemStack book)
	{
		ItemMeta bookMeta = book.getItemMeta();
		PersistentDataContainer dataContainer = bookMeta.getPersistentDataContainer();
		
		ArrayList<String> loreList = new ArrayList<String>();

		DecimalFormat df2 = new DecimalFormat("###.###");
		if(dataContainer.has(NBTKeys.flightTime, PersistentDataType.INTEGER))
		{
			loreList.add(ChatColor.AQUA + "Flight Time: " + ChatColor.GRAY + dataContainer.get(NBTKeys.flightTime, PersistentDataType.INTEGER));			
		}
		if(dataContainer.has(NBTKeys.flightAcceleration, PersistentDataType.FLOAT))
		{
			loreList.add(ChatColor.AQUA + "Flight Acceleration: " + ChatColor.GRAY + df2.format(dataContainer.get(NBTKeys.flightAcceleration, PersistentDataType.FLOAT)));
		}
		if(dataContainer.has(NBTKeys.flightRecovery, PersistentDataType.INTEGER))
		{
			loreList.add(ChatColor.AQUA + "Flight Recovery: " + ChatColor.GRAY + dataContainer.get(NBTKeys.flightRecovery, PersistentDataType.INTEGER));
		}
		if(dataContainer.has(NBTKeys.flightParticle, PersistentDataType.STRING))
		{
			loreList.add(ChatColor.AQUA + "Boost Particle: " + ChatColor.GRAY + (!dataContainer.get(NBTKeys.extraValue, PersistentDataType.STRING).contains("minecraft:air") ? ChatColor.GRAY + dataContainer.get(NBTKeys.extraValue, PersistentDataType.STRING) : dataContainer.get(NBTKeys.flightParticle, PersistentDataType.STRING)));
		}
		if(dataContainer.has(NBTKeys.particleCount, PersistentDataType.INTEGER))
		{
			loreList.add(ChatColor.AQUA + "Particle Count: " + ChatColor.GRAY + dataContainer.get(NBTKeys.particleCount, PersistentDataType.INTEGER));
		}
		if(dataContainer.has(NBTKeys.waterBoost, PersistentDataType.INTEGER))
		{
			loreList.add(ChatColor.AQUA + "Water Boost: " + ChatColor.GRAY + (dataContainer.get(NBTKeys.waterBoost, PersistentDataType.INTEGER) == 0 ? "No" : "Yes"));			
		}
		if(dataContainer.has(NBTKeys.returnsOldBook, PersistentDataType.INTEGER))
		{
			loreList.add(ChatColor.AQUA + "Returns old book: " + ChatColor.GRAY + (dataContainer.get(NBTKeys.returnsOldBook, PersistentDataType.INTEGER) == 0 ? "No" : "Yes"));			
		}
		bookMeta.setLore(loreList);
		book.setItemMeta(bookMeta);
		
		return book;
	}
	
	public static void addBoostToPlayer(Player player, float flightAcceleration)
	{
		float yaw = player.getLocation().getYaw();
		float pitch = player.getLocation().getPitch();
		float f = flightAcceleration;
		double motionX = (double)(-Math.sin(yaw / 180.0F * (float)Math.PI) * Math.cos(pitch / 180.0F * (float)Math.PI) * f);
		double motionZ = (double)(Math.cos(yaw / 180.0F * (float)Math.PI) * Math.cos(pitch / 180.0F * (float)Math.PI) * f);
		double motionY = (double)(-Math.sin((pitch) / 180.0F * (float)Math.PI) * f);
		player.setVelocity(player.getVelocity().add(new Vector(motionX, motionY, motionZ)));
	}
	
	public static boolean isPlayerSneakGliding(Player player)
	{
		if(!player.isGliding())
		{
			return false;
		}
		if(!player.isSneaking())
		{
			return false;
		}
		return true;
	}
	
	public static boolean isValidTakeoffConditions(Player player, BoostingElytra plugin)
	{
		ConfigurationManager configManager = plugin.getConfigurationManager();
		if(((Entity)player).isOnGround())
		{
			return false;
		}
		if(!player.isSneaking())
		{
			return false;
		}
		if(player.isGliding())
		{
			return false;
		}
		if(!(player.getLocation().getPitch() < configManager.getTakeoffAngle()))
		{
			return false;
		}
		return true;
	}
	
	public static String getFlightBarString(float elytraMaxFlightTime, float elytraFlightDuration)
	{
		StringBuilder pipes = new StringBuilder();
		int whitePipes = (int) (((double)elytraFlightDuration / (double)elytraMaxFlightTime) * (double)50);
		pipes.append(ChatColor.AQUA + "");
		for(int x = 0; x < whitePipes; x++)
		{
			pipes.append("|");
		}
		pipes.append(ChatColor.DARK_GRAY);
		for(int x = 0; x < (50 - whitePipes); x++)
		{
				pipes.append("|");
		}
		return pipes.toString();
	}
	
	public static ArrayList<String> getElytraToolTip(PersistentDataContainer dataContainer)
	{
		ArrayList<String> loreList = new ArrayList<>();
		DecimalFormat df2 = new DecimalFormat("###.###");
		loreList.add(ChatColor.AQUA + "Flight Time: " + ChatColor.GRAY + dataContainer.get(NBTKeys.flightTime, PersistentDataType.INTEGER));
		loreList.add(ChatColor.AQUA + "Flight Acceleration: " + ChatColor.GRAY + df2.format(dataContainer.get(NBTKeys.flightAcceleration, PersistentDataType.FLOAT)));
		loreList.add(ChatColor.AQUA + "Flight Recovery: " + ChatColor.GRAY + dataContainer.get(NBTKeys.flightRecovery, PersistentDataType.INTEGER));
		loreList.add(ChatColor.AQUA + "Boost Particle: " + ChatColor.GRAY + (!dataContainer.get(NBTKeys.extraValue, PersistentDataType.STRING).contains("minecraft:air") ? ChatColor.GRAY + dataContainer.get(NBTKeys.extraValue, PersistentDataType.STRING) : dataContainer.get(NBTKeys.flightParticle, PersistentDataType.STRING)));
		loreList.add(ChatColor.AQUA + "Particle Count: " + ChatColor.GRAY + dataContainer.get(NBTKeys.particleCount, PersistentDataType.INTEGER));
		loreList.add(ChatColor.AQUA + "Water Boost: " + ChatColor.GRAY + (dataContainer.get(NBTKeys.waterBoost, PersistentDataType.INTEGER) == 0 ? "No" : "Yes"));
		loreList.add(ChatColor.GRAY + "Hold Sneak to boost while flying");
		loreList.add(ChatColor.GRAY + "Hold Sneak and jump while looking up to take off");
		
		return loreList;
	}
}
