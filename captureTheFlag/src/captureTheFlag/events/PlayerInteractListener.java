package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import captureTheFlag.commands.SelectTeamCommand;

public class PlayerInteractListener implements Listener {
	private SelectTeamCommand selectTeamCommand;
	
	public PlayerInteractListener() {
		selectTeamCommand = new SelectTeamCommand();
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getItem().getType()==Material.BEACON) {
			selectTeamCommand.executeSelectTeamCommand(player);
		}
	}
}
