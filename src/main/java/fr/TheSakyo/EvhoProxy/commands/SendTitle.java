/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class SendTitle extends Command {

    /* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private ProxyMain main;
	public SendTitle(ProxyMain pluginMain) {
		super("sendtitle", "EvhoProxy.sendtitle", "st", "title");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/******************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN TITRE */
	/*****************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound = null;
		ServerInfo server = null;

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /sendtitle ou /st <all/'servername'/'playername'> <message> !");
		BaseComponent Success = new TextComponent(main.prefix + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Titre envoyé !");

		if(args.length == 0 || args.length == 1) {

			sender.sendMessage(Type);
			return;

		} else if(args.length >= 2) {

			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < args.length; i++) {
				sb.append(args[i]);
				sb.append(" ");
			}
			String combinedArgs = sb.toString();

			BaseComponent message = new TextComponent(main.methodcustom.format(combinedArgs));
			Title titleMessage = ProxyServer.getInstance().createTitle().title(message).fadeOut(100 / 20);

			notfound = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur ou le Joueur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " est introuvable !");

			if(ProxyServer.getInstance().getServerInfo(args[0]) == null) {

				if(args[0].equalsIgnoreCase("all")) {

					for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) { titleMessage.send(players); }
					sender.sendMessage(Success);
					return;

				} else if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					sender.sendMessage(Success);
					titleMessage.send(target);
					return;
				}

				sender.sendMessage(notfound);
				return;
			}

			server = ProxyServer.getInstance().getServerInfo(args[0]);

			if(main.methodcustom.isServerOnline(server)) {

				for(ProxiedPlayer players : server.getPlayers()) { titleMessage.send(players); }
				sender.sendMessage(Success);
				return;

			} else {

				BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName() + ChatColor.RED + " est " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "hors-ligne " + ChatColor.RED + "!");
				sender.sendMessage(error);
				return;
			}

		} else {

			 sender.sendMessage(Type);
			 return;
		}
	}

	/******************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN TITRE */
	/*****************************************/
}
