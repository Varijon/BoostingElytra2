package com.varijon.tinies.BoostingElytra;



import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.varijon.tinies.BoostingElytra.command.BoostedElytraCommand;
import com.varijon.tinies.BoostingElytra.command.BoostedElytraCommandTabCompleter;
import com.varijon.tinies.BoostingElytra.command.ElytraBookCommand;
import com.varijon.tinies.BoostingElytra.command.ElytraBookCommandTabCompleter;
import com.varijon.tinies.BoostingElytra.config.ConfigurationManager;
import com.varijon.tinies.BoostingElytra.key.NBTKeys;
import com.varijon.tinies.BoostingElytra.listener.ElytraBookListener;
import com.varijon.tinies.BoostingElytra.recipe.ArmoredElytraRecipe;
import com.varijon.tinies.BoostingElytra.task.BoostingElytraTask;


public class BoostingElytra extends JavaPlugin
{
	public ConfigurationManager configManager;
	
	@Override
	public void onEnable() 
	{
		NBTKeys.createNBTKeys(this);
		configManager = new ConfigurationManager(this);
		configManager.loadConfigFile();
		new BoostingElytraTask(this).runTaskTimer(this, 0, 1);
		Bukkit.getPluginManager().registerEvents(new ElytraBookListener(this), this);
		this.getCommand("elytrabook").setExecutor(new ElytraBookCommand(this));
		this.getCommand("elytrabook").setTabCompleter(new ElytraBookCommandTabCompleter());
		this.getCommand("elytraboosted").setExecutor(new BoostedElytraCommand(this));
		this.getCommand("elytraboosted").setTabCompleter(new BoostedElytraCommandTabCompleter());
		
		Bukkit.addRecipe(ArmoredElytraRecipe.getArmoredElytraRecipe(this));
	}
	
	public ConfigurationManager getConfigurationManager()
	{
		return configManager;
	}
}