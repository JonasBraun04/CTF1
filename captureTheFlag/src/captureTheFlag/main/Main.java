package captureTheFlag.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import captureTheFlag.commands.CTFCommands;
import captureTheFlag.events.EntityDamageByEntityListener;
import captureTheFlag.events.EntityDamageListener;
import captureTheFlag.events.EntityExplodeListener;
import captureTheFlag.events.PlayerJoinListener;
import captureTheFlag.events.PlayerMoveListener;
import captureTheFlag.utils.CtfGame;
import captureTheFlag.utils.FlagPoint;
import captureTheFlag.utils.TeamColor;

public class Main extends JavaPlugin {
	
	public static final String PREFIX = "§7[§cCTF§7] §r";
	public static final String BLUE = "§9";
	public static final String RED = "§c";
	public CtfGame game = new CtfGame();
	public boolean blueFlag = true;
	public boolean redFlag = true;
	
	//TODO: (resetFlag new), getFlag renew, (spawnFlag new),
	
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
		System.out.println(PREFIX + "Config wird geladen.");
		FileConfiguration config = this.getConfig();
		loadFlagPoints(config);
		loadSpawnPoint(config, TeamColor.BLUE);
		loadSpawnPoint(config, TeamColor.RED);
		System.out.println(PREFIX + "Config wurde geladen.");
	}
	
	public void initListeners(PluginManager pluginManager) {
		pluginManager.registerEvents(new PlayerJoinListener(), this);
		pluginManager.registerEvents(new EntityDamageListener(), this);
		pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
		pluginManager.registerEvents(new PlayerMoveListener(this), this);
	}
	
	public void loadFlagPoints(FileConfiguration config) {
		try {
			for(int i=1; i<=config.getInt("CTF.flag.amount"); i++) {
				TeamColor color = TeamColor.valueOf(config.getString("CTF.flag."+i+".Team"));
				World world = Bukkit.getWorld(config.getString("CTF.flag."+i+".World"));
				double x = config.getDouble("CTF.flag."+i+".X");
				double y = config.getDouble("CTF.flag."+i+".Y");
				double z = config.getDouble("CTF.flag."+i+".Z");
				Location location = new Location(world, x, y, z);
				this.game.addFlagPoint(new FlagPoint(color, location, game.getFlagPoints().size()));	
			}
			System.out.println(PREFIX + config.getString("CTF.flag.amount")+" FlagPoints wurden geladen.");
		} catch(Exception e) {
			System.out.println(PREFIX + "Achtung! Es sind keine FlagPoints gesetzt!");
		}
	}
	
	public void loadSpawnPoint(FileConfiguration config, TeamColor color) {
		try {
			World world = Bukkit.getWorld(config.getString("CTF.spawn."+color.toString()+".World"));
			double x = config.getDouble("CTF.spawn."+color.toString()+".X");
			double y = config.getDouble("CTF.spawn."+color.toString()+".Y");
			double z = config.getDouble("CTF.spawn."+color.toString()+".Z");
			float yaw = Float.parseFloat(config.getString("CTF.spawn."+color.toString()+".Yaw"));
			float pitch = Float.parseFloat(config.getString("CTF.spawn."+color.toString()+".Pitch"));
			Location location = new Location(world, x, y, z, yaw, pitch);
			System.out.println(PREFIX + "SpawnPoint für Team "+color.getColorCode()+" wurden geladen.");
		} catch (Exception e) {
			System.out.println(PREFIX + "Achtung! Es ist kein SpawnPoint für Team "+color.getColorCode()+" gesetzt!");
		}
	}
}
