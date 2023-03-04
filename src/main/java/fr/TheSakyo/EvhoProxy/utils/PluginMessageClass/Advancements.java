/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.utils.PluginMessageClass;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class Advancements {

    //*** DÉBUT ACHIEVEMENTS ***\\


    // Créer un 'achievement' customisé et l'envoie un serveur demandé //
    public static void create(ServerInfo server, final ProxiedPlayer p, boolean getPlayerWithGrade, String Title, String Description,
                              boolean showToast, boolean announceChat, String Frame, String Visibility, String Sound, boolean AdminOnly) {

        String forwardstr = p.getName() + ", " + getPlayerWithGrade + ", " + Title + ", " + Description +
                            ", " + showToast + ", " + announceChat + ", " + Frame + ", " + Visibility
                            + ", " + Sound + ", " + AdminOnly;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {

            out.writeUTF("Advancements"); //Écrit la donnée clé 'NickName'
            out.writeUTF(forwardstr); //Écrit le reste des données.

        } catch(IOException e) { e.printStackTrace(System.err); }

        server.sendData(ProxyMain.channel, bytes.toByteArray());
    }
     // Créer un 'achievement' customisé et l'envoie un serveur demandé //


    //*** FIN ACHIEVEMENTS ***\\
}
