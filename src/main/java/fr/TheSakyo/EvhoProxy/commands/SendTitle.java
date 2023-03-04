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
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class SendTitle extends Command {

    /* Récupère la class "Main" et créer la commande avec ses alias et sa permission */
	private final ProxyMain main;
	public SendTitle(ProxyMain pluginMain) {

		super("sendtitle", "EvhoProxy.sendtitle", "st", "title");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande avec ses alias et sa permission */


	/******************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN TITRE */
	/*****************************************/

	public void execute(CommandSender sender, String[] args) {

		BaseComponent notfound = null;
		ServerInfo server = null;

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /sendtitle ou /st <all/'servername'/'playername'> <message> !");
		BaseComponent Success = new TextComponent(main.prefix + ChatColor.GRAY.toString() + ChatColor.ITALIC.toString() + "Titre envoyé !");

		if(args.length == 0 || args.length == 1) sender.sendMessage(Type);
		else {

			StringBuilder sb = new StringBuilder();

			for(int i = 1; i < args.length; i++) {

				sb.append(args[i]);
				sb.append(" ");
			}

			String combinedArgs = sb.toString();

			BaseComponent message = new TextComponent(main.methodCustom.format(combinedArgs));
			Title titleMessage = ProxyServer.getInstance().createTitle().title(message).fadeOut(100 / 20);

            SubTitle.executeTitle(sender, args, Success, titleMessage, main);
        }
	}

	/******************************************/
	/* PARTIE COMMANDE POUR AFFICHER UN TITRE */
	/*****************************************/
}
