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

public class SubTitle extends Command {

    /* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private ProxyMain main;
	public SubTitle(ProxyMain pluginMain) {
		super("subtitle", "EvhoProxy.subtitle", "sbt");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN SOUS TITRE */
	/**********************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound = null;
		ServerInfo server = null;

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /subtitle ou /sbt <all/'servername'/'playername'> <message> !");
		BaseComponent Success = new TextComponent(main.prefix + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Sous-Titre envoyé !");

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
			Title subTitleMessage = ProxyServer.getInstance().createTitle().title(new TextComponent(" ")).subTitle(message).fadeOut(100 / 20);

			notfound = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur ou le Joueur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " est introuvable !");

			if(ProxyServer.getInstance().getServerInfo(args[0]) == null) {

				if(args[0].equalsIgnoreCase("all")) {

					for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) { subTitleMessage.send(players); }
					sender.sendMessage(Success);
					return;

				} else if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					sender.sendMessage(Success);
					subTitleMessage.send(target);
					return;
				}

				sender.sendMessage(notfound);
				return;
			}

			server = ProxyServer.getInstance().getServerInfo(args[0]);

			if(main.methodcustom.isServerOnline(server)) {

				for(ProxiedPlayer players : server.getPlayers()) { subTitleMessage.send(players); }
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

	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN SOUS TITRE */
	/**********************************************/
}
