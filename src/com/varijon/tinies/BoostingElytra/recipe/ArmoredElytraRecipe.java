package com.varijon.tinies.BoostingElytra.recipe;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.varijon.tinies.BoostingElytra.BoostingElytra;
import com.varijon.tinies.BoostingElytra.config.ConfigurationManager;
import com.varijon.tinies.BoostingElytra.key.NBTKeys;

import net.md_5.bungee.api.ChatColor;

public class ArmoredElytraRecipe 
{
	public static ShapedRecipe getArmoredElytraRecipe(BoostingElytra plugin)
	{
		ConfigurationManager configManager = plugin.getConfigurationManager();
		
		ItemStack armorElytra = new ItemStack(Material.ELYTRA);
		ItemMeta meta = armorElytra.getItemMeta();
		
		meta.setDisplayName(ChatColor.RESET + "Armored Elytra");
		ArrayList<String> loreList = new ArrayList<String>();
	
		loreList.add(ChatColor.GRAY + "Hold Sneak to boost while flying");
		loreList.add(ChatColor.GRAY + "Hold Sneak and jump while looking up to take off");
		meta.setLore(loreList);
		
		armorElytra.setItemMeta(meta);
			
		PersistentDataContainer container = meta.getPersistentDataContainer();
		container.set(NBTKeys.isBoostingElytra, PersistentDataType.INTEGER, 1);	
		container.set(NBTKeys.flightDuration, PersistentDataType.INTEGER, configManager.getDefaultFlightTime());	
		container.set(NBTKeys.flightAcceleration, PersistentDataType.FLOAT, configManager.getDefaultFlightAcceleration());
		container.set(NBTKeys.flightRecovery, PersistentDataType.INTEGER, configManager.getDefaultFlightRecovery());		
		container.set(NBTKeys.flightParticle, PersistentDataType.STRING, configManager.getDefaultFlightParticle());		
		container.set(NBTKeys.particleOffset, PersistentDataType.DOUBLE, configManager.getDefaultParticleOffset());		
		container.set(NBTKeys.particleSpeed, PersistentDataType.DOUBLE, configManager.getDefaultParticleSpeed());		
		container.set(NBTKeys.particleCount, PersistentDataType.INTEGER, configManager.getDefaultParticleCount());	
		container.set(NBTKeys.extraValue, PersistentDataType.STRING, configManager.getDefaultExtraValue());			

		armorElytra.setItemMeta(meta);
		
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"armor", 2, Operation.ADD_NUMBER,EquipmentSlot.CHEST));
		meta.setUnbreakable((configManager.getDefaultUnbreakable() == 1 ? true : false));
		meta.setRarity(ItemRarity.EPIC);
		meta.setFireResistant(true);
		armorElytra.setItemMeta(meta);
		
		NamespacedKey key = new NamespacedKey(plugin, "armored_elytra");
		
		ShapedRecipe recipe = new ShapedRecipe(key, armorElytra);
		
		recipe.shape("ISI","IEI","RAR");
		 
		recipe.setIngredient('I', Material.NETHERITE_INGOT);
		recipe.setIngredient('S', Material.NETHER_STAR);
		recipe.setIngredient('E', Material.ELYTRA);
		recipe.setIngredient('R', Material.BREEZE_ROD);
		recipe.setIngredient('A', Material.NETHERITE_CHESTPLATE);
		 
		return recipe;
	}
}
