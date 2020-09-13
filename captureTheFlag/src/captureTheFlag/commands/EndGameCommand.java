package captureTheFlag.commands;

import captureTheFlag.main.Main;

public class EndGameCommand {
	private Main plugin;
	private SpawnCommand spawnCommand;
	
	public EndGameCommand(Main plugin) {
		this.plugin = plugin;
		spawnCommand = new SpawnCommand(plugin);
	}
	
	public void executeEndGameCommand() {
		for(int i=0; i<plugin.game.getCtfPlayers().size(); i++) {
			spawnCommand.executeSpawnCommand(plugin.game.getCtfPlayers().get(i).getPlayer());
			plugin.game.getCtfPlayers().get(i).getPlayer().teleport(plugin.game.getSpawn());
			plugin.game.clearPlayer();
		}
		plugin.clearArena();
	}
}
