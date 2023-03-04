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

public class PrivateMessage extends Command {

    /* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private final ProxyMain main;
	public PrivateMessage(ProxyMain pluginMain) {

		super("privatemessage", null, "msg");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/***************************************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN MESSAGE PRIVÉ À UN JOUEUR  */
	/***************************************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound;
		ServerInfo server = null;
		BaseComponent messagePrivate;

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /msg <player> <message> !");

		if(args.length == 0) sender.sendMessage(Type);
		else if(args.length >= 2) {

			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < args.length; i++) {

				sb.append(args[i]);
				sb.append(" ");
			}

			String combinedArgs = sb.toString();

			String GI = ChatColor.GRAY.toString() + ChatColor.ITALIC.toString();

			String prefixMsg = GI + "[Message Privé de " + sender.getName() + "] : \n\"" + ChatColor.RESET + " ";
			if(sender.hasPermission("evhoutility.chatcolor")) { messagePrivate = new TextComponent(prefixMsg + ChatColor.RESET.toString() + main.methodCustom.format(combinedArgs) + GI + "\""); }
			else { messagePrivate = new TextComponent(prefixMsg + ChatColor.RESET.toString() + combinedArgs + GI + "\""); }

			notfound = new TextComponent(main.prefix + ChatColor.RED + "Le Joueur " + ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + args[0] + ChatColor.RED + " est introuvable !");

			if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

				BaseComponent messageSuccess;

				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				target.sendMessage(messagePrivate);

				String prefixMsgSuccess = GI + "[Votre Message Privée envoyé à " + target.getName() + "] : \n\"" + ChatColor.RESET + " ";
				if(sender.hasPermission("evhoutility.chatcolor")) { messageSuccess = new TextComponent(prefixMsgSuccess + ChatColor.RESET.toString() + main.methodCustom.format(combinedArgs) + GI + "\""); }
				else { messageSuccess = new TextComponent(prefixMsgSuccess + ChatColor.RESET.toString() + combinedArgs + GI + "\""); }

				sender.sendMessage(messageSuccess);

            } else sender.sendMessage(notfound);

        } else sender.sendMessage(Type);
	}

	/***************************************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN MESSAGE PRIVÉ À UN JOUEUR  */
	/***************************************************************/
}
