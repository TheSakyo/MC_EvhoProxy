/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class Lobby extends Command {

	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private final ProxyMain main;
	public Lobby(ProxyMain pluginMain) {

		super("lobby", null, "hub");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/*****************************************************/
	/* PARTIE COMMANDE POUR SE TÉLÉPORTER AU SERVEUR HUB */
	/****************************************************/

	public void execute(CommandSender sender, String[] args) {

		if((sender instanceof ProxiedPlayer p)) {

			if(args.length != 0) {

				BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /hub ou /lobby sans arguments !");
				sender.sendMessage(Type);
				return;
			}

			if(p.getServer().getInfo().getName().equals("hub")) {

				BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous êtes déjà dans un serveur " + main.commandLabel.substring(0, 1).toUpperCase() + main.commandLabel.substring(1));
				p.sendMessage(error);

				main.commandLabel = null;
				return;
			}

			 p.connect(ProxyServer.getInstance().getServerInfo("hub"));

			 BaseComponent success = new TextComponent(main.prefix + ChatColor.GRAY + "Téléportation vers un serveur " + main.commandLabel.substring(0, 1).toUpperCase() + main.commandLabel.substring(1) + "...");
			 p.sendMessage(success);

			 main.commandLabel = null;
			 return;

		  }

		if((sender instanceof CommandSender)) {

			BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");
			sender.sendMessage(error);
		}
	}

	/*****************************************************/
	/* PARTIE COMMANDE POUR SE TÉLÉPORTER AU SERVEUR HUB */
	/****************************************************/
}
