/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ServerInfo extends Command {

	/* Méthode lorsque le joueur Clique/Passe la souris sur un message */

	//Variable passage de souris
	HoverEvent.Action show_text = HoverEvent.Action.SHOW_TEXT;

	//Variable Clique
	ClickEvent.Action clic_text = ClickEvent.Action.OPEN_URL;

	//Méthode passage de souris
	private HoverEvent Hover(HoverEvent.Action action, Text value) { return new HoverEvent(action, (Content)value); }

	//Méthode Clique
	private ClickEvent Clic(ClickEvent.Action action, String value) {
	return new ClickEvent(action, value);
	}

	/* Méthode lorsque le joueur Clique/Passe la souris sur un message */


	/* Récupère la class "Main" et créer la commande */
	private ProxyMain main;
	public ServerInfo(ProxyMain pluginMain) {
		super("serverinfo");
		this.main = pluginMain;
	}
	/* Récupère la class "Main" et créer la commande */


	/**************************************************************/
	/* PARTIE COMMANDE POUR AVOIR DES INFORMATIONS SUR LE SERVEUR */
	/*************************************************************/

	public void execute(CommandSender sender, String[] args) {

		//Récupère l'IP dans le fichier de Configuration 'info_server.yml', s'il existe
		String IPInfo = (main.infoServerconfig.getString("IP").isBlank()) ? "play.example.com" : main.infoServerconfig.getString("IP");

		//Récupère le Discord dans le fichier de Configuration 'info_server.yml', s'il existe
		String DISCORDInfo = (main.infoServerconfig.getString("DISCORD").isBlank()) ? "https://discord.com/invite/minecraft" : main.infoServerconfig.getString("DISCORD");

		//Récupère le Site dans le fichier de Configuration 'info_server.yml', s'il existe
		String WEBSITEInfo = (main.infoServerconfig.getString("WEBSITE").isBlank()) ? "https://www.minecraft.net" : main.infoServerconfig.getString("WEBSITE");

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /serverinfo");

		if(sender instanceof ProxiedPlayer) {

		  if(args.length == 0) {

			ProxiedPlayer p = (ProxiedPlayer)sender;

			BaseComponent discord = new TextComponent(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + "Discord du Serveur");


			discord.setHoverEvent(Hover(this.show_text, new Text(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString() +  "Cliquez pour rejoindre le Discord")));
			discord.setClickEvent(Clic(this.clic_text, DISCORDInfo));

			BaseComponent site = new TextComponent(ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + ChatColor.ITALIC.toString() + "Site du Serveur");


			site.setHoverEvent(Hover(this.show_text, new Text(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString() + "Cliquez pour rejoindre le Site")));
			site.setClickEvent(Clic(this.clic_text, WEBSITEInfo));

			BaseComponent ip = new TextComponent(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD.toString() + "IP : " + ChatColor.WHITE.toString() + ChatColor.ITALIC.toString() + IPInfo);

			BaseComponent info = new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + "Info du Serveur :");

			BaseComponent tiret = new TextComponent(ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "- ");

			p.sendMessage(info);
			p.sendMessage(new TextComponent(" "));
			p.sendMessage(ip);
			p.sendMessage(new TextComponent(" "));
			p.sendMessage(tiret, discord);
			p.sendMessage(tiret, site);
			return;

		  } else {

			sender.sendMessage(Type);
			return;
		  }
		}

		if(sender instanceof CommandSender) {

		  BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");
		  sender.sendMessage(error);
		  return;
		}
	}

	/**************************************************************/
	/* PARTIE COMMANDE POUR AVOIR DES INFORMATIONS SUR LE SERVEUR */
	/*************************************************************/
}
