/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.events;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static fr.TheSakyo.EvhoProxy.managers.VersionManager.*;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class PingListener implements Listener {

	/* Récupère la class "Main" */
	private final ProxyMain main;
	public PingListener(ProxyMain pluginMain) { this.main = pluginMain; }
	/* Récupère la class "Main" */


    /*************************************/
    /* ÉVÈNEMENT DE PING SUR LE SERVEUR */
    /***********************************/
    @EventHandler
    public void onServerPing(ProxyPingEvent e) {
    	
       ServerPing ping = e.getResponse();
       String RBI = ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString()  + ChatColor.ITALIC.toString();


       if(main.maintenanceConfig.contains("maintenance_mode.proxy")) {

           BaseComponent MOTD = new TextComponent(ChatColor.translateAlternateColorCodes('&', main.configMOTD.getString("MOTD_MAINTENANCE")));
    	   ping.setDescriptionComponent(MOTD);

           if(versionServerIsSupported(ping)) {

                ping.setVersion(new ServerPing.Protocol(ChatColor.ITALIC + "Serveur en" + ChatColor.GOLD.toString() + ChatColor.ITALIC.toString() + " Maintenance", 0));

           } else { ping.setVersion(new ServerPing.Protocol(versionSupportedMessage(ChatColor.DARK_RED), 0)); }

       } else {

           if(versionServerIsSupported(ping)) { ping.setVersion(new ServerPing.Protocol(versionSupportedMessage(ChatColor.DARK_RED), 0)); }

           BaseComponent MOTD = new TextComponent(ChatColor.translateAlternateColorCodes('&', main.configMOTD.getString("MOTD")));
    	   ping.setDescriptionComponent(MOTD);
       }

       e.setResponse(ping);
    }
    /*************************************/
    /* ÉVÈNEMENT DE PING SUR LE SERVEUR */
    /***********************************/
}
