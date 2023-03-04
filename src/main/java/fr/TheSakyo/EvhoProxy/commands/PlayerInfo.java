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

public class PlayerInfo extends Command {

	  /* Récupère la class "Main" et créer la commande et sa permission */
	  private final ProxyMain main;
	  public PlayerInfo(ProxyMain pluginMain) {

		super("playerinfo", "EvhoProxy.playerinfo");
		this.main = pluginMain;
	  }
	  /* Récupère la class "Main" et créer la commande et sa permission */


	 /**************************************************************/
	 /* PARTIE COMMANDE POUR RÉCUPÉRER DES INFORMATIONS DU JOUEURS */
	 /**************************************************************/

	  public void execute(CommandSender sender, String[] args) {

		  if((sender instanceof ProxiedPlayer p)) {

			  if(!super.hasPermission(p)) {

				   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
				   p.sendMessage(error);
				   return;
			  }
		  }

		  BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /playerinfo <player>");

		  if(args.length == 1) {

			  if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

				BaseComponent playerinfo = new TextComponent(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + "Info du Joueur :");

				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

				BaseComponent successUUID = new TextComponent(ChatColor.GOLD + "UUID de " + ChatColor.YELLOW + target.getName() + ChatColor.GOLD + " :");
				BaseComponent uuid = new TextComponent(ChatColor.ITALIC + target.getUniqueId().toString());

				BaseComponent successAddress = new TextComponent(ChatColor.GOLD + "IP et PORT de " + ChatColor.YELLOW + target.getName() + ChatColor.GOLD + " :");
				BaseComponent address = new TextComponent(ChatColor.ITALIC + target.getSocketAddress().toString());

				BaseComponent dash = new TextComponent(ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "- ");

				sender.sendMessage(playerinfo);
				sender.sendMessage(new TextComponent(" "));
				sender.sendMessage(successUUID);
				sender.sendMessage(dash, uuid);
				sender.sendMessage(new TextComponent(" "));
				sender.sendMessage(successAddress);
				sender.sendMessage(dash, address);

			  } else {

				BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
				sender.sendMessage(notfound);
			  }

		  } else sender.sendMessage(Type);
	  }

	/**************************************************************/
	/* PARTIE COMMANDE POUR RÉCUPÉRER DES INFORMATIONS DU JOUEURS */
	/**************************************************************/
}
