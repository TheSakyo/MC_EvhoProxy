/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class Broadcast extends Command {

    	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private final ProxyMain main;
	public Broadcast(ProxyMain pluginMain) {

		super("broadcast", "EvhoProxy.broadcast", "bc");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN BROADCAST  */
	/**********************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound;
		ServerInfo server;

		String GB = ChatColor.GOLD.toString() + ChatColor.BOLD.toString();
		String D_PBU = ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString();
		String RBU = ChatColor.RED.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString();

		String BroadcastPrefix = GB + "[" + D_PBU + "Evho" + RBU + "Message" + GB + "]" + ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + " :" + ChatColor.RESET + " ";

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /broadcast ou /bc <all/'servername'/'playername'> <message> !");
		BaseComponent Success = new TextComponent(main.prefix + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Broadcast envoyé !");

		if(args.length == 0 || args.length == 1) sender.sendMessage(Type);
		else {

			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < args.length; i++) {

				sb.append(args[i]);
				sb.append(" ");
			}

			String combinedArgs = sb.toString();

			BaseComponent message = new TextComponent(main.methodCustom.format(BroadcastPrefix + combinedArgs));

			notfound = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur ou le Joueur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " est introuvable !");

			if(ProxyServer.getInstance().getServerInfo(args[0]) == null) {

				if(args[0].equalsIgnoreCase("all")) {

					ProxyServer.getInstance().broadcast(message);
					sender.sendMessage(Success);
					return;

				} else if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					target.sendMessage(message);
					sender.sendMessage(Success);
					return;
				}

				sender.sendMessage(notfound);
				return;
			}

			server = ProxyServer.getInstance().getServerInfo(args[0]);

			if(main.methodCustom.isServerOnline(server)) {

				for(ProxiedPlayer players : server.getPlayers()) { players.sendMessage(message); }
				sender.sendMessage(Success);

            } else {

				BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName() + ChatColor.RED + " est " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "hors-ligne " + ChatColor.RED + "!");
				sender.sendMessage(error);
            }
        }
	}

	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN BROADCAST  */
	/**********************************************/
}
