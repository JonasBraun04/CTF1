package captureTheFlag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.TeamColor;

public class CTFCommands implements CommandExecutor {
	private Main plugin;
	
	private SetFlagPointCommand setFlagCommand;
	private SetSpawnPointCommand setSpawnCommand;
	private SpawnFlagCommand spawnFlagCommand;
	private JoinTeamCommand joinTeamCommand;
	private RemoveFlagPointCommand removeFlagPointCommand;
	
	
	public CTFCommands(Main plugin) {
		this.plugin = plugin;
		setFlagCommand = new SetFlagPointCommand(plugin);
		setSpawnCommand = new SetSpawnPointCommand(plugin);
		spawnFlagCommand = new SpawnFlagCommand(plugin);
		joinTeamCommand = new JoinTeamCommand(plugin);
		removeFlagPointCommand = new RemoveFlagPointCommand(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length>0) {
			//Player Commands
			if(sender instanceof Player) {
				Player player = (Player) sender;
				//Op Commands
				if(player.isOp()) {
					//setFlagPoint Command
					if(args[0].equalsIgnoreCase("setFlagPoint")) {
						if(args.length==2) {
							try {
								TeamColor color = TeamColor.valueOf(args[1].toUpperCase());
								setFlagCommand.executeSetFlagCommand(sender, command, label, args, color, player);
								player.sendMessage(Main.PREFIX + "Du hast erfolgreich den FlagPoint für Team " + color.getColorCode() + " gesetzt!" ); return(true);
							} catch(Exception e) {
								player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setFlagPoint §9blue§r/§cred§r"); return(false);
							}
						} else
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setFlagPoint [color]"); return(false);
					//setSpawnPoint Command
					} else if(args[0].equalsIgnoreCase("setSpawnPoint")) {
						if(args.length==2) {
							try {
								TeamColor color = TeamColor.valueOf(args[1].toUpperCase());
								setSpawnCommand.executeSetSpawnCommand(sender, command, label, args, color, player);
								player.sendMessage(Main.PREFIX + "Du hast erfolgreich den SpawnPoint für Team " + color.getColorCode() + "§r gesetzt!" ); return(true);
							} catch(Exception e) {
								player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setSpawnPoint §9blue§r/§cred§r"); return(false);
							}
						} else
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setSpawnPoint [color]"); return(false);
					//empty Command
					} else if(args[0].equalsIgnoreCase("")) {
						if(args.length==2) {
							//TODO
						} else
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
					}
				//Normal Player Commands
				}
				//empty Command
				if(args[0].equalsIgnoreCase("joinTeam")) {
					if(args.length==2) {
						try {
							TeamColor color = TeamColor.valueOf(args[1].toUpperCase());
							joinTeamCommand.executeJoinTeamCommand(player, color);
							return(true);
						} catch(Exception e) {
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF joinTeam [color]"); return(false);
						}
					} else
						player.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
				//empty Command
				} else if(args[0].equalsIgnoreCase("")) {
					if(args.length==2) {
						//TODO
					} else
						player.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
				}
			//Console only Commands
			} else {
				//empty Command
				if(args[0].equalsIgnoreCase("")) {
					if(args.length==2) {
						//TODO
					} else
						sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
				}
			}
			//removeFlagPoint Command
			if(args[0].equalsIgnoreCase("removeFlagPoint")) {
				if(args.length==2) {
					try {
						int index = Integer.parseInt(args[1]);
						if(plugin.game.getFlagPoints().size()>=index && index>0) {
							removeFlagPointCommand.executeRemoveFlagPointCommand(sender, index);
							sender.sendMessage(Main.PREFIX + "Du hast erfolgreich den "+index+"ten FlagPoint removed."); return(true);
						} else {
							sender.sendMessage(Main.PREFIX + "Bitte wähle einen der "+plugin.game.getFlagPoints().size()+" vorhanden FlagPoints aus."); return(false);
						}
					} catch(Exception e) {
						sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF removeFlagPoint [index]"); return(false);
					}
				} else
					sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF removeFlagPoint [index]"); return(false);
			//spawnFlag Command
			} else if(args[0].equalsIgnoreCase("spawnFlag")) {
				if(args.length==2) {
					try {
						int index = Integer.parseInt(args[1]);
						if(plugin.game.getFlagPoints().size()>=index && index>0) {
							spawnFlagCommand.executeSpawnFlagCommand(index);
							sender.sendMessage(Main.PREFIX + "Du hast erfolgreich eine Flag auf dem "+index+"ten FlagPoint erzeugt."); return(true);
						} else {
							sender.sendMessage(Main.PREFIX + "Bitte wähle einen der "+plugin.game.getFlagPoints().size()+" vorhanden FlagPoints aus."); return(false);
						}
					} catch(Exception e) {
						sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF spawnFlag [index]"); return(false);
					}
				} else
				sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF spawnFlag [index]"); return(false);
			//empty Command
			} else if(args[0].equalsIgnoreCase("")) {
				if(args.length==2) {
					//TODO
				} else
				sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
			}
		}
		sender.sendMessage(Main.PREFIX + "Benutze /CTF help für mehr Information."); return(false);
	}
	
}
