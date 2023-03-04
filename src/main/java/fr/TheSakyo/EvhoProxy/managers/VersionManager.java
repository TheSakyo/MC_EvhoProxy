/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.managers;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.PendingConnection;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

/************************************************************************/
/* QUELQUES MÉTHODES POUR GÉRER LES VERSIONS SUPPORTÉES PAR LE SERVEUR */
/**********************************************************************/
public class VersionManager {

    /**
     *
     * Vérifie si le joueur est bien sur la bonne version supportée par le Serveur ou non.
     *
     * @param ping - Le Ping du Serveur en question
     *
     * @return Une valeur boolèenne
     */
    public static boolean versionServerIsSupported(ServerPing ping) {

        if(ping.getVersion().getProtocol() < ProxyMain.MINIMUM_VERSION.getProtocol()) return false;
        return true;
    }

                                                            /* --------------------------------- */
    /**
     *
     * Vérifie si le joueur lors sa connexion est bien sur la bonne version supportée par le Serveur ou non.
     *
     * @param pendingConnection - La connexion du Joueur en question
     *
     * @return Une valeur boolèenne
     */
    public static boolean versionPlayerIsSupported(PendingConnection pendingConnection) {

        if(pendingConnection.getVersion() < ProxyMain.MINIMUM_VERSION.getProtocol()) return false;
        return true;
    }

                                                         /* ------------------------------------------------------ */

    /**
     * Un Message disant les versions supportées.
     *
     * @param color - La Couleur du Message qui sera retourné
     *
     * @return Un Message informant les versions supportées
     */
    public static String versionSupportedMessage(ChatColor color) {

        return color + ChatColor.ITALIC.toString() + ProxyMain.MINIMUM_VERSION.getName() + " et +";
    }

}
/************************************************************************/
/* QUELQUES MÉTHODES POUR GÉRER LES VERSIONS SUPPORTÉES PAR LE SERVEUR */
/**********************************************************************/