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


    // Créer un 'achievement' customisé et l'envoit un serveur demandé //
    public static void create(ServerInfo server, final ProxiedPlayer p, boolean getPlayerWithGrade, String Title, String Description,
                              boolean showToast, boolean announceChat, String Frame, String Visiblity, String Sound, boolean AdminOnly) {

        String forwardstr = p.getName() + ", " + String.valueOf(getPlayerWithGrade) + ", " + Title + ", " + Description +
                            ", " + String.valueOf(showToast) + ", " + String.valueOf(announceChat) + ", " + Frame + ", " + Visiblity
                            + ", " + Sound + ", " + AdminOnly;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {

            out.writeUTF("Advancements"); //Écrit la donnée clé 'NickName'
            out.writeUTF(forwardstr); //Écrit le reste des données.

        } catch(IOException e) { e.printStackTrace(); }

        server.sendData(ProxyMain.channel, bytes.toByteArray());
    }
     // Créer un 'achievement' customisé et l'envoit un serveur demandé //


    //*** FIN ACHIEVEMENTS ***\\
}
