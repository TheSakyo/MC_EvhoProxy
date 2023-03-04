/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

public class Freeze extends Command {
	
   /* Récupère la class "Main" et créer la commande */
   private final ProxyMain main;
   public Freeze(ProxyMain pluginMain) {

	 super("freeze", "EvhoProxy.freeze");
	 this.main = pluginMain;
   }
   /* Récupère la class "Main" et créer la commande */


	/*******************************************/
	/* PARTIE COMMANDE POUR 'FREEZE' UN JOUEUR */
	/*******************************************/

	@Override
	public void execute(CommandSender sender, String[] args) {

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /freeze <player> !");

		if((sender instanceof ProxiedPlayer p)) {

			if(!super.hasPermission(p)) {

			   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
			   p.sendMessage(error);
			   return;
			 }
		}

		if(args.length != 1) { sender.sendMessage(Type); }
		else {

			if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

				if((sender instanceof ProxiedPlayer p)) {

					frozenPlayer(target.getServer().getInfo(), p.getName(), target);
					return;
				}

				frozenPlayer(target.getServer().getInfo(), "console", target);

			 } else {

				BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
				sender.sendMessage(notfound);
			 }
		}
	}
	/*******************************************/
	/* PARTIE COMMANDE POUR 'FREEZE' UN JOUEUR */
	/*******************************************/


	// Petite Méthode pour 'freeze' un joueur (envoie depuis les autres Serveurs) //
	private void frozenPlayer(ServerInfo server, String sender, ProxiedPlayer p) {

		String forwardSTR = main.freezePrefix + ", " + p.getName() + ", " + sender;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {

            out.writeUTF("Freezed"); //Écrit la donnée clé 'Freezed'
            out.writeUTF(forwardSTR); //Écrit le reste des données.

        } catch(IOException e) { e.printStackTrace(System.err); }

		server.sendData(ProxyMain.channel, bytes.toByteArray());
	}
	// Petite Méthode pour 'freeze' un joueur (envoie depuis les autres Serveurs) //

}
