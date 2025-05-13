package com.varijon.tinies.BoostingElytra.config;

import com.varijon.tinies.BoostingElytra.BoostingElytra;

public class ConfigurationManager 
{
//	public static int DEFAULT_FLIGHT_TIME = 240;
//	public static float DEFAULT_FLIGHT_ACCELERATION = 0.038F;
//	public static int DEFAULT_FLIGHT_RECOVERY = 4;
//	public static String DEFAULT_FLIGHT_PARTICLE = "END_ROD";
//	public static String DEFAULT_EXTRA_VALUE = "minecraft:air";
//	public static double DEFAULT_PARTICLE_OFFSET = 1.5;
//	public static double DEFAULT_PARTICLE_SPEED = 0;
//	public static int DEFAULT_PARTICLE_COUNT = 5;
//	public static int DEFAULT_WATERBOOST_TOGGLE = 0;
//	public static String DEFAULT_ELYTRA_NAME = ChatColor.YELLOW + "Boosting Elytra";
//	public static float TAKEOFF_ANGLE = -30;
//	public static float TAKEOFF_BOOST_COST_PERCENTAGE = 30;
//	public static float WATERBOOST_ACCELERATION_MULTIPLIER = 10;
//	public static float TAKEOFF_BOOST_MULTIPLIER = 15;
//	public static int TAKEOFF_PARTICLE_COUNT = 50;
//	public static double TAKEOFF_PARTICLE_SPREAD = 1.5;
	
	int defaultFlightTime;
	float defaultFlightAcceleration;
	int defaultFlightRecovery;
	String defaultFlightParticle;
	String defaultExtraValue;
	double defaultParticleOffset;
	double defaultParticleSpeed;
	int defaultParticleCount;
	int defaultWaterBoost;
	int defaultUnbreakable;
	String defaultElytraName;
	float takeoffAngle;
	float waterBoostAccelerationMultiplier;
	float takeoffBoostMultiplier;
	float takeoffBoostCostPercentage;
	int takeoffParticleCount;
	double takeoffParticleSpread;
	int enableArmoredElytraRecipe;
	
	BoostingElytra plugin;
	
	public ConfigurationManager(BoostingElytra plugin)
	{
		plugin.saveDefaultConfig();
		this.plugin = plugin;
	}
	
	public void loadConfigFile()
	{
		defaultFlightTime = plugin.getConfig().getInt("default-flight-time");
		defaultFlightAcceleration = (float) plugin.getConfig().getDouble("default-flight-acceleration");
		defaultFlightRecovery = plugin.getConfig().getInt("default-flight-recovery");
		defaultFlightParticle = plugin.getConfig().getString("default-flight-particle");
		defaultExtraValue = plugin.getConfig().getString("default-extra-value");
		defaultParticleOffset = plugin.getConfig().getDouble("default-particle-offset");
		defaultParticleSpeed = plugin.getConfig().getDouble("default-particle-speed");
		defaultParticleCount = plugin.getConfig().getInt("default-particle-count");
		defaultWaterBoost = plugin.getConfig().getBoolean("default-waterboost") ? 1 : 0;
		defaultUnbreakable = plugin.getConfig().getBoolean("default-unbreakable") ? 1 : 0;
		defaultElytraName = plugin.getConfig().getString("default-elytra-name");
		takeoffAngle = (float) plugin.getConfig().getDouble("takeoff-angle");
		takeoffBoostCostPercentage = (float) plugin.getConfig().getDouble("takeoff-boost-cost-percentage");
		waterBoostAccelerationMultiplier = (float) plugin.getConfig().getDouble("waterboost-acceleration-multiplier");
		takeoffBoostMultiplier = (float) plugin.getConfig().getDouble("takeoff-boost-multiplier");
		takeoffParticleCount = plugin.getConfig().getInt("takeoff-particle-count");
		takeoffParticleSpread = plugin.getConfig().getDouble("takeoff-particle-spread");
	}
	
	public int getDefaultFlightTime() {
		return defaultFlightTime;
	}

	public void setDefaultFlightTime(int defaultFlightTime) {
		this.defaultFlightTime = defaultFlightTime;
	}

	public float getDefaultFlightAcceleration() {
		return defaultFlightAcceleration;
	}

	public void setDefaultFlightAcceleration(float defaultFlightAcceleration) {
		this.defaultFlightAcceleration = defaultFlightAcceleration;
	}

	public int getDefaultFlightRecovery() {
		return defaultFlightRecovery;
	}

	public void setDefaultFlightRecovery(int defaultFlightRecovery) {
		this.defaultFlightRecovery = defaultFlightRecovery;
	}

	public String getDefaultFlightParticle() {
		return defaultFlightParticle;
	}

	public void setDefaultFlightParticle(String defaultFlightParticle) {
		this.defaultFlightParticle = defaultFlightParticle;
	}

	public String getDefaultExtraValue() {
		return defaultExtraValue;
	}

	public void setDefaultExtraValue(String defaultExtraValue) {
		this.defaultExtraValue = defaultExtraValue;
	}

	public double getDefaultParticleOffset() {
		return defaultParticleOffset;
	}

	public void setDefaultParticleOffset(double defaultParticleOffset) {
		this.defaultParticleOffset = defaultParticleOffset;
	}

	public double getDefaultParticleSpeed() {
		return defaultParticleSpeed;
	}

	public void setDefaultParticleSpeed(double defaultParticleSpeed) {
		this.defaultParticleSpeed = defaultParticleSpeed;
	}

	public int getDefaultParticleCount() {
		return defaultParticleCount;
	}

	public void setDefaultParticleCount(int defaultParticleCount) {
		this.defaultParticleCount = defaultParticleCount;
	}

	public int getDefaultWaterBoost() {
		return defaultWaterBoost;
	}

	public void setDefaultWaterBoost(int defaultWaterBoost) {
		this.defaultWaterBoost = defaultWaterBoost;
	}

	public int getDefaultUnbreakable() {
		return defaultUnbreakable;
	}

	public void setDefaultUnbreakable(int defaultUnbreakable) {
		this.defaultUnbreakable = defaultUnbreakable;
	}

	public String getDefaultElytraName() {
		return defaultElytraName;
	}

	public void setDefaultElytraName(String defaultElytraName) {
		this.defaultElytraName = defaultElytraName;
	}

	public float getTakeoffAngle() {
		return takeoffAngle;
	}

	public void setTakeoffAngle(float takeoffAngle) {
		this.takeoffAngle = takeoffAngle;
	}

	public float getWaterBoostAccelerationMultiplier() {
		return waterBoostAccelerationMultiplier;
	}

	public void setWaterBoostAccelerationMultiplier(float waterBoostAccelerationMultiplier) {
		this.waterBoostAccelerationMultiplier = waterBoostAccelerationMultiplier;
	}

	public float getTakeoffBoostMultiplier() {
		return takeoffBoostMultiplier;
	}

	public void setTakeoffBoostMultiplier(float takeoffBoostMultiplier) {
		this.takeoffBoostMultiplier = takeoffBoostMultiplier;
	}

	public int getTakeoffParticleCount() {
		return takeoffParticleCount;
	}

	public void setTakeoffParticleCount(int takeoffParticleCount) {
		this.takeoffParticleCount = takeoffParticleCount;
	}

	public double getTakeoffParticleSpread() {
		return takeoffParticleSpread;
	}

	public void setTakeoffParticleSpread(double takeoffParticleSpread) {
		this.takeoffParticleSpread = takeoffParticleSpread;
	}

	public float getTakeoffBoostCostPercentage() {
		return takeoffBoostCostPercentage;
	}

	public void setTakeoffBoostCostPercentage(float takeoffBoostCostPercentage) {
		this.takeoffBoostCostPercentage = takeoffBoostCostPercentage;
	}

	
}
