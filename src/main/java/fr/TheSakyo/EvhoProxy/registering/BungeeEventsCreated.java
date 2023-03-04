/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.registering;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ProxyServer;

import fr.TheSakyo.EvhoProxy.events.*;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class BungeeEventsCreated {

	 /* Récupère la class "Main" */
	 private ProxyMain main;
	 public BungeeEventsCreated(ProxyMain pluginMain) { this.main = pluginMain; }
	 /* Récupère la class "Main" */
	 
	 /*************************************/
	 /* CHARGEMENT DES CLASS D'EVENEMENTS */ 
	 /*************************************/	
	 public void getEvents() {
		 
		//Évènement pour le tchat
	    ProxyServer.getInstance().getPluginManager().registerListener(main, new ChatListener(main));
	    
	    
	    //Évènement pour le 'ping'
	    ProxyServer.getInstance().getPluginManager().registerListener(main, new PingListener(main));
	    
	    
	    //Évènement quand le joueur se connecte dans un serveur
	    ProxyServer.getInstance().getPluginManager().registerListener(main, new ConnectListener(main));
	    
	    
	    //Évènement pour les "plugins messages"
	    ProxyServer.getInstance().getPluginManager().registerListener(main, new PluginMessageListener(main));
	}
	 /*************************************/
	 /* CHARGEMENT DES CLASS D'EVENEMENTS */ 
	 /*************************************/
	 
}
