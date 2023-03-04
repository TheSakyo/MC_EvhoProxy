/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;


/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class Maintenance extends Command {

	/* Récupère la class "Main" et créer la commande */
	private ProxyMain main;
	public Maintenance(ProxyMain pluginMain) {
	super("maintenance", "EvhoProxy.maintenance", "mt");
	this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande */


	// Mise En Page En-Tête et Pied De Page de la partie "help" //
    String evhomaintenance = ChatColor.GRAY + "========= " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "Evho" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Maintenance" + ChatColor.GRAY + " =========";
    String footer = ChatColor.GRAY + "===========================";
    //Mise En Page En-Tête et Pied De Page de la partie "help" //*

	// Préfix "EvhoMaintenance" //
    String prefixmaintenance = ChatColor.WHITE + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "Evho" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Maintenance" + ChatColor.WHITE + "]" + ChatColor.RESET + " ";
    // Préfix "EvhoMaintenance" //

	// Format du Chat (couleur grise en italic)
    String GI = ChatColor.GRAY.toString() + ChatColor.ITALIC.toString();


	/***********************************************************/
	/* PARTIE COMMANDE POUR ACTIVER/DÉSACTIVER LA MAINTENANCER */
	/***********************************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Commande Introuvable ou Argument Incorrect, Essayez /maintenance help");

		BaseComponent notfound = null;
		ServerInfo server = null;


		if(sender instanceof ProxiedPlayer p) {

				if(!super.hasPermission(p)) {

				  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
				  p.sendMessage(error);
				  return;
			  }
		}


		if(args.length == 0) {

			      String prefix_cmd = ChatColor.YELLOW + "/maintenance " + ChatColor.GOLD;

                  sender.sendMessage(new TextComponent(evhomaintenance));
                  sender.sendMessage(new TextComponent(""));
				  sender.sendMessage(new TextComponent(ChatColor.AQUA + "alias: " + ChatColor.WHITE + "/mt"));
				  sender.sendMessage(new TextComponent(""));
                  sender.sendMessage(new TextComponent(prefix_cmd + "help" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Affiche la Liste des commandes d'EvhoMaintenance."));
				  sender.sendMessage(new TextComponent(prefix_cmd + "status" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Affiche la Liste des Serveurs en Maintenance."));
				  sender.sendMessage(new TextComponent(prefix_cmd + "whitelist" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Affiche la Liste des Joueurs ayant accés à la Maintenance."));
				  sender.sendMessage(new TextComponent(prefix_cmd + "motd <message>" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Change le MOTD du Serveur quand il est en Maintenance."));
				  sender.sendMessage(new TextComponent(prefix_cmd + "<add/remove> <player>" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Ajoute/Supprime un Joueur dans la WhiteList."));
				  sender.sendMessage(new TextComponent(prefix_cmd + "<on/off> <server>" + ChatColor.WHITE + ": " + ChatColor.GRAY + "Active/Désactive la Maintenance sur un Serveur"));
                  sender.sendMessage(new TextComponent(""));
                  sender.sendMessage(new TextComponent(footer));

		} else if(args.length == 1) {

			if(args[0].equalsIgnoreCase("help")) { ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "maintenance"); }
			else if(args[0].equalsIgnoreCase("status")) {

				List<String> listServers = new ArrayList<String>();

				if(main.maintenanceconfig.contains("maintenance_mode")) {

				    if(main.maintenanceconfig.contains("maintenance_mode.proxy") && main.maintenanceconfig.getBoolean("maintenance_mode.proxy") == true) { listServers.add("proxy"); }

					for(ServerInfo serverInfo : ProxyServer.getInstance().getServersCopy().values()) {

						if(main.maintenanceconfig.contains("maintenance_mode." + serverInfo.getName())) { listServers.add(serverInfo.getName()); }
					}

					if(!listServers.isEmpty()) {

						sender.sendMessage(new TextComponent(ChatColor.GRAY + "========= " + prefixmaintenance + ChatColor.GRAY + "========="));
						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(new TextComponent(" "));

						sender.sendMessage(new TextComponent(ChatColor.AQUA.toString() + ChatColor.UNDERLINE.toString() + "Liste des Serveur(s) en Maintenance :"));

						for(String serverName : listServers){

							BaseComponent actualServer = new TextComponent(ChatColor.WHITE + "- " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "Serveur_" + serverName.toUpperCase());
							actualServer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/maintenance off " + serverName));
							actualServer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.ITALIC + "Cliquez pour désactiver la Maintenance de ce Serveur !")));

							sender.sendMessage(new TextComponent(" "));
							sender.sendMessage(actualServer);
						}

						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(new TextComponent(ChatColor.GRAY + "==========================="));

					} else { sender.sendMessage(new TextComponent(prefixmaintenance + GI + "Aucun Serveur(s) est en Maintenance !")); }

            	} else { sender.sendMessage(new TextComponent(prefixmaintenance + GI + "Aucun Serveur(s) est en Maintenance !")); }

			} else if(args[0].equalsIgnoreCase("whitelist")) {

				List<String> listPlayers = (List<String>)main.maintenanceconfig.getList("whitelist");

				if(listPlayers != null && !listPlayers.isEmpty()) {

					sender.sendMessage(new TextComponent(ChatColor.GRAY + "========= " + prefixmaintenance + ChatColor.GRAY + "========="));
					sender.sendMessage(new TextComponent(" "));
					sender.sendMessage(new TextComponent(" "));

					sender.sendMessage(new TextComponent(ChatColor.AQUA.toString() + ChatColor.UNDERLINE.toString() + "Liste des Joueur(s) ayant accés à la Maintenance :"));


					for(String name : listPlayers){

						BaseComponent actualPlayerName = new TextComponent(ChatColor.WHITE + "- " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + name.toUpperCase());
						actualPlayerName.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/maintenance remove " + name));
						actualPlayerName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.ITALIC + "Cliquez pour retirer l'accès au Joueur à la Maintenance !")));

						sender.sendMessage(new TextComponent(" "));
						sender.sendMessage(actualPlayerName);
					}

					sender.sendMessage(new TextComponent(" "));
					sender.sendMessage(new TextComponent(" "));
					sender.sendMessage(new TextComponent(ChatColor.GRAY + "==========================="));

				} else { sender.sendMessage(new TextComponent(prefixmaintenance + GI + "Aucun Joueur(s) ont accès à la Maintenance !")); }

			} else {

				sender.sendMessage(Type);
				return;
			}

		} else if(args.length == 2) {

			if(args[0].equalsIgnoreCase("add")) {

				List<String> listPlayers = (List<String>)main.maintenanceconfig.getList("whitelist");

				if(listPlayers != null && listPlayers.contains(args[1])) {

					sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.RED + " a déjà accès à la Maintenance !"));

				} else {

					listPlayers.add(args[1]);

					main.maintenanceconfig.set("whitelist", listPlayers);
					main.maintenanceconfig.saveConfig();

					sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.GREEN + " a maintenant accès à la Maintenance"));
				}

			} else if(args[0].equalsIgnoreCase("remove")) {

				List<String> listPlayers = (List<String>)main.maintenanceconfig.getList("whitelist");

				if(listPlayers != null && listPlayers.contains(args[1])) {

					listPlayers.remove(args[1]);

					main.maintenanceconfig.set("whitelist", listPlayers);
					main.maintenanceconfig.saveConfig();

					sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.GREEN + " n'a plus accès à la Maintenance"));

				} else {

					sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.RED + " n'a déjà pas accès à la Maintenance !"));
				}

			} else if(args[0].equalsIgnoreCase("on")) {

				if(ProxyServer.getInstance().getServerInfo(args[1]) != null) {

					ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[1]);

					if(main.maintenanceconfig.contains("maintenance_mode." + serverInfo.getName())) {

						sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.RED + " est dèjà en Maintenance !"));

					} else {

						main.maintenanceconfig.set("maintenance_mode." + serverInfo.getName(), true);
						main.maintenanceconfig.saveConfig();

						sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.GREEN + " est maintenant en Maintenance"));


						BaseComponent message = new TextComponent(prefixmaintenance + ChatColor.GOLD + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.GOLD + " est en Maintenance !");
						ProxyServer.getInstance().broadcast(message);
					}

				} else {

					if(args[1].equalsIgnoreCase("proxy")) {

						if(main.maintenanceconfig.contains("maintenance_mode.proxy")) {

							sender.sendMessage(new TextComponent(prefixmaintenance + prefixmaintenance + ChatColor.RED + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.RED + " est dèjà en Maintenance !"));

						} else {

							main.maintenanceconfig.set("maintenance_mode.proxy", true);
							main.maintenanceconfig.saveConfig();

							sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.GREEN + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.GREEN + " est maintenant en Maintenance"));


							BaseComponent message = new TextComponent(prefixmaintenance + ChatColor.GOLD + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.GOLD + " est en Maintenance !");
							ProxyServer.getInstance().broadcast(message);

						}

					} else {

						notfound = new TextComponent(prefixmaintenance + ChatColor.RED + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.RED + " est introuvable !");
						sender.sendMessage(notfound);
						return;
					}
				}

			} else if(args[0].equalsIgnoreCase("off")) {

				if(ProxyServer.getInstance().getServerInfo(args[1]) != null) {

					ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[1]);

					if(main.maintenanceconfig.contains("maintenance_mode." + serverInfo.getName())) {

						main.maintenanceconfig.removeKey("maintenance_mode." + serverInfo.getName());
						main.maintenanceconfig.saveConfig();

						sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.GREEN + " n'est plus en Maintenance"));


						BaseComponent message = new TextComponent(prefixmaintenance + ChatColor.GOLD + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.GOLD + " n'est plus en Maintenance !");
						ProxyServer.getInstance().broadcast(message);

					} else {

						sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + serverInfo.getName() + ChatColor.RED + " n'est pas en Maintenance !"));
					}

				} else {

					if(args[1].equalsIgnoreCase("proxy")) {

						if(main.maintenanceconfig.contains("maintenance_mode.proxy")) {

							main.maintenanceconfig.removeKey("maintenance_mode.proxy");
							main.maintenanceconfig.saveConfig();

							sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.GREEN + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.GREEN + " n'est plus en Maintenance"));


							BaseComponent message = new TextComponent(prefixmaintenance + ChatColor.GOLD + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.GOLD + " n'est plus en Maintenance !");
							ProxyServer.getInstance().broadcast(message);

						} else {

							sender.sendMessage(new TextComponent(prefixmaintenance + prefixmaintenance + ChatColor.RED + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.RED + " n'est pas en Maintenance !"));
						}

					} else {

						notfound = new TextComponent(prefixmaintenance + ChatColor.RED + "Le Serveur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[1] + ChatColor.RED + " est introuvable !");
						sender.sendMessage(notfound);
						return;
					}
				}

			} else {

				sender.sendMessage(Type);
				return;
			}

		} else {

			if(args[0].equalsIgnoreCase("motd")) {

				StringBuilder sb = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					sb.append(args[i]);
					sb.append(" ");
				}
				String combinedArgs = sb.toString();

				main.motdconfig.set("MOTD_MAINTENANCE", main.methodcustom.format(combinedArgs) + ChatColor.RESET);
				sender.sendMessage(new TextComponent(prefixmaintenance + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Vous avez changé le MOTD du Serveur"));

			} else {

				sender.sendMessage(Type);
				return;
			}
		}
	}

	/****************************************************/
	/* PARTIE COMMANDE POUR SE TÉLÉPORTER ENTRE SERVEUR */
	/****************************************************/
}
