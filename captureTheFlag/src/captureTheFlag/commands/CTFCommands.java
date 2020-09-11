package captureTheFlag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.CtfPlayer;
import captureTheFlag.utils.TeamColor;

public class CTFCommands implements CommandExecutor {
	private Main plugin;
	
	private SetFlagCommand setFlagCommand;
	private SetSpawnCommand setSpawnCommand;
	private JoinTeamCommand joinTeamCommand;
	
	public CTFCommands(Main plugin) {
		this.plugin = plugin;
		setFlagCommand = new SetFlagCommand(plugin);
		setSpawnCommand = new SetSpawnCommand(plugin);
		joinTeamCommand = new JoinTeamCommand(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length>0) {
			//Player Commands
			if(sender instanceof Player) {
				Player player = (Player) sender;
				//Op Commands
				if(player.isOp()) {
					//setFlag Command
					if(args[0].equalsIgnoreCase("setFlag")) {
						if(args.length==2) {
							try {
								TeamColor color = TeamColor.valueOf(args[1].toUpperCase());
								setFlagCommand.executeSetFlagCommand(sender, command, label, args, color, player);
								player.sendMessage(Main.PREFIX + "Du hast erfolgreich die Flage für Team " + color.getColorCode() + " gesetzt!" ); return(true);
							} catch(Exception e) {
								player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setFlag §9blue§r/§cred§r"); return(false);
							}
						} else
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setFlag [color]"); return(false);
					//setSpawn Command
					} else if(args[0].equalsIgnoreCase("setSpawn")) {
						if(args.length==2) {
							if(args[1].equalsIgnoreCase("blue") || args[1].equalsIgnoreCase("red")) {
								String color = args[1].toLowerCase();
								setSpawnCommand.executeSetSpawnCommand(sender, command, label, args, color, player);
								if(color.equals("blue")) {color=Main.BLUE+color;}else if(color.equals("red")) {color=Main.RED+color;}
								player.sendMessage(Main.PREFIX + "Du hast erfolgreich den Spawn für Team " + color + "§r gesetzt!" ); return(true);
							} else
								player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setSpawn §9blue§r/§cred§r"); return(false);
						} else
							player.sendMessage(Main.PREFIX + "Bitte nutze /CTF setSpawn [color]"); return(false);
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
			//Console Commands
			} else {
				//empty Command
				if(args[0].equalsIgnoreCase("")) {
					if(args.length==2) {
						//TODO
					} else
						sender.sendMessage(Main.PREFIX + "Bitte nutze /CTF empty"); return(false);
				}
			}
		}
		sender.sendMessage(Main.PREFIX + "Benutze /CTF help für mehr Information."); return(false);
	}
	
}
