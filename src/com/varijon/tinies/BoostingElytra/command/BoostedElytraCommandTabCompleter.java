package com.varijon.tinies.BoostingElytra.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.varijon.tinies.BoostingElytra.BoostingElytra;

public class BoostedElytraCommandTabCompleter implements TabCompleter
{
	ArrayList<String> lstBlocks;
	ArrayList<String> lstParticles;
	
	public BoostedElytraCommandTabCompleter() 
	{
		lstBlocks = new ArrayList<>();
		for(Material material : Material.values())
		{
			if(material.isBlock())
			{
				lstBlocks.add("extravalue:" + material.toString());							
			}
		}
		lstParticles = new ArrayList<>();
		for(Particle particle : Particle.values())
		{
			lstParticles.add("flightparticle:" + particle);
		}
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) 
	{
		if(args.length == 1)
		{
			return sender.getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
		}
		if(args.length > 1)
		{
			ArrayList<String> lstTabComplete1 = new ArrayList<>();
			lstTabComplete1.add("flighttime");
			lstTabComplete1.add("flightacceleration");
			lstTabComplete1.add("flightrecovery");
			lstTabComplete1.add("flightparticle");
			lstTabComplete1.add("particleoffset");
			lstTabComplete1.add("particlespeed");
			lstTabComplete1.add("particlecount");
			lstTabComplete1.add("extravalue");
			lstTabComplete1.add("waterboost");
			lstTabComplete1.add("armored");
			
			ArrayList<String> lstTabComplete2 = new ArrayList<>();
			if(args[args.length-1].contains("flightparticle"))
			{
				return StringUtil.copyPartialMatches(args[args.length-1], lstParticles, new ArrayList<>());
			}
			if(args[args.length-1].contains("extravalue"))
			{
				return StringUtil.copyPartialMatches(args[args.length-1], lstBlocks, new ArrayList<>());
			}
			if(args[args.length-1].contains("waterboost"))
			{
				lstTabComplete2.add("waterboost:0");
				lstTabComplete2.add("waterboost:1");	

				return StringUtil.copyPartialMatches(args[args.length-1], lstTabComplete2, new ArrayList<>());			
			}
			return StringUtil.copyPartialMatches(args[args.length-1], lstTabComplete1, new ArrayList<>());	
		}
		return Collections.emptyList();
	}

}
