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

    //PROTOCOL DE LA VERSION MINIMUM SUPPORTÉE PAR LE SERVEUR
    public static ServerPing.Protocol MINIMUM_VERSION = new ServerPing.Protocol("1.20", 763);

    /* --------------------------------------------- */
    /* --------------------------------------------- */
    /* --------------------------------------------- */

    /**
     *
     * Vérifie si le joueur est bien sur la bonne version supportée par le Serveur ou non.
     *
     * @param ping - Le Ping du Serveur en question
     *
     * @return Une valeur booléenne
     */
    public static boolean versionServerIsSupported(ServerPing ping) {

        return ping.getVersion().getProtocol() >= MINIMUM_VERSION.getProtocol();
    }

                                                            /* --------------------------------- */
    /**
     *
     * Vérifie si le joueur lors sa connexion est bien sur la bonne version supportée par le Serveur ou non.
     *
     * @param pendingConnection - La connexion du Joueur en question
     *
     * @return Une valeur booléenne
     */
    public static boolean versionPlayerIsSupported(PendingConnection pendingConnection) {

        return pendingConnection.getVersion() >= MINIMUM_VERSION.getProtocol();
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

        return color + ChatColor.ITALIC.toString() + MINIMUM_VERSION.getName() + " et +";
    }
}
/************************************************************************/
/* QUELQUES MÉTHODES POUR GÉRER LES VERSIONS SUPPORTÉES PAR LE SERVEUR */
/**********************************************************************/