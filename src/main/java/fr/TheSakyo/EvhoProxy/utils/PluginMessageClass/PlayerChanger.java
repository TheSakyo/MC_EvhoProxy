/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.utils.PluginMessageClass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import io.netty.util.internal.StringUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class PlayerChanger {

    
    //*** DÉBUT NICKNAME ***\\


	// Récupère le Nom Customisé du Joueur //
    public static void Check_CustomName(ServerInfo server, final ProxiedPlayer p) {

        String nickName = (String)ProxyMain.getInstance().playerNickConfig.get("Nicknames." + p.getUniqueId().toString());

    	if(StringUtil.isNullOrEmpty(nickName)) { nickName = p.getName(); }
        PlayerChanger.Change_CustomName(server, p, nickName);
    }
    // Récupère le Nom Customisé du Joueur //
    
    
    // Permet de créer le Nom Customisé du Joueur //
    public static void Set_CustomName(final ProxiedPlayer p) {
    	
        String nickName = (String)ProxyMain.getInstance().playerNickConfig.get("Nicknames." + p.getUniqueId().toString());
        
        if(StringUtil.isNullOrEmpty(nickName)) { nickName = p.getName(); }

        nickName = ChatColor.translateAlternateColorCodes('&', nickName);
        ProxyMain.getInstance().playerCustomName.put(p, nickName);
    }
    // Permet de créer le Nom Customisé du Joueur //

    
    
    // Permet de créer le Nom Customisé du Joueur en récupérant le serveur du joueur (liaison avec EvhoProxy) //
    public static void Set_CustomNameServer(ServerInfo server, final ProxiedPlayer p) {
    	
        String nickName = (String)ProxyMain.getInstance().playerNickConfig.get("Nicknames." + p.getUniqueId().toString());
        
        if(StringUtil.isNullOrEmpty(nickName)) { nickName = p.getName(); }
        
        nickName = ChatColor.translateAlternateColorCodes('&', nickName);

        String forwardstr = p.getName() + ", " + nickName;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {

            out.writeUTF("Nickname"); //Écrit la donnée clé 'NickName'
            out.writeUTF(forwardstr); //Écrit le reste des données.
            
        } catch(IOException e) { e.printStackTrace(System.err); }

        server.sendData(ProxyMain.channel, bytes.toByteArray());
    }
    // Permet de créer le Nom Customisé du Joueur en récupérant le serveur du joueur (liaison avec EvhoProxy) //
    
    
    
    // Change et Sauvegarde le Nom Customisé du Joueur //
    public static void Change_CustomName(ServerInfo server, final ProxiedPlayer p, String s) {

        // ⬇️ Sauvegarde la configuration, du nom du skin ⬇️ //
        if(!s.equals(p.getName())) ProxyMain.getInstance().playerNickConfig.set("Nicknames." + p.getUniqueId().toString(), s);
        else ProxyMain.getInstance().playerNickConfig.set("Nicknames." + p.getUniqueId().toString(), null);
        // ⬆️ Sauvegarde la configuration, du nom du skin ⬆️ //

        /***********************************/

        ProxyMain.getInstance().config_manager.SaveNickNamePlayerConfig(); //Sauvegarde la configuration
        Set_CustomName(p);
        Set_CustomNameServer(server, p);
    }
    // Change et Sauvegarde le Nom Customisé du Joueur //


    /* ----------------------------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------------------------- */


	// Récupère le Nom du Skin Customisé du Joueur //
    public static void Check_SkinName(ServerInfo server, final ProxiedPlayer p) {

        String skinName = (String)ProxyMain.getInstance().playerSkinConfig.get("SkinNames." + p.getUniqueId().toString());

    	if(StringUtil.isNullOrEmpty(skinName)) { skinName = p.getName(); }
        PlayerChanger.Change_SkinName(server, p, skinName);
    }
    // Récupère le Nom du Skin Customisé du Joueur //


    // Permet de créer le Nom du Skin Customisé du Joueur //
    public static void Set_SkinName(final ProxiedPlayer p) {

        String skinName = (String)ProxyMain.getInstance().playerSkinConfig.get("SkinNames." + p.getUniqueId().toString());

        if(StringUtil.isNullOrEmpty(skinName)) { skinName = p.getName(); }

        skinName = ChatColor.translateAlternateColorCodes('&', skinName);
        ProxyMain.getInstance().playerSkinName.put(p, skinName);
    }
    // Permet de créer le Nom du Skin Customisé du Joueur //



    // Permet de créer le Nom du Skin Customisé du Joueur en récupérant le serveur du joueur (liaison avec EvhoProxy) //
    public static void Set_SkinNameServer(ServerInfo server, final ProxiedPlayer p) {

        String skinName = (String)ProxyMain.getInstance().playerSkinConfig.get("SkinNames." + p.getUniqueId().toString());

        if(StringUtil.isNullOrEmpty(skinName)) { skinName = p.getName(); }

        skinName = ChatColor.translateAlternateColorCodes('&', skinName);

        String forwardstr = p.getName() + ", " + skinName;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);

        try {

            out.writeUTF("SkinName"); //Écrit la donnée clé 'NickName'
            out.writeUTF(forwardstr); //Écrit le reste des données.

        } catch(IOException e) { e.printStackTrace(System.err); }

        server.sendData(ProxyMain.channel, bytes.toByteArray());
    }
    // Permet de créer le Nom du Skin Customisé du Joueur en récupérant le serveur du joueur (liaison avec EvhoProxy) //


    // Change et Sauvegarde le Nom Skin Customisé du Joueur //
    public static void Change_SkinName(ServerInfo server, final ProxiedPlayer p, String s) {

        // ⬇️ Sauvegarde la configuration, du nom du skin ⬇️ //
        if(!s.equals(p.getName())) ProxyMain.getInstance().playerSkinConfig.set("SkinNames." + p.getUniqueId().toString(), s);
        else ProxyMain.getInstance().playerSkinConfig.set("SkinNames." + p.getUniqueId().toString(), null);
        // ⬆️ Sauvegarde la configuration, du nom du skin ⬆️ //

        /***********************************/

        ProxyMain.getInstance().config_manager.SaveSkinNamePlayerConfig(); //Sauvegarde la configuration
        Set_SkinName(p);
        Set_SkinNameServer(server, p);
    }
    // Change et Sauvegarde le Nom Skin Customisé du Joueur //

    
    //*** FIN NICKNAME ***\\
}
