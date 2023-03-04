/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.TheSakyo.EvhoProxy.config.ConfigFile;
import fr.TheSakyo.EvhoProxy.config.ConfigFileManager;
import fr.TheSakyo.EvhoProxy.registering.BungeeCommandsCreated;
import fr.TheSakyo.EvhoProxy.registering.BungeeEventsCreated;
import fr.TheSakyo.EvhoProxy.utils.CustomMethod;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ProxyMain extends Plugin {

	//Variable pour récupérer le plugin
	public static Plugin plugin;
	
	// Variable pour récupérer l'instance du plugin et de l'api "Maintenance"/"LuckPerm"/"ViaVersion" //
	public static ProxyMain instance;
	public LuckPerms luckapi;
	// Variable pour récupérer l'instance du plugin et de l'api "Maintenance"/"LuckPerm"/"ViaVersion" //
	
	// Variable pour les fichiers config personnalisée //
    public ConfigFileManager config_manager = new ConfigFileManager(this);
    public ConfigFile motdconfig;
	public ConfigFile infoServerconfig;
	public ConfigFile maintenanceconfig;
    
    //Partie Configuration des "nicknames" et "skinnames"
	public Configuration playerNickConfig;
	public Configuration playerSkinConfig;

	public File nickConfigFile;
	public File skinConfigFile;
    // Variable pour les fichiers config personnalisée //


	// Variable pour la liste des joueurs "Freeze" //
	public List<ProxiedPlayer> freezeP = new ArrayList<ProxiedPlayer>();
	// Variable pour la liste des joueurs "Freeze" //

	
	// Variable pour Détecter la Console //
	public CommandSender console = ProxyServer.getInstance().getConsole();
	// Variable pour Détecter la Console //
	
	// Variable pour le Nom du Plugin //
	public String prefix = ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "[" + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD.toString() + "Evho" + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "Proxy" + ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "]" + ChatColor.RESET + " ";
	// Variable pour le Nom du Plugin //

	// Variable pour le Préfix du Message de 'Freeze' //
	public String freezePrefix = ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "[" + ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "FREEZE" + ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "]" + ChatColor.RESET + " ";
	// Variable pour le Préfix du Message de 'Freeze' //


	// Variable pour Charger la class "BungeeCommandsCreated" et la class "BungeeEventsCreated" //
	BungeeCommandsCreated commands = new BungeeCommandsCreated(this);
	BungeeEventsCreated events = new BungeeEventsCreated(this);
	// Variable pour Charger la class "BungeeCommandsCreated" et la class "BungeeEventsCreated" //
	
	//Variable pour récupérer la class "HologramManager"
    public CustomMethod methodcustom = new CustomMethod(this); 

	// Petite Variable pour récupérer le nom d'une commande //
	public String Commandlabel = null;
	// Petite Variable pour récupérer le nom d'une commande //


	// Variable permettant d'enregistrer les Noms Customisés des Joueurs (nickname) //
	public HashMap<ProxiedPlayer, String> playerCustomName = new HashMap<ProxiedPlayer, String>();
	// Variable permettant d'enregistrer les Noms Customisés des Joueurs (nickname) //


	// Variable permettant d'enregistrer les Skins Customisés des Joueurs (changeskin) //
	public HashMap<ProxiedPlayer, String> playerSkinName = new HashMap<ProxiedPlayer, String>();
	// Variable permettant d'enregistrer les Skins Customisés des Joueurs (changeskin) //


	//Nom de canal de base 'BungeeCord' pour envoyer des informations entre serveurs
	public static String channel = "BungeeCord";


	//Nom de canal customisé 'evhonia:evhoproxy' pour envoyer des informations entre serveurs
	public static String channelcustom = "evhonia:evhoproxy";


				/* --------------------------------------------- */

    public static ServerPing.Protocol MINIMUM_VERSION = new ServerPing.Protocol("1.17.1", 756); //PROTOCOL DE LA VERSION MINIMUM SUPPORTÉE PAR LE SERVEUR
    
    				/* --------------------------------------------- */
	
	/****************************************************/
	/* CRÉATIONS D'INSTANCES POUR LIRE DES CLASS UTILES */
	/****************************************************/
	
	// Instance et Imporation Plugin des différents API (utile pour lire ses class ensuite) //
	
	private Plugin getLuckPermsPlugin() { return ProxyServer.getInstance().getPluginManager().getPlugin("LuckPerms"); }

	private void LuckPermsInstance() { luckapi = LuckPermsProvider.get(); }
	
	
	//Récupère toutes les instances des différents API en une seule méthode
	private void getInstanceAPI() { LuckPermsInstance(); }
	
	// Instance et Imporation Plugin  des différents API (utile pour lire ses class ensuite) //
	
	
    //Instance de la class "Main"
    public static ProxyMain getInstance() {
        return instance;
    }
    
    /****************************************************/
	/* CRÉATIONS D'INSTANCES POUR LIRE DES CLASS UTILES */
	/****************************************************/
   
   
   
   
    /**************************************************/
    /* PARTIE AVEC ACTIVATION/DÉSACTIVATION DU PLUGIN */
    /**************************************************/
   
    @Override
    public void onEnable() {
    	
    	if(getLuckPermsPlugin() != null) {
    		
    		plugin = (Plugin)this; 
 		   
    		instance = this;
    		
    		getInstanceAPI();
    		
    		config_manager.LoadConfig();
    		
            ProxyServer.getInstance().registerChannel(channel);
    		
    		BaseComponent enabled = new TextComponent(prefix + ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "Plugin Proxy Enabled");
            console.sendMessage(enabled);
            
            commands.getCommands();
            events.getEvents();
            
    	} else { CancelEnable(); }
    } 
    
    @Override
    public void onDisable() {
  	  
 	   instance = null;
 	   
 	   BaseComponent disabled = new TextComponent(prefix + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Plugin Proxy Disabled");
       console.sendMessage(disabled);
 	  
 	}
    
    /**************************************************/
    /* PARTIE AVEC ACTIVATION/DÉSACTIVATION DU PLUGIN */
    /**************************************************/
    
    
    
    
   // Petite Méthode pour annuler l'activation du plugin (en cas d'API non valide) //
   private void CancelEnable() {
   	
		BaseComponent space = null;
		BaseComponent disabled = null;

		if(getLuckPermsPlugin() == null) {
   			
   			String debut = prefix + ChatColor.RED + "Il vous manque le plugin suivant : ";
   			disabled = new TextComponent(debut + ChatColor.YELLOW + "LuckPerms-Bungee");
   		}

	   console.sendMessage(new TextComponent(" "));
	   console.sendMessage(disabled);
	   console.sendMessage(new TextComponent(" "));
		   
	   onDisable();
   }
   // Petite Méthode pour annuler l'activation du plugin (en cas d'API non valide) //
      
}
