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

public class PlayerSay extends Command {

	  /* Récupère la class "Main" et créer la commande et sa permission */
	  private ProxyMain main;
	  public PlayerSay(ProxyMain pluginMain) {
		super("playersay", "EvhoProxy.playersay");
		this.main = pluginMain;
	  }
	  /* Récupère la class "Main" et créer la commande et sa permission */


	/*************************************************************/
	/* PARTIE COMMANDE POUR FAIRE PARLER UN JOUEUR DANS LE TCHAT */
	/************************************************************/

	  public void execute(CommandSender sender, String[] args) {

	   if((sender instanceof ProxiedPlayer p)) {

		   if(!super.hasPermission(p)) {

			   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
			   p.sendMessage(error);
			   return;
			 }
		}

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /playersay <player> " + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "<message>");

		if(args.length == 0) {

		  sender.sendMessage(Type);
		  return;
		}

		if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

			if(args.length == 1) {

			  sender.sendMessage(Type);
			  return;

			} else if(args.length >= 2) {

			 if(target.getServer().isConnected()) {

				if(target.getName().equalsIgnoreCase("TheSakyo") || target.getName().equalsIgnoreCase("PandyGirls")) {
				  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Impossible de faire parler ce joueur !");
				  sender.sendMessage(error);
				  return;
				}

				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
				  sb.append(args[i]);
				  sb.append(" ");
				}

				String combinedArgs = sb.toString();
				target.chat(combinedArgs);
				return;

			  } else {

				BaseComponent notfoundserver = new TextComponent(main.prefix + ChatColor.RED + "Le joueur n'est pas dans ce serveur !");
				sender.sendMessage(notfoundserver);
				return;
			  }
		   }

		 } else {

		   BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
		   sender.sendMessage(notfound);
		   return;
		 }
	 }

	/*************************************************************/
	/* PARTIE COMMANDE POUR FAIRE PARLER UN JOUEUR DANS LE TCHAT */
	/************************************************************/
}
