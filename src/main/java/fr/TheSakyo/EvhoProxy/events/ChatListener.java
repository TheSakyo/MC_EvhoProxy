/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.events;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ChatListener implements Listener {

	/* Récupère la class "Main" */
	private ProxyMain main;
	public ChatListener(ProxyMain pluginMain) { this.main = pluginMain; }
	/* Récupère la class "Main" */


    /*******************/
    /* ÉVÈNEMENT TCHAT */
    /*******************/

    @EventHandler
    public void onChat(ChatEvent e) {
    	
        if(e.getSender() instanceof ProxiedPlayer) {
        	
            String message = e.getMessage();

            if(message.equalsIgnoreCase("/lobby")) main.Commandlabel = "lobby";
            else if(message.equalsIgnoreCase("/hub")) main.Commandlabel = "hub";
        }
            
     }

    /*******************/
    /* ÉVÈNEMENT TCHAT */
    /*******************/
    
}
    
