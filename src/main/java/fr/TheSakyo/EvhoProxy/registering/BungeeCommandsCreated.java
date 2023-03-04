/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.registering;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ProxyServer;

import fr.TheSakyo.EvhoProxy.commands.*;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class BungeeCommandsCreated {
	
	 /* Récupère la class "Main" */
	 private ProxyMain main;
	 public BungeeCommandsCreated(ProxyMain pluginMain) { this.main = pluginMain; }
	 /* Récupère la class "Main" */
	 
	 /**********************************/
	 /* CHARGEMENT DES CLASS COMMANDES */ 
	 /**********************************/	
	 public void getCommands() {
		 
		//Commande Reload du Plugin 
		ProxyServer.getInstance().getPluginManager().registerCommand(main, new EvhoProxyReload(main));

		//Commande pour le lister les plugins
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new PluginsList(main));
		
		//Commande pour aller au lobby/hub
		ProxyServer.getInstance().getPluginManager().registerCommand(main, new Lobby(main));
		
		//Commande bonus pour faire parler un joueur
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new PlayerSay(main));
	    
	    //Commande pour récupèrer l'UUID du joueur
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new PlayerInfo(main));
	    
	    //Commande pour voir les infos du serveur
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new ServerInfo(main));
	    
	    //Commande pour voir la liste des serveurs
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new Server(main));
	    
	    //Commande pour la téléportation entre serveurs
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new ServerList(main));
	    
	    //Commande pour être en mode Vanish
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new Vanish(main));
	    
	    //Commande pour freeze un jouer
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new Freeze(main));
	    
	    //Commande pour avoir un "nickname"
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new NickName(main));

		//Commande pour avoir un skin customisé
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new ChangeSkin(main));

	    //Commande pour faire un broadcast
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new Broadcast(main));

	    //Commande pour faire un title
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new SendTitle(main));

		//Commande pour faire un subtitle
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new SubTitle(main));

		//Commande pour faire un message privée
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new PrivateMessage(main));

		//Commande pour gérer la Maintenance
	    ProxyServer.getInstance().getPluginManager().registerCommand(main, new Maintenance(main));
	}
	/**********************************/
	/* CHARGEMENT DES CLASS COMMANDES */ 
	/**********************************/
}
