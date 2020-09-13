package captureTheFlag.commands;

import captureTheFlag.main.Main;
import captureTheFlag.utils.TeamColor;

public class StartGameCommand {
	private Main plugin;
	
	public StartGameCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeStartGameCommand() {
		//Spawnpoints check
		if(plugin.game.getSpawnPoint(TeamColor.BLUE)==null || plugin.game.getSpawnPoint(TeamColor.RED)==null) {
			System.out.println(Main.DEBUGPREFIX+"ERROR: Das Game konnte nicht gestertet werden, da die Spawnpointe nicht gesetzt sind.");
			return;
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Spawnpoints wurden erfolgreich gechecked.");
		
		//Flagpoints check
		if(plugin.game.getFlagPoints().size()<4) {
			System.out.println(Main.DEBUGPREFIX+"ERROR: Das Game konnte nicht gestartet werden, da zu wenig FlagPoints gesetzt wurden.");
			return;
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Flagpoints wurden erfolgreich gechecked.");
		
		//Flagpoints zählen
		int flagsBlue = 0;
		int flagsRed = 0;
		for(int i=0; i<plugin.game.getFlagPoints().size(); i++) {
			if(plugin.game.getFlagPoints().get(i).getTeamColor().equals(TeamColor.BLUE)) {
				flagsBlue++;
			} else {
				flagsRed++;
			}
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Flagpoints wurden erfolgreich gezählt");
		
		//Flagpoint Amount korrigieren
		if(plugin.game.getFlagPoints().size() %2 !=0) {
			System.out.println(Main.DEBUGPREFIX+"WARINING: there isn't an equalent number of Flagpoints set, one will be removed!");
			
			int size;
			TeamColor mode;
			if(flagsBlue>flagsRed) {
				size = flagsBlue;
				mode = TeamColor.BLUE;
			} else {
				size = flagsRed;
				mode = TeamColor.RED;
			}
			int removedFlagPoint = (int) (Math.random()*size);
			for(int i=0; i<plugin.game.getFlagPoints().size(); i++) {
				if(plugin.game.getFlagPoints().get(i).getTeamColor().equals(mode)) {
					removedFlagPoint--;
					if(removedFlagPoint == 1) {
						plugin.game.removeFlagPoint(plugin.game.getFlagPoints().get(i));
						System.out.println("flagpoint "+i+" wurde removed");
						break;
					}
				}
			}
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Anzahl der Flagpoints wurde erfolgreich korrigiert");
		
		//Flags spawnen
		int blueFlags = flagsBlue/2;
		int redFlags = flagsRed/2;
		System.out.println(redFlags+Main.DEBUGPREFIX+flagsRed+Main.DEBUGPREFIX+plugin.game.getFlagPoints().size());
		for(int i=0; i<plugin.game.getFlagPoints().size(); i++) {
			if(plugin.game.getFlagPoints().get(i).getTeamColor().equals(TeamColor.BLUE)) {
				int getFlag = 0; //(int) (Math.random()*flagsBlue/blueFlags);
				if(getFlag == 0 && blueFlags>0) {
					SpawnFlagCommand.executeSpawnFlagCommand(plugin.game.getFlagPoints().get(i));
					blueFlags--;
				}
				flagsBlue--;
			}
			if(plugin.game.getFlagPoints().get(i).getTeamColor().equals(TeamColor.RED)) {
				int getFlag = 0;//(int) (Math.random()*flagsRed/redFlags);
				if(getFlag == 0 && redFlags>0) {
					SpawnFlagCommand.executeSpawnFlagCommand(plugin.game.getFlagPoints().get(i));
					redFlags--;
				}
				flagsRed--;
			}
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Flaggen wurden erfolgreich gespawned.");
		
		//Player tpen und kit
		for(int i=0; i<plugin.game.getCtfPlayers().size(); i++) {
			plugin.game.getCtfPlayers().get(i).getPlayer().teleport(plugin.game.getSpawnPoint(plugin.game.getCtfPlayers().get(i).getTeamColor()));
			plugin.game.getCtfPlayers().get(i).getKit();
		}
		System.out.println(Main.DEBUGPREFIX+"DEBUG: Die Spieler wurden erfolgreich teleportiert und mit ihren Kits versehen.");
	}
}
