/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.config;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.common.io.ByteStreams;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ConfigFileManager {

    /* Récupère la class "Main" */
	private ProxyMain main;
	public ConfigFileManager(ProxyMain pluginMain) { this.main = pluginMain; }
    /* Récupère la class "Main" */
	
	
	/********************************************************/
	/* METHODES POUR CHARGER UN FICHIER CONFIG PERSONNALISER */ 
	/********************************************************/
	
	// Partie Rechargements des différents fichiers de configuration //
	public void LoadConfig() {
		
		final File UtilsDir = new File(main.getDataFolder() + "/utils/");
		
		//En-Tête des différents fichiers de configurations//

		String[] headermotd = {
		   "| === EvhoProxy Configuration === |",
		   " ",
		   "*** MOTD DU SERVEUR ***", 
		   " ",
		   "par TheSakyo",
		   " "
		   
		};

		String[] headerinfoServer = {
		   "| === EvhoProxy Configuration === |",
		   " ",
		   "*** INFORMATION DU SERVEUR ***",
		   " ",
		   "par TheSakyo",
		   " "

		};

		String[] headermaintenance = {
		   "| === EvhoProxy Configuration === |",
		   " ",
		   "*** MAINTENANCE DU SERVEUR ***",
		   " ",
		   "par TheSakyo",
		   " "

		};

		//En-Tête des différents fichiers de configurations//
		
		
		// Création des dossiers //
		
	    if(!main.getDataFolder().exists()) { main.getDataFolder().mkdir(); }
	
		
		if(!UtilsDir.exists()) { 
			
			if(!UtilsDir.mkdirs()) { 
			
				try { UtilsDir.createNewFile(); }
				catch (IOException ignored) {}

			} 
		}
		
		// Création des dossiers //
	       
		
		/* Chargement/Création du fichier de configuration 'motd.yml' */
		if(getConfigFile("/config/motd.yml").exists()) {
			
			main.motdconfig = getNewConfig("/config/motd.yml", headermotd);
			main.motdconfig.reloadConfig();
		
		} else {
			
			main.motdconfig = getNewConfig("/config/motd.yml", headermotd);
			main.motdconfig.set("MOTD", "&7&oWelcome to Server !");
            main.motdconfig.set("MOTD_MAINTENANCE", "&cServer is in Maintenance !");
			main.motdconfig.saveConfig();
			
		}
		/* Chargement/Création du fichier de configuration 'motd.yml' */


        /* Chargement/Création du fichier de configuration 'info_server.yml' */
		if(getConfigFile("/config/info_server.yml").exists()) {

			main.infoServerconfig = getNewConfig("/config/info_server.yml", headerinfoServer);
			main.infoServerconfig.reloadConfig();

		} else {

			main.infoServerconfig = getNewConfig("/config/info_server.yml", headerinfoServer);
			main.infoServerconfig.set("IP", "play.example.com");
            main.infoServerconfig.set("DISCORD", "https://discord.com/invite/minecraft");
            main.infoServerconfig.set("WEBSITE", "https://www.minecraft.net");
			main.infoServerconfig.saveConfig();

		}
		/* Chargement/Création du fichier de configuration 'info_server.yml' */


        /* Chargement/Création du fichier de configuration 'maintenance.yml' */
		if(getConfigFile("/config/maintenance.yml").exists()) {

			main.maintenanceconfig = getNewConfig("/config/maintenance.yml", headermaintenance);
			main.maintenanceconfig.reloadConfig();

		} else {

			main.maintenanceconfig = getNewConfig("/config/maintenance.yml", headermaintenance);
            main.maintenanceconfig.set("maintenance_mode", null);
            main.maintenanceconfig.set("whitelist", new ArrayList<String>());
			main.maintenanceconfig.saveConfig();

		}
		/* Chargement/Création du fichier de configuration 'maintenance.yml' */

		
		/* Chargement/Création du fichier de configuration 'players-nickname.yml' */
        main.nickConfigFile = new File(UtilsDir, "players-nickname.yml");
        LoadAndSaveNickNamePlayerConfig();
		/* Chargement/Création du fichier de configuration 'players-nickname.yml' */



		/* Chargement/Création du fichier de configuration 'players-skinname.yml' */
        main.skinConfigFile = new File(UtilsDir, "players-skinname.yml");
        LoadAndSaveSkinNamePlayerConfig();
		/* Chargement/Création du fichier de configuration 'players-skinname.yml' */
	
	}
	// Partie Rechargements des différents fichiers de configuration //
	
	
	//Création du fichier de configuration 'players-nickname.yml' //
	private void CreateNickNamePlayerConfig() {
        
        if(!main.nickConfigFile.exists()) {
        	
            try { main.nickConfigFile.createNewFile(); } 

            catch(IOException e) { throw new RuntimeException("Impossible de créer le fichier de configuration", e); }
            
            
            try(InputStream is = main.getResourceAsStream(main.nickConfigFile.getAbsolutePath())) {
        		
               OutputStream os = new FileOutputStream(main.nickConfigFile);
               
               try { ByteStreams.copy(is, os); }
               catch(NullPointerException ignored) {}
            
            } catch(IOException e) { throw new RuntimeException("Impossible de créer le fichier de configuration", e); }
        }
     } 
	//Création du fichier de configuration 'players-nickname.yml' //


	//Création du fichier de configuration 'players-skinname.yml' //
	private void CreateSkinNamePlayerConfig() {

        if(!main.skinConfigFile.exists()) {

            try { main.skinConfigFile.createNewFile(); }

            catch(IOException e) { throw new RuntimeException("Impossible de créer le fichier de configuration", e); }


            try(InputStream is = main.getResourceAsStream(main.skinConfigFile.getAbsolutePath())) {

               OutputStream os = new FileOutputStream(main.skinConfigFile);

               try { ByteStreams.copy(is, os); }
               catch(NullPointerException ignored) {}

            } catch(IOException e) { throw new RuntimeException("Impossible de créer le fichier de configuration", e); }
        }
     }
	//Création du fichier de configuration 'players-skinname.yml' //
	
	
	//Rechargement du fichier de configuration 'players-nickname.yml' //
	public void LoadNickNamePlayerConfig() {
		
		try { 
	     
		 main.playerNickConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(main.nickConfigFile);
		
		} catch(IOException e) { CreateNickNamePlayerConfig(); LoadAndSaveNickNamePlayerConfig(); }
	}
	//Rechargement du fichier de configuration 'players-nickname.yml' //


	//Rechargement du fichier de configuration 'players-skinname.yml' //
	public void LoadSkinNamePlayerConfig() {

		try {

		 main.playerSkinConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(main.skinConfigFile);

		} catch(IOException e) { CreateSkinNamePlayerConfig(); LoadAndSaveSkinNamePlayerConfig(); }
	}
	//Rechargement du fichier de configuration 'players-skinname.yml' //
	
	
	//Sauvegarde du fichier de configuration 'players-nickname.yml' //
	public void SaveNickNamePlayerConfig() {
		
		try {
			
		  ConfigurationProvider.getProvider(YamlConfiguration.class).save(main.playerNickConfig, main.nickConfigFile);
		
		} catch(IOException e) { CreateNickNamePlayerConfig(); LoadAndSaveNickNamePlayerConfig(); }
	}
	//Sauvegarde du fichier de configuration 'players-nickname.yml' //


	//Sauvegarde du fichier de configuration 'players-skinname.yml' //
	public void SaveSkinNamePlayerConfig() {

		try {

		  ConfigurationProvider.getProvider(YamlConfiguration.class).save(main.playerSkinConfig, main.nickConfigFile);

		} catch(IOException e) { CreateNickNamePlayerConfig(); LoadAndSaveNickNamePlayerConfig(); }
	}
	//Sauvegarde du fichier de configuration 'players-skinname.yml' //
	
	
	//Rechargement/Sauvegarde du fichier de configuration 'players-nickname.yml' (méthode privé) //
	private void LoadAndSaveNickNamePlayerConfig() {
		
		LoadNickNamePlayerConfig();
		
		SaveNickNamePlayerConfig();
	}
	//Rechargement/Sauvegarde du fichier de configuration 'players-nickname.yml'(méthode privé) //


    // Rechargement/Sauvegarde du fichier de configuration 'players-skinname.yml' (méthode privé) //
	private void LoadAndSaveSkinNamePlayerConfig() {

		LoadSkinNamePlayerConfig();

		SaveSkinNamePlayerConfig();
	}
	//Rechargement/Sauvegarde du fichier de configuration 'players-skinname.yml'(méthode privé) //
	
	
	/********************************************************/
	/* METHODES POUR CHARGER UN FICHIER CONFIG PERSONNALISER */ 
	/********************************************************/




    /*
     * Obtient une nouvelle configuration avec l'en-tête
     * @param filePath - Chemin d'accés au fichier
     * @return - 'New ConfigFile'
     */
    public ConfigFile getNewConfig(String filePath, String[] header) {
 
        File file = this.getConfigFile(filePath);
 
        if(!file.exists()) {
            this.prepareFile(filePath);
 
            if(header != null && header.length != 0) {
                this.setHeader(file, header);
            }
 
        }
 
        ConfigFile config = new ConfigFile(this.getConfigContent(filePath), file, this.getCommentsNum(file), main);
        return config;
 
    }

    /*
     * Obtient une nouvelle configuration
     * @param filePath - Chemin d'accés au fichier
     * @return - 'New ConfigFile'
     */
    public ConfigFile getNewConfig(String filePath) {
        return this.getNewConfig(filePath, null);
    }


    /*
     * Obtient le fichier de configuration à partir de la chaîne
     * @param file - Chemin d'accés au fichier
     * @return - 'New file object'
     */
    public File getConfigFile(String file) {
 
        if(file.isEmpty() || file == null) {
            return null;
        }
 
        File configFile;
 
        if(file.contains("/")) {
 
            if(file.startsWith("/")) {
                configFile = new File(main.getDataFolder() + file.replace("/", File.separator));
            } else {
                configFile = new File(main.getDataFolder() + File.separator + file.replace("/", File.separator));
            }
 
        } else {
            configFile = new File(main.getDataFolder(), file);
        }
 
        return configFile;
 
    }


    /*
     * Lit le fichier et place les commentaires SnakeYAML
     * @param filePath - Chemin d'accés au fichier
     * @return - Fichier en tant que flux d'entrée
     */
    public Reader getConfigContent(File file) {
 
        if(!file.exists()) {
            return null;
        }
 
        try {
            int commentNum = 0;
 
            String addLine;
            String currentLine;
            String pluginName = this.getPluginName();
 
            StringBuilder whole = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
 
            while((currentLine = reader.readLine()) != null) {
 
                if(currentLine.startsWith("#")) {
                    addLine = currentLine.replaceFirst("#", pluginName + "_COMMENT_" + commentNum + ":");
                    whole.append(addLine + "\n");
                    commentNum++;
 
                } else {
                    whole.append(currentLine + "\n");
                }
 
            }
            
            Reader configStream = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF8"));
 
            reader.close();
            return configStream;
 
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
 
    }


    /*
     * Obtient le contenu de la configuration à partir du fichier
     * @param filePath - Chemin d'accés au fichier
     * @return - fichier prêt
     */
    public Reader getConfigContent(String filePath) {
        return this.getConfigContent(this.getConfigFile(filePath));
    }

    /*
     * Créé un nouveau fichier pour la configuration et y copier la ressource
     * @param file - Chemin d'accés au fichier
     * @param resource - Ressource à copier
     */
    public void prepareFile(String filePath, String resource) {
 
        File file = this.getConfigFile(filePath);
 
        if(file.exists()) {
            return;
        }
 
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
 
            if(resource != null && !resource.isEmpty()) {
                this.copyResource(main.getResourceAsStream(resource), file);
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    /*
     * Créé un nouveau fichier pour la configuration sans ressource
     * @param file - Fichier à créer
     */
    public void prepareFile(String filePath) {
        this.prepareFile(filePath, null);
    }

    /*
     * Ajoute un bloc d'en-tête à la configuration
     * @param file - Fichier Config
     * @param header - Lignes d'en-tête
     */
    public void setHeader(File file, String[] header) {
 
        if(!file.exists()) {
            return;
        }
 
        try {
            String currentLine;
            StringBuilder config = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
 
            while((currentLine = reader.readLine()) != null) {
                config.append(currentLine + "\n");
            }
 
            reader.close();
            config.append("# +----------------------------------------------------+ #\n");
 
            for(String line : header) {
 
                if(line.length() > 50) {
                    continue;
                }
 
                int lenght = (50 - line.length()) / 2;
                StringBuilder finalLine = new StringBuilder(line);
 
                for(int i = 0; i < lenght; i++) {
                    finalLine.append(" ");
                    finalLine.reverse();
                    finalLine.append(" ");
                    finalLine.reverse();
                }
 
                if(line.length() % 2 != 0) {
                    finalLine.append(" ");
                }
 
                config.append("# < " + finalLine.toString() + " > #\n");
 
        }
 
        config.append("# +----------------------------------------------------+ #");
 
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(this.prepareConfigString(config.toString()));
        writer.flush();
        writer.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
    /*
    * Obtient des commentaires du fichier
    * @param file - Fichier
    * @return - Nombre de commentaires
    */
    private int getCommentsNum(File file) {
 
        if(!file.exists()) {
            return 0;
        }
 
        try {
            int comments = 0;
            String currentLine;
 
            BufferedReader reader = new BufferedReader(new FileReader(file));
 
            while((currentLine = reader.readLine()) != null) {
 
                if(currentLine.startsWith("#")) {
                    comments++;
                }
 
            }
 
        reader.close();
        return comments;
 
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
 
    }
 
    private String prepareConfigString(String configString) {
 
        int lastLine = 0;
        int headerLine = 0;
 
        String[] lines = configString.split("\n");
        StringBuilder config = new StringBuilder();
 
        for(String line : lines) {
 
            if(line.startsWith(this.getPluginName() + "_COMMENT_")) {
                String comment = "#" + line.trim().substring(line.indexOf(":") + 1);
 
                if(comment.startsWith("# +-")) {

                    /*
                     * Si la ligne d'en-tête = 0, alors c'est
                     * début de l'en-tête, s'il est égal
                     * à 1 c'est la fin de l'en-tête
                     */
 
                    if(headerLine == 0) {
                        config.append(comment + "\n");
 
                        lastLine = 0;
                        headerLine = 1;
 
                    } else if(headerLine == 1) {
                        config.append(comment + "\n\n");
 
                        lastLine = 0;
                        headerLine = 0;
 
                    }
 
                } else {

                    /*
                     * Dernière ligne = 0 - Commentaire
                     * Dernière ligne = 1 - Cheminement normal
                     */
 
                    String normalComment;
 
                    if(comment.startsWith("# ' ")) {
                        normalComment = comment.substring(0, comment.length() - 1).replaceFirst("# ' ", "# ");
                    } else {
                        normalComment = comment;
                    }
 
                    if(lastLine == 0) {
                        config.append(normalComment + "\n");
                    } else if(lastLine == 1) {
                        config.append("\n" + normalComment + "\n");
                    }
 
                    lastLine = 0;
 
                }
 
            } else {
                config.append(line + "\n");
                lastLine = 1;
            }
 
        }
 
   return config.toString();
 
    }

    /*
     * Enregistre la configuration dans un fichier
     * @param configString - Chaîne de configuration
     * @param file - Fichier Config
     */
    public void saveConfig(String configString, File filePath, Configuration file) {
    	
        String configuration = this.prepareConfigString(configString);
        
        try {
        	
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            
            writer.write(configuration);
            writer.flush();
            
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(file, writer);
            
            writer.close();

        } catch (IOException e) { e.printStackTrace(); }
    }
 
        
 
    public String getPluginName() { return main.getDescription().getName(); }

    /*
     * Copie la ressource du flux d'entrée dans le fichier
     * @param resource - Ressource de .jar
     * @param file - Fichier à écrire
     */
    private void copyResource(InputStream resource, File file) {
 
        try {
            OutputStream out = new FileOutputStream(file);
 
            int lenght;
            byte[] buf = new byte[1024];
 
            while((lenght = resource.read(buf)) > 0){
                out.write(buf, 0, lenght);
            }
 
            out.close();
            resource.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
}