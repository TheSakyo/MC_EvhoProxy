/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class ConfigFile {

    private int comments;

    private final ConfigFileManager manager;

    private final File file;

    private final Configuration config;

    public ConfigFile(Reader configStream, File configFile, int comments, ProxyMain pluginMain) {

    	//Class "Main"
        /* Récupère la class "Main" et les différentes options pour faire fonctionner le fichier de configuration */

        this.comments = comments;

        this.manager = new ConfigFileManager(pluginMain);

        this.file = configFile;

        this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configStream);

    }
    /* Récupère la class "Main" et les différentes options pour faire fonctionner le fichier de configuration */


    /***************************************************/
    /* PARTIE MANAGEMENT D'UN FICHIER DE CONFIGURATION */
    /***************************************************/

    public Object get(String path) {  return this.config.get(path); }

    public Object get(String path, Object def) { return this.config.get(path, def); }

    public String getString(String path) { return this.config.getString(path); }

    public String getString(String path, String def) { return this.config.getString(path, def); }

    public int getInt(String path) { return this.config.getInt(path); }

    public int getInt(String path, int def) { return this.config.getInt(path, def); }

    public boolean getBoolean(String path) { return this.config.getBoolean(path); }

    public boolean getBoolean(String path, boolean def) { return this.config.getBoolean(path, def); }


    public double getDouble(String path) { return this.config.getDouble(path); }

    public double getDouble(String path, double def) { return this.config.getDouble(path, def); }

    public List<?> getList(String path) { return this.config.getList(path); }

    public List<?> getList(String path, List<?> def) { return this.config.getList(path, def); }

    public boolean contains(String path) { return this.config.contains(path); }

    public void removeKey(String path) { this.config.set(path, null); }

    public void set(String path, Object value) { this.config.set(path, value); }

    public void set(String path, Object value, String comment) {

        if(!this.config.contains(path)) {

            this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comment);
            comments++;
        }

        this.config.set(path, value);
    }

    public void set(String path, Object value, String[] comment) {

        for(String comm : comment) {

            if(!this.config.contains(path)) {

                this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comm);
                comments++;
            }

        }

        /**************************************/

        this.config.set(path, value);

    }

    public void setHeader(String[] header) {

        manager.setHeader(this.file, header);
        this.comments = header.length + 2;
        this.reloadConfig();
    }

    public void reloadConfig() {

    	try { ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file, this.config); }
        catch (IOException e) { e.printStackTrace(System.err); }

    }

    public void saveConfig() {

    	String config = savetoString(this.file);
        manager.saveConfig(config, this.file, this.config);
    }

    private String savetoString(File file) {

        try {
            int commentNum = 0;

            String addLine;
            String currentLine;
            String pluginName = manager.getPluginName();

            StringBuilder whole = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((currentLine = reader.readLine()) != null) {

                if(currentLine.startsWith("#")) {

                    addLine = currentLine.replaceFirst("#", pluginName + "_COMMENT_" + commentNum + ":");
                    whole.append(addLine).append("\n");
                    commentNum++;

                } else whole.append(currentLine).append("\n");
            }

            String strings = whole.toString();

            reader.close();
            return strings;

        } catch(IOException e) {

            e.printStackTrace(System.err);
            return null;
        }
    }

    /***************************************************/
    /* PARTIE MANAGEMENT D'UN FICHIER DE CONFIGURATION */
    /***************************************************/
}

