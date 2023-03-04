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

public class Server extends Command {

	/* Récupère la class "Main" et créer la commande */
	private final ProxyMain main;
	public Server(ProxyMain pluginMain) {

	super("server", "EvhoProxy.server");
	this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande */


	/****************************************************/
	/* PARTIE COMMANDE POUR SE TÉLÉPORTER ENTRE SERVEUR */
	/****************************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /server <server> [player]");

		BaseComponent notfound;
		ServerInfo server;

		if(sender instanceof ProxiedPlayer p) {

			if(!super.hasPermission(p)) {

			  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
			  p.sendMessage(error);
			  return;
		  }


		  if(args.length == 1) {

			notfound = new TextComponent(main.prefix + ChatColor.RED + "Le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " n'éxiste pas !");

			if(ProxyServer.getInstance().getServerInfo(args[0]) == null) {

				p.sendMessage(notfound);
				return;
			}

			server = ProxyServer.getInstance().getServerInfo(args[0]);

			if(main.methodCustom.isServerOnline(server)) {

				if(p.getServer().getInfo().getName().equals(server.getName())) {

					BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous êtes déjà dans le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName());
					p.sendMessage(error);
					return;
				}

				p.connect(server);

				BaseComponent success = new TextComponent(main.prefix + ChatColor.GRAY + "Téléportation dans le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName());
				p.sendMessage(success);

            } else {

				BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName() + ChatColor.RED + " est " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "hors-ligne " + ChatColor.RED + "!");
				p.sendMessage(error);
            }
              return;

          } else if(args.length == 2) {

				notfound = new TextComponent(main.prefix + ChatColor.RED + "Le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " n'éxiste pas ou le joueur est introuvable !");


				if(ProxyServer.getInstance().getServerInfo(args[0]) == null || ProxyServer.getInstance().getPlayer(args[1]) == null) {

					p.sendMessage(notfound);
					return;
				}


				server = ProxyServer.getInstance().getServerInfo(args[0]);

				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);

				if(main.methodCustom.isServerOnline(server)) {

					if(target.getServer().getInfo().getName().equals(server.getName())) {

						BaseComponent error = new TextComponent(main.prefix + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + target.getName() + ChatColor.GRAY + ChatColor.RED + " est déjà dans le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName());
						p.sendMessage(error);
						return;
					}

					target.connect(server);

					BaseComponent successtarget = new TextComponent(main.prefix + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + p.getName() + ChatColor.GRAY + " vous téléporte dans le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName());
					target.sendMessage(successtarget);

					BaseComponent success = new TextComponent(main.prefix + ChatColor.GRAY + "Vous avez téléporté le joueur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + target.getName() + ChatColor.GRAY + " dans le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName());
					p.sendMessage(success);

                } else {

					BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Le serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + server.getName() + ChatColor.RED + " est " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "hors-ligne " + ChatColor.RED + "!");
					p.sendMessage(error);
                }

              return;

          } else {

			  sender.sendMessage(Type);
			  return;
		  }
		}

		if(sender instanceof CommandSender) {

		  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");
		  sender.sendMessage(error);
		}
	}

	/****************************************************/
	/* PARTIE COMMANDE POUR SE TÉLÉPORTER ENTRE SERVEUR */
	/****************************************************/
}
