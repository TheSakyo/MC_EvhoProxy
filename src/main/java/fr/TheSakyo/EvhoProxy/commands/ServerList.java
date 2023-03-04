/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import java.util.Map.Entry;

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

public class ServerList extends Command {

	/* Récupère la class "Main" et créer la commande */
	private ProxyMain main;
	public ServerList(ProxyMain pluginMain) {
	super("serverlist", "EvhoProxy.serverlist");
	this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande */


	/****************************************************/
	/* PARTIE COMMANDE POUR AVOIR LA LISTE DES SERVEURS */
	/****************************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /serverlist");

		if((sender instanceof ProxiedPlayer p)) {

			if(!super.hasPermission(p)) {

				   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
				   p.sendMessage(error);
				   return;
				 }
			}


		if(args.length == 0) {

				BaseComponent serverlist = new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + "Liste des Serveurs :");

				BaseComponent tiret = new TextComponent(ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "- ");

				BaseComponent servername = null;


				String wi = ChatColor.WHITE.toString() + ChatColor.ITALIC.toString();

				String gi = ChatColor.GREEN.toString() + ChatColor.ITALIC.toString();

				String ri = ChatColor.RED.toString() + ChatColor.ITALIC.toString();

				sender.sendMessage(serverlist);
				sender.sendMessage(new TextComponent(" "));

				for(ServerInfo server : ProxyServer.getInstance().getServersCopy().values()) {

					if(main.methodcustom.isServerOnline(server)) {

						servername = new TextComponent(ChatColor.GRAY.toString() + ChatColor.BOLD.toString() + server.getName() + wi + " (" + gi + "en ligne" + wi + ")");

					} else {

						servername = new TextComponent(ChatColor.GRAY.toString() + ChatColor.BOLD.toString() + server.getName() + wi + " (" + ri + "hors-ligne" + wi + ")");
					}

					sender.sendMessage(tiret, servername);
				}

		  } else {

			  sender.sendMessage(Type);
			  return;
		  }
	}

	/****************************************************/
	/* PARTIE COMMANDE POUR AVOIR LA LISTE DES SERVEURS */
	/****************************************************/

}
