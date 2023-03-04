/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class EvhoProxyReload extends Command {

	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private final ProxyMain main;
	public EvhoProxyReload(ProxyMain pluginMain) {

		super("evhoproxyreload", "EvhoProxy.reload", "evhoproxyrl", "evhopxreload", "evhopxrl");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/*********************************************************/
	/* PARTIE COMMANDE POUR FAIRE RECHARGER LE PLUGIN BUNGEE */
	/*********************************************************/

	public void execute(CommandSender sender, String[] args) {

		if((sender instanceof ProxiedPlayer p)) {

			if(!super.hasPermission(p)) {

				  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
				  p.sendMessage(error);
				  return;
			  }


			main.onDisable();
			main.onEnable();

			BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Plugin Rechargé avec Succès !");
			p.sendMessage(success);
			return;

		  }

		if((sender instanceof CommandSender)) {

			main.onDisable();
			main.onEnable();

			BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Plugin Rechargé avec Succès !");
			sender.sendMessage(success);
		}
	}

	/*********************************************************/
	/* PARTIE COMMANDE POUR FAIRE RECHARGER LE PLUGIN BUNGEE */
	/*********************************************************/
}
