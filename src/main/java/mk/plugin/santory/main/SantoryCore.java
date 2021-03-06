package mk.plugin.santory.main;

import mk.plugin.santory.command.AdminCommand;
import mk.plugin.santory.command.PlayerCommand;
import mk.plugin.santory.config.Configs;
import mk.plugin.santory.listener.*;
import mk.plugin.santory.placeholder.SantoryPlaceholder;
import mk.plugin.santory.slave.Slaves;
import mk.plugin.santory.slave.master.Masters;
import mk.plugin.santory.slave.task.SlaveTask;
import mk.plugin.santory.task.HealTask;
import mk.plugin.santory.task.TargetTask;
import mk.plugin.santory.traveler.Travelers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SantoryCore extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.reloadConfig();
		this.registerListeners();
		this.registerCommands();
		this.registerTasks();
		this.registerPlaceholders();
	}
	
	@Override
	public void onDisable() {
		this.saveOninePlayers();
		for (Player player : Bukkit.getOnlinePlayers()) {
			Slaves.despawnCurrentSlave(player);
		}
	}
	
	@Override
	public void reloadConfig() {
		this.saveDefaultConfig();
		Configs.reload(this);
	}
	
	public void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ItemListener(), this);
		pm.registerEvents(new LevelListener(), this);
		pm.registerEvents(new StateListener(), this);
		pm.registerEvents(new StatListener(), this);
		pm.registerEvents(new WeaponListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new ArmorListener(), this);
		pm.registerEvents(new GUIListener(), this);
		
		pm.registerEvents(new SlaveListener(), this);
		
		if (pm.isPluginEnabled("MythicMobs")) {
			pm.registerEvents(new MobListener(), this);
		}

		if (pm.isPluginEnabled("XacMinh")) {
			pm.registerEvents(new XacMinhListener(), this);
		}
	}
	
	public void registerTasks() {
		new TargetTask().runTaskTimer(this, 0, 2);
		new HealTask().runTaskTimer(this, 0, 20);
		new SlaveTask().runTaskTimer(this, 0, 10);
	}
	
	public void registerPlaceholders() {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) new SantoryPlaceholder().register();
	}
	
	public void registerCommands() {
		var adminCmd = new AdminCommand();
		var playerCmd = new PlayerCommand();

		this.getCommand("santory").setExecutor(adminCmd);
		this.getCommand("player").setExecutor(playerCmd);
		this.getCommand("forge").setExecutor(playerCmd);
		this.getCommand("see").setExecutor(playerCmd);
		this.getCommand("artifact").setExecutor(playerCmd);
	}
	
	public void saveOninePlayers() {
		Bukkit.getOnlinePlayers().forEach(player -> {
			Travelers.saveAndClearCache(player.getName());
			Masters.saveAndClearCache(player);
		});
	}
	
	public static SantoryCore get() { 
		return JavaPlugin.getPlugin(SantoryCore.class); 
	}
	
	
}
