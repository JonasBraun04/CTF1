package captureTheFlag.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import captureTheFlag.commands.CTFCommands;
import captureTheFlag.commands.RemoveFlagCommand;
import captureTheFlag.events.BlockBreakListener;
import captureTheFlag.events.BlockPlaceListener;
import captureTheFlag.events.EntityDamageByEntityListener;
import captureTheFlag.events.EntityDamageListener;
import captureTheFlag.events.EntityExplodeListener;
import captureTheFlag.events.EntityShootBowListener;
import captureTheFlag.events.InventoryClickListener;
import captureTheFlag.events.PlayerDeathListener;
import captureTheFlag.events.PlayerDropItemListener;
import captureTheFlag.events.PlayerInteractListener;
import captureTheFlag.events.PlayerItemBreakListener;
import captureTheFlag.events.PlayerItemConsumeListener;
import captureTheFlag.events.PlayerJoinListener;
import captureTheFlag.events.PlayerMoveListener;
import captureTheFlag.events.EntityPickupItemListener;
import captureTheFlag.utils.CtfGame;
import captureTheFlag.utils.FlagPoint;
import captureTheFlag.utils.TeamColor;

public class Main extends JavaPlugin {
	
	public static final String PREFIX = "§7[§6CTF§7] §r";
	public static final String DEBUGPREFIX = "[CTF]";
	public CtfGame game = new CtfGame();
	
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
		clearArena();
		saveConfig();
		
		Bukkit.broadcastMessage(PREFIX + "Disabled");
	}
	
	
	public void loadConfig() {
		System.out.println(DEBUGPREFIX + " Config wird geladen.");
		FileConfiguration config = this.getConfig();
		loadFlagPoints(config);
		loadSpawnPoint(config, TeamColor.BLUE);
		loadSpawnPoint(config, TeamColor.RED);
		loadSpawn(config);
		System.out.println(DEBUGPREFIX + " Config wurde geladen.");
	}
	
	public void initListeners(PluginManager pluginManager) {
		pluginManager.registerEvents(new BlockBreakListener(this), this);
		pluginManager.registerEvents(new BlockPlaceListener(this), this);
		pluginManager.registerEvents(new EntityDamageByEntityListener(this), this);
		pluginManager.registerEvents(new EntityDamageListener(this), this);
		pluginManager.registerEvents(new EntityExplodeListener(), this);
		pluginManager.registerEvents(new EntityShootBowListener(this), this);
		pluginManager.registerEvents(new InventoryClickListener(this), this);
		pluginManager.registerEvents(new PlayerDeathListener(this), this);
		pluginManager.registerEvents(new PlayerDropItemListener(), this);
		pluginManager.registerEvents(new PlayerInteractListener(), this);
		pluginManager.registerEvents(new PlayerItemBreakListener(this), this);
		pluginManager.registerEvents(new PlayerItemConsumeListener(this), this);
		pluginManager.registerEvents(new PlayerJoinListener(this), this);
		pluginManager.registerEvents(new PlayerMoveListener(this), this);
		pluginManager.registerEvents(new EntityPickupItemListener(), this);
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
			System.out.println(DEBUGPREFIX + " " + config.getString("CTF.flag.amount")+" FlagPoints wurden geladen.");
		} catch(Exception e) {
			System.out.println(DEBUGPREFIX + " Achtung! Es sind keine FlagPoints gesetzt!");
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
			game.setSpawnPoint(color, location);
			System.out.println(DEBUGPREFIX + " SpawnPoint für Team "+color.getColorCode()+" wurden geladen.");
		} catch(Exception e) {
			System.out.println(DEBUGPREFIX + " Achtung! Es ist kein SpawnPoint für Team "+color.getColorCode()+" gesetzt!");
		}
	}
	
	public void loadSpawn(FileConfiguration config) {
		try {
			World world = Bukkit.getWorld(config.getString("CTF.spawn.lobby.World"));
			double x = config.getDouble("CTF.spawn.lobby.X");
			double y = config.getDouble("CTF.spawn.lobby.Y");
			double z = config.getDouble("CTF.spawn.lobby.Z");
			float yaw = Float.parseFloat(config.getString("CTF.spawn.lobby.Yaw"));
			float pitch = Float.parseFloat(config.getString("CTF.spawn.lobby.Pitch"));
			Location location = new Location(world, x, y, z, yaw, pitch);
			game.setSpawn(location);
			System.out.println(DEBUGPREFIX + " Spawn wurde geladen.");
		} catch(Exception e) {
			System.out.println(DEBUGPREFIX + " Achtug! Es ist kein Spawn gesetzt!");
		}
	}
	
	public void clearArena() {
		for(int i=0; i<game.getFlagPoints().size(); i++) {
			if(game.getFlagPoints().get(i).hasFlag()) {
				RemoveFlagCommand.executeRemoveFlagCommand(game.getFlagPoints().get(i));
			}
		}
	}
}
