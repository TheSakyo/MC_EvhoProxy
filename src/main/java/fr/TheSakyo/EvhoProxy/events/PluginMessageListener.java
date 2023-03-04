/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.events;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class PluginMessageListener implements Listener {
	
	/* Récupère la class "Main" */
	@SuppressWarnings("unused")
	private final ProxyMain main;
	public PluginMessageListener(ProxyMain pluginMain) { this.main = pluginMain; }
	/* Récupère la class "Main" */

	
	/**************************************************************/
	/* ÉVÈNEMENT POUR RÉCUPÉRER DES INFORMATIONS VENANT DE BUKKIT */
	/**************************************************************/
	
	@EventHandler
	public void OnReceiveData(PluginMessageEvent e) {

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
		String incomingChannel = e.getTag();

		if(!incomingChannel.equalsIgnoreCase(ProxyMain.channel)) return;

		try {

			String subChannel = in.readUTF();

			if(subChannel.equalsIgnoreCase("BungeeCount")) {

				String server = in.readUTF();
				if(e.getReceiver() instanceof ProxiedPlayer p) sendCustomDataCountPlayers(p, server, !p.isConnected());

			} else if(subChannel.equalsIgnoreCase("Command")) {

				String command = in.readUTF();

				if(e.getReceiver() instanceof ProxiedPlayer p) { sendCustomDataCommand(p, command); }

			} else if(subChannel.equalsIgnoreCase("ConnectServer")) {

				String playerName = in.readUTF();
				String serverName = in.readUTF();

				if(e.getReceiver() instanceof ProxiedPlayer p) { sendCustomConnectServer(p, playerName, serverName); }
			}

		} catch(IOException ex) { ex.printStackTrace(System.err); }
	}

	/**************************************************************/
	/* ÉVÈNEMENT POUR RÉCUPÉRER DES INFORMATIONS VENANT DE BUKKIT */
	/**************************************************************/
	   
	   
				/* --------------------------------------------------------------------------------------------------------------- */

	// Petite Méthode pour envoyer une information (commande) à un autre serveur //
	 protected void sendCustomDataCommand(ProxiedPlayer p, String command) {

		 ByteArrayOutputStream stream = new ByteArrayOutputStream();
		 DataOutputStream out = new DataOutputStream(stream);

		 try {

			 out.writeUTF("Command");
			 out.writeUTF(command);

		 } catch(IOException e) { e.printStackTrace(System.err); }


		 p.getServer().getInfo().sendData(ProxyMain.channelCustom, stream.toByteArray());


		 // Aprés une seconde, on exécute la commande //
		 new Timer().schedule(new TimerTask() {

			 @Override
			 public void run() { ProxyServer.getInstance().getPluginManager().dispatchCommand(p, command); }

		 }, 1000);
		 // Aprés une seconde, on exécute la commande //
	}
	// Petite Méthode pour envoyer une information (commande) à un autre serveur //



	// Petite Méthode pour connecter un Joueur dans un Serveur spécifique //
	protected void sendCustomConnectServer(ProxiedPlayer p, String... args) {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[1]);

		try {

			if(serverInfo == null) {

				out.writeUTF("ErrorConnectServer");
				out.writeUTF(main.prefix + ChatColor.RED + "Connexion au serveur impossible !");

			}

		} catch(IOException e) { e.printStackTrace(System.err); }

						/* ---------------------------- */

		if(serverInfo == null) { p.getServer().getInfo().sendData(ProxyMain.channelCustom, stream.toByteArray()); }
		else {

			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

			if(target.getServer().getInfo() != serverInfo) { target.connect(serverInfo); }
			else { target.sendMessage(new TextComponent(main.prefix + ChatColor.RED + "Vous êtes déjà connecté sur ce serveur !")); }
		}
	}
	// Petite Méthode pour connecter un Joueur dans un Serveur spécifique //
	   


	// Petite Méthode pour envoyer une information (Joueur(s) en ligne) à un autre serveur //
	protected void sendCustomDataCountPlayers(ProxiedPlayer p, String serverName, boolean disconnect) {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);

		ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);

												/* ----------------------------------- */

		try {

			out.writeUTF("BungeeCount");
			if(!serverName.equalsIgnoreCase("all") && serverInfo != null) {

				int playersOnline = serverInfo.getPlayers().size();

				out.writeInt(disconnect ? playersOnline - 1 : playersOnline);
				out.writeUTF(String.valueOf(serverInfo.getPlayers().size()));
				out.writeBoolean(false);

			} else {

				int playersOnline = ProxyServer.getInstance().getOnlineCount();

				out.writeInt(disconnect ? playersOnline - 1 : playersOnline);
				out.writeUTF("");
				out.writeBoolean(true);
			}

		} catch(IOException e) { e.printStackTrace(System.err); }

												/* ----------------------------------- */

		ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> {

			if(proxiedPlayer.getServer() != null) proxiedPlayer.getServer().getInfo().sendData(ProxyMain.channelCustom, stream.toByteArray());
		});

												/* ----------------------------------- */

		if(serverInfo != null) serverInfo.sendData(ProxyMain.channelCustom, stream.toByteArray());
	}
	// Petite Méthode pour envoyer une information (Joueur(s) en ligne) à un autre serveur //
}
