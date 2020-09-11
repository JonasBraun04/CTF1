package captureTheFlag.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import captureTheFlag.commands.CTFCommands;
import captureTheFlag.events.EntityDamageByEntityListener;
import captureTheFlag.events.EntityDamageListener;
import captureTheFlag.events.EntityExplodeListener;
import captureTheFlag.events.PlayerJoinListener;
import captureTheFlag.events.PlayerMoveListener;
import captureTheFlag.utils.CtfGame;

public class Main extends JavaPlugin {
	
	public static final String PREFIX = "§7[§cCTF§7] §r";
	public static final String BLUE = "§9";
	public static final String RED = "§c";
	public CtfGame game = new CtfGame();
	public boolean blueFlag = true;
	public boolean redFlag = true;
	
	//TODO: SetFlag renew, load config new, resetFlag new, getFlag renew, spawnFlag new,
	
	@Override
	public void onEnable() {
		loadConfig();
		initListeners(Bukkit.getPluginManager());
		getCommand("CTF").setExecutor(new CTFCommands(this));
		
		Bukkit.broadcastMessage(PREFIX + "Enabled");
	}
	
	@Override
	public void onDisable() {	
		saveConfig();
		
		Bukkit.broadcastMessage(PREFIX + "Disabled");
	}
	
	
	public void loadConfig() {
		
	}
	
	public void initListeners(PluginManager pluginManager) {
		pluginManager.registerEvents(new PlayerJoinListener(), this);
		pluginManager.registerEvents(new EntityDamageListener(), this);
		pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
		pluginManager.registerEvents(new PlayerMoveListener(this), this);
	}
}
