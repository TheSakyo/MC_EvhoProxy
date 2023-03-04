/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.utils;

import java.io.IOException;
import java.net.Socket;


import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.config.ServerInfo;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class CustomMethod {

	/* Récupère la class "Main" */
	@SuppressWarnings("unused")
	private ProxyMain main;
	public CustomMethod(ProxyMain pluginMain) {
		this.main = pluginMain;
	}
	/* Récupère la class "Main" */ 
	   
   
   
	/*********************************************************/
	/* TOUTE PETITE MÉTHODE POUR TRADUIRE LES CODES COULEURS */
	/********************************************************/
		  
	public String format(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }
		  
	/*********************************************************/
	/* TOUTE PETITE MÉTHODE POUR TRADUIRE LES CODES COULEURS */
	/********************************************************/
	
    
	
    // Petite Méthode pour vérifier la permission "LuckPerms" du joueur //
    public boolean hasLuckPermission(User user, String permission) {
    	
    	if(user.getCachedData().getPermissionData().getPermissionMap().containsKey(permission)) { return true; } 
    	
    	else { return false; }
      
    }
    // Petite Méthode pour vérifier la permission "LuckPerms" du joueur //    
    
    
    
    
    
    // Petite Méthode pour vérifier si le serveur précisé est en ligne (ou pas) //
    public boolean isServerOnline(ServerInfo server) {
        
    	//Variable pour établir la connexion au serveur demandé
    	Socket s = new Socket();
    	
        try {
        	
        	//Essaye de se connecter au serveur demandé
            s.connect(server.getSocketAddress());
            s.close();
            
            //Serveur "ONLINE"
            return true;
            
        } catch (IOException e) {
        	
        	//Serveur "OFFLINE"
	        return false;
        }
    }
    // Petite Méthode pour vérifier si le serveur précisé est en ligne (ou pas) //
	
}
