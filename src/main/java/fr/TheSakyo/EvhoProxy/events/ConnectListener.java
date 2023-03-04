/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.events;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import fr.TheSakyo.EvhoProxy.utils.PluginMessageClass.Advancements;
import fr.TheSakyo.EvhoProxy.utils.PluginMessageClass.PlayerChanger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static fr.TheSakyo.EvhoProxy.managers.VersionManager.*;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ConnectListener implements Listener {

	/* Récupère la class "Main" */
	@SuppressWarnings("unused")
	private final ProxyMain main;
	public ConnectListener(ProxyMain pluginMain) { this.main = pluginMain; }
	/* Récupère la class "Main" */


    /***************************************/
    /* ÉVÈNEMENT DE CONNEXION/DÉCONNEXION */
    /*************************************/

    @EventHandler
    @SuppressWarnings("unchecked")
    public void OnServerConnect(ServerConnectEvent e) {

                         /* ------------------------------------ */

        final ProxiedPlayer p = e.getPlayer();
        final ServerInfo serverTarget = e.getTarget();
        List<String> listPlayers = (List<String>)main.maintenanceConfig.getList("whitelist");

                    /* ------------------------------------ */

         if(p.isConnected() && main.maintenanceConfig.contains("maintenance_mode." + serverTarget.getName()) && (listPlayers != null && !listPlayers.contains(p.getName()))) {

             BaseComponent maintenanceMessage = new TextComponent(ChatColor.DARK_RED + "Le Serveur " + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + serverTarget.getName()  + ChatColor.DARK_RED + " est en Maintenance !");
             BaseComponent errorToConnectMessage = new TextComponent(ChatColor.RED + "Il est donc impossible de s'y connecter...");

             p.sendMessage(maintenanceMessage, new TextComponent(" "), errorToConnectMessage);
             e.setCancelled(true);
             return;
         }
                            /* ------------------------------------ */

        if(p.isConnected() && main.freezeP.contains(p)) {

            BaseComponent freezeMessage = new TextComponent(ChatColor.RED + "Vous ne pouvez pas changer de Serveur, vous êtes Freeze !");

            p.sendMessage(freezeMessage);
            e.setCancelled(true);
            return;
        }

        e.setCancelled(false);

                         /* ------------------------------------ */

        if(p.getServer() != null) new PluginMessageListener(main).sendCustomDataCountPlayers(p, p.getServer().getInfo().getName(), true);

                        /* ------------------------------------ */

        // Aprés une seconde, on recharge le nom d'Affichage du Joueur //
		ProxyServer.getInstance().getScheduler().schedule(main, () -> {

            PlayerChanger.Check_CustomName(serverTarget, p);
            new PluginMessageListener(main).sendCustomDataCountPlayers(p, serverTarget.getName(), false);
        }, 1, TimeUnit.SECONDS);
		// Aprés une seconde, on recharge le nom d'Affichage du Joueur //

                         /* ------------------------------------ */
    }

            /* ------------------------------------------------------------------------------------------- */

    @EventHandler
    @SuppressWarnings("unchecked")
    public void OnPlayerLogin(PostLoginEvent e) {

                         /* ------------------------------------ */

        final ProxiedPlayer p = e.getPlayer();
        List<String> listPlayers = (List<String>)main.maintenanceConfig.getList("whitelist");

                            /* ------------------------------------ */

         if(main.maintenanceConfig.contains("maintenance_mode.proxy") && (listPlayers != null && !listPlayers.contains(p.getName()))) {

             p.disconnect(new TextComponent(ChatColor.DARK_RED + "Le Serveur est en Maintenance !\n\n" + ChatColor.RED + "Veuillez revenir ultérieurement."));
             return;

         } else if(!versionPlayerIsSupported(p.getPendingConnection())) {

            BaseComponent errorMessage = new TextComponent(ChatColor.DARK_RED + "Le Serveur ne supporte pas la version dans laquelle vous essayez de vous connecter !");
            BaseComponent supportVersionMessage = new TextComponent(ChatColor.GOLD.toString() + ChatColor.UNDERLINE.toString() + "Version(s) Supportée(s):" + ChatColor.RESET + " " + versionSupportedMessage(ChatColor.GREEN));

            p.disconnect(errorMessage, new TextComponent("\n\n"), supportVersionMessage);
            return;
        }
                         /* ------------------------------------ */

        // Aprés une seconde, on recharge le nom d'Affichage du Joueur //
		ProxyServer.getInstance().getScheduler().schedule(main, () -> {

            new PluginMessageListener(main).sendCustomDataCountPlayers(p, "all", false);

        }, 1, TimeUnit.SECONDS);
		// Aprés une seconde, on recharge le nom d'Affichage du Joueur //

                         /* ------------------------------------ */

        // Aprés trois secondes, on affiche un 'achievement' customisé //
		ProxyServer.getInstance().getScheduler().schedule(main, () -> {

            // Récupère le titre à afficher en achievement
            String title = ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString() + " a rejoint le Serveur !";
            String description = "Un Administrateur vient de se connecté !"; // Récupère la petite description a affiché en achievement

            // Créer un achievement qui sera envoyé aux autres Serveurs (Message Personnalisé), si le joueur est bien dans un Serveur
            if(p.getServer() != null) {

                Advancements.create(p.getServer().getInfo(), p, true, title, description, false, false, "TASK" , "ALWAYS", "ENTITY_WITHER_AMBIENT", true);
            }

        }, 3, TimeUnit.SECONDS);
		// Aprés trois secondes, on affiche un 'achievement' customisé //

                            /* ------------------------------------ */
    }

                    /* ------------------------------------------------------ */

    @EventHandler
    public void OnPlayerDisconnect(PlayerDisconnectEvent e) {

                            /* ------------------------------------ */

        final ProxiedPlayer p = e.getPlayer();

                             /* ------------------------------------ */

        if(p.getServer() != null) {

            // Récupère le titre à afficher en achievement
            String title = ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString() + " a quitter le Serveur !";
            String description = "Un Administrateur vient de se déconnecté !"; // Récupère la petite description a affiché en achievement

            new PluginMessageListener(main).sendCustomDataCountPlayers(p, "all", true);

            // Créer un achievement qui sera envoyé aux autres Serveurs (Message Personnalisé)
            Advancements.create(p.getServer().getInfo(), p, true, title, description, false, false, "TASK" , "ALWAYS", "ENTITY_WITHER_AMBIENT", true);
        }
                             /* ------------------------------------ */
    }
            /* ------------------------------------------------------------------------------------------- */

    /***************************************/
    /* ÉVÈNEMENT DE CONNEXION/DÉCONNEXION */
    /*************************************/
}
