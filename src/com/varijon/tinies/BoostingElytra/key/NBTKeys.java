package com.varijon.tinies.BoostingElytra.key;

import org.bukkit.NamespacedKey;

import com.varijon.tinies.BoostingElytra.BoostingElytra;

public class NBTKeys 
{
	public static NamespacedKey isBoostingElytra;
	public static NamespacedKey isElytraBook;
	public static NamespacedKey returnsOldBook;
	public static NamespacedKey flightTime;
	public static NamespacedKey flightDuration;
	public static NamespacedKey flightAcceleration;
	public static NamespacedKey flightRecovery;
	public static NamespacedKey flightParticle;
	public static NamespacedKey particleOffset;
	public static NamespacedKey particleSpeed;
	public static NamespacedKey particleCount;
	public static NamespacedKey extraValue;
	public static NamespacedKey waterBoost;
	public static NamespacedKey finishCharging;
	
	public static void createNBTKeys(BoostingElytra plugin)
	{
		isBoostingElytra = new NamespacedKey(plugin, "isBoostingElytra");
		isElytraBook = new NamespacedKey(plugin, "isElytraBook");
		returnsOldBook = new NamespacedKey(plugin, "returnsOldBook");
		flightTime = new NamespacedKey(plugin, "flightTime");
		flightDuration = new NamespacedKey(plugin, "flightDuration");
		flightAcceleration = new NamespacedKey(plugin, "flightAcceleration");
		flightRecovery = new NamespacedKey(plugin, "flightRecovery");
		flightParticle = new NamespacedKey(plugin, "flightParticle");
		particleOffset = new NamespacedKey(plugin, "particleOffset");
		particleSpeed = new NamespacedKey(plugin, "particleSpeed");
		particleCount = new NamespacedKey(plugin, "particleCount");
		extraValue = new NamespacedKey(plugin, "extraValue");
		waterBoost = new NamespacedKey(plugin, "waterBoost");
		finishCharging = new NamespacedKey(plugin, "finishCharging");
	}
}
