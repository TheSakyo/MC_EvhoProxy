/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */


package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class PluginsList extends Command {

     /* Récupère la class "Main" et créer la commande et sa permission */
	  private final ProxyMain main;
	  public PluginsList(ProxyMain pluginMain) {

		super("pluginslist", "EvhoProxy.pluginslist", "pllist");
		this.main = pluginMain;
	  }
	  /* Récupère la class "Main" et créer la commande et sa permission */


    /************************************************************/
	/* PARTIE COMMANDE POUR LISTER LES PLUGINS DU SERVEUR PROXY */
	/************************************************************/

	  public void execute(CommandSender sender, String[] args) {

	  	if((sender instanceof ProxiedPlayer p)) {

		   if(!super.hasPermission(p)) {

			   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
			   p.sendMessage(error);
			   return;
			 }
		}


	  	BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /pluginslist sans arguments");

		if(args.length != 0) {

		  sender.sendMessage(Type);
		  return;
		}

		  BaseComponent title = new TextComponent(main.prefix + ChatColor.GOLD + "Listes des Plugins du Serveur Proxy :");


		  try {

		  	List<String> plugins = findFiles(ProxyServer.getInstance().getPluginsFolder().toPath(), ".jar");

			sender.sendMessage(title);
			sender.sendMessage(new TextComponent(" "));

              for(String plugin : plugins) {

                  sender.sendMessage(new TextComponent(plugin));
                  sender.sendMessage(new TextComponent(" "));
              }

			BaseComponent end = new TextComponent(ChatColor.GOLD + "------------------------------------");
			sender.sendMessage(new TextComponent(end));

		  } catch(IOException e) {

		  	BaseComponent error = new TextComponent(ChatColor.RED + "Une erreur esr survenue ! Il est possible que vous n'ayez aucun plugins dans le Serveur Proxy !");
		  	sender.sendMessage(error);

		  	e.printStackTrace(System.err);
		  }
      }
      /************************************************************/
      /* PARTIE COMMANDE POUR LISTER LES PLUGINS DU SERVEUR PROXY */
	  /************************************************************/



      /* Petite Méthode pour détecter les fichiers ayant l'extension "..." */
      private static List<String> findFiles(Path path, String... fileExtensions) throws IOException {

      if (!Files.isDirectory(path)) {
          throw new IllegalArgumentException("Le chemin doit être un répertoire !");
      }

      List<String> result;
      try (Stream<Path> walk = Files.walk(path, 1)) {
          result = walk
                  .filter(p -> !Files.isDirectory(p))
                  // converti le chemin en chaîne de caractères
                  .map(p -> p.toString().toLowerCase())

                  // référence à la méthode
                  .filter(f -> Arrays.stream(fileExtensions).anyMatch(f::endsWith))
                  .collect(Collectors.toList());
      }
      return result;

  }
      /* Petite Méthode pour détecter les fichiers ayant l'extension "..." */
}
