package captureTheFlag.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		loadConfig();
		this.getConfig().set("Config.test.to.String", "HalloWelt!");
		String test = this.getConfig().getString("Config.test");
		Bukkit.broadcastMessage(test);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
        saveConfig();
	}
}
