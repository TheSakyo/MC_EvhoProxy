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
	private final ProxyMain main;
	public SubTitle(ProxyMain pluginMain) {

		super("subtitle", "EvhoProxy.subtitle", "sbt");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN SOUS TITRE */
	/**********************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound;
		ServerInfo server;

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /subtitle ou /sbt <all/'servername'/'playername'> <message> !");
		BaseComponent Success = new TextComponent(main.prefix + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Sous-Titre envoyé !");

		if(args.length == 0 || args.length == 1) sender.sendMessage(Type);
		else {

			StringBuilder sb = new StringBuilder();

			for(int i = 1; i < args.length; i++) {

				sb.append(args[i]);
				sb.append(" ");
			}

			String combinedArgs = sb.toString();

			BaseComponent message = new TextComponent(main.methodCustom.format(combinedArgs));
			Title subTitleMessage = ProxyServer.getInstance().createTitle().title(new TextComponent(" ")).subTitle(message).fadeOut(100 / 20);

			executeTitle(sender, args, Success, subTitleMessage, main);
		}
	}

	/**************************************************************************************************/
	/**************************************************************************************************/

	/**
	 * Envoie un message de titre par expéditeur spécifié, avec la possibilité de cibler un joueur spécifique ou tous les joueurs sur un serveur.
	 *
	 * @param sender L'Expéditeur qui doit envoyer le message de titre
	 * @param args les arguments de la commande, contenant le nom du serveur ou du joueur cible
	 * @param success le composant de base à afficher en cas de succès
	 * @param subTitleMessage le message de titre à envoyer
	 * @param main l'instance principale du plugin
	 */
	static void executeTitle(CommandSender sender, String[] args, BaseComponent success, Title subTitleMessage, ProxyMain main) {

		BaseComponent notfound;
		ServerInfo server;

		notfound = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur ou le Joueur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " est introuvable !");

		if(ProxyServer.getInstance().getServerInfo(args[0]) == null) {

			if(args[0].equalsIgnoreCase("all")) {

				for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) { subTitleMessage.send(players); }
				sender.sendMessage(success);
				return;

			} else if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				sender.sendMessage(success);
				subTitleMessage.send(target);
				return;
			}

			sender.sendMessage(notfound);
			return;
		}

		server = ProxyServer.getInstance().getServerInfo(args[0]);

		if(main.methodCustom.isServerOnline(server)) {

			for(ProxiedPlayer players : server.getPlayers()) { subTitleMessage.send(players); }
			sender.sendMessage(success);

		} else {

			BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Le Serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName() + ChatColor.RED + " est " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "hors-ligne " + ChatColor.RED + "!");
			sender.sendMessage(error);
		}
	}

	/***********************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN SOUS TITRE */
	/**********************************************/
}
