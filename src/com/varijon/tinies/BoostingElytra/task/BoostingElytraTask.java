package com.varijon.tinies.BoostingElytra.task;



import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import com.varijon.tinies.BoostingElytra.BoostingElytra;
import com.varijon.tinies.BoostingElytra.config.ConfigurationManager;
import com.varijon.tinies.BoostingElytra.key.NBTKeys;
import com.varijon.tinies.BoostingElytra.util.Util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;



public class BoostingElytraTask extends BukkitRunnable
{
	BoostingElytra plugin;
	Server server;
	ConfigurationManager configManager;
	int tickTPSCount = 0;
	long oldTime = System.currentTimeMillis();
	float tps = 20;
		
	public BoostingElytraTask(BoostingElytra boostingElytra) 
	{
		server = Bukkit.getServer();
		plugin = boostingElytra; 
		configManager = plugin.getConfigurationManager();
	}
	

	@Override
	public void run() 
	{
		try
		{
			if(tickTPSCount >= 20)
			{
				tickTPSCount = 0;
				long newTime = System.currentTimeMillis();
				long timeDiff = newTime - oldTime;
				oldTime = newTime;
				tps = 20 * (1000f / timeDiff);
				if(tps > 20)
				{
					tps = 20;
				}
			}
			tickTPSCount++;
			for(Player bukkitPlayer : Bukkit.getOnlinePlayers())
			{
				ItemStack elytra = Util.getAndCompleteBoostingElytra(bukkitPlayer.getInventory().getChestplate(), plugin);
				if(elytra != null)
				{
					//attempt porting tps lag scaling feature from forge
					//command for giving particlebooks, command for editing worn elytra
					//fix riptiding boost
					ItemMeta elytraMeta = elytra.getItemMeta();
					PersistentDataContainer container = elytraMeta.getPersistentDataContainer();
					int maxFlightTime = container.get(NBTKeys.flightTime,PersistentDataType.INTEGER);
					float flightAcceleration = container.get(NBTKeys.flightAcceleration,PersistentDataType.FLOAT);
					int flightTimeRecovery = container.get(NBTKeys.flightRecovery,PersistentDataType.INTEGER);
					String flightParticle = container.get(NBTKeys.flightParticle,PersistentDataType.STRING);
					int flightDuration = container.get(NBTKeys.flightDuration,PersistentDataType.INTEGER);
					double particleOffset = container.get(NBTKeys.particleOffset,PersistentDataType.DOUBLE);
					double particleSpeed = container.get(NBTKeys.particleSpeed,PersistentDataType.DOUBLE);
					int particleCount = container.get(NBTKeys.particleCount,PersistentDataType.INTEGER);
					String blockData = "minecraft:air";
					if(container.has(NBTKeys.extraValue,PersistentDataType.STRING))
					{
						if(!container.get(NBTKeys.extraValue,PersistentDataType.STRING).contains("minecraft:air"))
						{
							blockData = container.get(NBTKeys.extraValue,PersistentDataType.STRING);
						}
					}
					int waterBoost = container.get(NBTKeys.waterBoost,PersistentDataType.INTEGER);
					
					if(bukkitPlayer.isRiptiding())
					{
						continue;
					}
					
					if(Util.isPlayerSneakGliding(bukkitPlayer))
					{
						if(flightDuration > maxFlightTime)
						{
							flightDuration = maxFlightTime;
						}
						if(flightDuration > 0)
						{
							float tpsMultiplier = (20f / tps);
							if(tpsMultiplier > 2)
							{
								tpsMultiplier = 2;
							}
							
							if(bukkitPlayer.isInWater() && waterBoost == 1)
							{
								flightAcceleration *= configManager.getWaterBoostAccelerationMultiplier();
								bukkitPlayer.spawnParticle(Particle.BUBBLE, bukkitPlayer.getLocation().getX(), bukkitPlayer.getLocation().getY(), bukkitPlayer.getLocation().getZ(), 10, 0.5, 0.5, 0.5, 0, null);
							}
							Util.addBoostToPlayer(bukkitPlayer, flightAcceleration * tpsMultiplier);
							flightDuration -= 1;
							container.set(NBTKeys.flightDuration,PersistentDataType.INTEGER, flightDuration);
							if(Particle.valueOf(flightParticle) != null)
							{
								bukkitPlayer.getWorld().spawnParticle(Particle.valueOf(flightParticle), bukkitPlayer.getLocation().getX(), bukkitPlayer.getLocation().getY(), bukkitPlayer.getLocation().getZ(), particleCount, particleOffset, particleOffset, particleOffset, particleSpeed,(!blockData.contains("minecraft:air") ? Material.getMaterial(blockData).createBlockData() : null));	
							}
						}
					}
					if(Util.isValidTakeoffConditions(bukkitPlayer, plugin))
					{
						if(flightDuration > maxFlightTime)
						{
							flightDuration = maxFlightTime;
						}
						int amountToTake = (int) ((maxFlightTime / 100) * configManager.getTakeoffBoostCostPercentage());
						if(flightDuration > amountToTake)
						{
							if(bukkitPlayer.isInWater() && waterBoost == 1)
							{
								flightAcceleration /= 10;
							}
							
							Util.addBoostToPlayer(bukkitPlayer, flightAcceleration * configManager.getTakeoffBoostMultiplier());

							bukkitPlayer.setGliding(true);
							container.set(NBTKeys.flightDuration,PersistentDataType.INTEGER, flightDuration - amountToTake);
							if(Particle.valueOf(flightParticle) != null)
							{	
								bukkitPlayer.getWorld().spawnParticle(Particle.valueOf(flightParticle), bukkitPlayer.getLocation().getX(), bukkitPlayer.getLocation().getY(), bukkitPlayer.getLocation().getZ(), configManager.getTakeoffParticleCount(), configManager.getTakeoffParticleSpread(), configManager.getTakeoffParticleSpread(), configManager.getTakeoffParticleSpread(), 0, (!blockData.contains("minecraft:air") ? Material.getMaterial(blockData).createBlockData() : null));
							}	
						}
					}
					if(((Entity)bukkitPlayer).isOnGround() && flightDuration < maxFlightTime)
					{
						flightDuration = flightDuration + flightTimeRecovery;
						container.set(NBTKeys.flightDuration,PersistentDataType.INTEGER, flightDuration);
						if(flightDuration + flightTimeRecovery >= maxFlightTime)
						{
							container.set(NBTKeys.finishCharging, PersistentDataType.INTEGER, 1);
						}
					}
//					if (bukkitPlayer.isInWater() && flightDuration < maxFlightTime && waterBoost == 1)
//					{
//						container.set(NBTKeys.flightDuration,PersistentDataType.FLOAT, flightDuration + (flightTimeRecovery / ConfigurationManager.WATERBOOST_RECOVERY_MULTIPLIER));
//					}
					if(flightDuration < maxFlightTime)
					{					
						bukkitPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(Util.getFlightBarString(maxFlightTime, flightDuration)));
					}
					if(container.has(NBTKeys.finishCharging, PersistentDataType.INTEGER))
					{
						if(container.get(NBTKeys.finishCharging, PersistentDataType.INTEGER) == 1 && flightDuration >= maxFlightTime)
						{
							flightDuration = maxFlightTime;
							container.set(NBTKeys.flightDuration,PersistentDataType.INTEGER, flightDuration);
							bukkitPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(Util.getFlightBarString(maxFlightTime, flightDuration)));

							container.set(NBTKeys.finishCharging, PersistentDataType.INTEGER, 0);
						}
					}
					elytra.setItemMeta(elytraMeta);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
