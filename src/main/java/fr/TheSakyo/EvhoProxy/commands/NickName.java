/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import fr.TheSakyo.EvhoProxy.utils.PluginMessageClass.PlayerChanger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class NickName extends Command {

	  /* Récupère la class "Main" et créer la commande et sa permission */
	  private final ProxyMain main;
	  public NickName(ProxyMain pluginMain) {

		super("nickname", "EvhoProxy.nickname", "nick");
		this.main = pluginMain;
	  }
	  /* Récupère la class "Main" et créer la commande et sa permission */


	 // Erreur de Commande
	 BaseComponent Type = new TextComponent(ProxyMain.getInstance().prefix + ChatColor.RED + "Essayez /nickame|nick <newName> ou /nickame|nick <player> <newName> ou alors /nickame|nick <reset>");

	 String GI_COLOR = ChatColor.GRAY.toString() + ChatColor.ITALIC.toString(); // Code Couleur "GRIS + ITALIC"


	/********************************************************/
	/* PARTIE COMMANDE POUR CUSTOMISÉ LE PSEUDO D'UN JOUEUR */
	/********************************************************/

	  public void execute(CommandSender sender, String[] args) {

		  if((sender instanceof ProxiedPlayer p)) {

			  if(!super.hasPermission(p)) {

				  BaseComponent incorrectPerm = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
				  p.sendMessage(incorrectPerm);
				  return;
			  }

			  BaseComponent comingSoon = new TextComponent(main.prefix + ChatColor.RED + "La commande n'est pas encore disponible !");
			  p.sendMessage(comingSoon);
		  }

		  /**
		  if(args.length == 1) {

			  if(args[0].equalsIgnoreCase("reset")) {

				  resetName(sender, args);
				  return;
			  }

		  } else if(args.length >= 2) {

			  if(args[1].equalsIgnoreCase("reset")) {

				  resetName(sender, args);
				  return;
			  }
		  }

		  ChangeName(sender, args);
		   **/
	 }

	/********************************************************/
	/* PARTIE COMMANDE POUR CUSTOMISÉ LE PSEUDO D'UN JOUEUR */
	/********************************************************/


	// Petite Méthode pour changer le Pseudo du Joueur //
	public void ChangeName(CommandSender commandSender, String[] strings) {

		BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");
		ProxiedPlayer player;

		if(commandSender instanceof ProxiedPlayer) { player = (ProxiedPlayer)commandSender; }
		else {

			commandSender.sendMessage(error);
			return;
		}

		if(strings.length == 0) player.sendMessage(Type);
		else {

			if(strings.length == 1) {

					String string = main.methodCustom.format(strings[0]);

					if(main.playerSkinName.containsKey(player) && main.playerSkinName.get(player).equalsIgnoreCase(string)) {

						BaseComponent alreadyName = new TextComponent(main.prefix + ChatColor.RED + "Votre Pseudo est déja celui que vous voulez définir !");

						player.sendMessage(alreadyName);
						return;
					}

					if(ChatColor.stripColor(string).length() <= 16) {

						PlayerChanger.Change_CustomName(player.getServer().getInfo(), player, string);

						BaseComponent nameChanged = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez changer votre Pseudo en : " + ChatColor.GOLD + string);
						BaseComponent info = new TextComponent(main.prefix + GI_COLOR + "Cependant : Le Pseudo au-dessus de votre Tête ne prendra pas l'Effet des Codes Couleurs, s'il y en a !");

						player.sendMessage(nameChanged);
						player.sendMessage(info);

					} else {

						BaseComponent newName = new TextComponent(main.prefix + ChatColor.RED + "Le nouveau Pseudo que vous avez définit contient " + ChatColor.GOLD + ChatColor.stripColor(string).length() + ChatColor.RED + " caractères");
						BaseComponent lengthMax = new TextComponent(main.prefix + ChatColor.RED + "Mais il doit être inférieur ou égal à " + ChatColor.GOLD + 16 + ChatColor.RED + " caractères");

						player.sendMessage(newName);
						player.sendMessage(lengthMax);
					}

			} else if(strings.length == 2) {

				if (!player.hasPermission("EvhoProxy.nickname.other")) {

				   BaseComponent incorrectPerm = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission requise");
				   player.sendMessage(incorrectPerm);

				} else {

					if(ProxyServer.getInstance().getPlayer(strings[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
						String string = main.methodCustom.format(strings[1]);


						if(main.playerCustomName.containsKey(target) && main.playerCustomName.get(target).equalsIgnoreCase(string)) {

							BaseComponent alreadyName = new TextComponent(main.prefix + ChatColor.RED + "Le Pseudo du Joueur est déja celui que vous voulez définir !");

							player.sendMessage(alreadyName);
							return;
						}


						if(ChatColor.stripColor(string).length() <= 16) {

						   PlayerChanger.Change_CustomName(player.getServer().getInfo(), target, string);

						   BaseComponent nameChanged = new TextComponent(main.prefix + ChatColor.YELLOW + commandSender.getName() + ChatColor.GREEN + " a changer votre Pseudo en : " + ChatColor.GOLD + string);
						   BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez changer le Pseudo de " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " en : " + ChatColor.GOLD + string);

						   BaseComponent infoTarget = new TextComponent(main.prefix + GI_COLOR + "Cependant : Le Pseudo au-dessus de votre Tête ne prendra pas l'Effet des Codes Couleurs, s'il y'en a !");
						   BaseComponent infoSender = new TextComponent(main.prefix + GI_COLOR + "Cependant : Le Pseudo au-dessus de la Tête de " + ChatColor.YELLOW + target.getName() + GI_COLOR + " ne prendra pas l'Effet des Codes Couleurs, s'il y'en a !");

						   player.sendMessage(success);
						   player.sendMessage(infoSender);

						   target.sendMessage(nameChanged);
						   target.sendMessage(infoTarget);

						} else {

							BaseComponent newName = new TextComponent(main.prefix + ChatColor.RED + "Le nouveau Pseudo que vous avez définit contient " + ChatColor.GOLD + ChatColor.stripColor(string).length() + ChatColor.RED + " caractères");
							BaseComponent lengthMax = new TextComponent(main.prefix + ChatColor.RED + "Mais il doit être inférieur ou égal à " + ChatColor.GOLD + 16 + ChatColor.RED + " caractères");

							player.sendMessage(newName);
							player.sendMessage(lengthMax);
						}

					} else {

					  BaseComponent playerNotfound = new TextComponent(main.prefix + ChatColor.RED + "Le Joueur est introuvable !");
					  player.sendMessage(playerNotfound);
					}
				}

			} else { player.sendMessage(Type); }
		}
	}
	// Petite Méthode pour changer le Pseudo du Joueur //



	// Petite Méthode pour remettre le Pseudo d'origine du Joueur //
	public void resetName(CommandSender commandSender, String[] strings) {

		BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");

		ProxiedPlayer player = null;


		if(commandSender instanceof ProxiedPlayer) { player = (ProxiedPlayer)commandSender; }
		else commandSender.sendMessage(error);

		if(strings.length == 0) player.sendMessage(Type);
		else {

			if(strings.length == 1) {

				if(main.playerCustomName.containsKey(player) && main.playerCustomName.get(player).equalsIgnoreCase(player.getName())) {

					BaseComponent alreadyReset = new TextComponent(main.prefix + ChatColor.RED + "Votre Pseudo est déja par défaut");
					player.sendMessage(alreadyReset);

				} else {

					PlayerChanger.Change_CustomName(player.getServer().getInfo(), player, player.getName());

					BaseComponent nameReset = new TextComponent(main.prefix + ChatColor.GREEN + "Votre Pseudo a été définit par défaut");
					player.sendMessage(nameReset);
				}

			} else if(strings.length == 2) {

				if(!player.hasPermission("EvhoProxy.nickname.other")) {

					BaseComponent incorrectPerm = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission requise");
					player.sendMessage(incorrectPerm);

				} else {

					if(ProxyServer.getInstance().getPlayer(strings[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);

						if(main.playerCustomName.containsKey(target) && main.playerCustomName.get(target).equalsIgnoreCase(target.getName())) {

							BaseComponent alreadyReset = new TextComponent(main.prefix + ChatColor.RED + "Le Pseudo du Joueur est déja par défaut");
							player.sendMessage(alreadyReset);

						} else {

							PlayerChanger.Change_CustomName(player.getServer().getInfo(), target, target.getName());

							BaseComponent nameChanged = new TextComponent(main.prefix + ChatColor.YELLOW + commandSender.getName() + ChatColor.GREEN + " a définit votre Pseudo par défaut");
							BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez définit le Pseudo de " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " par défaut");

							player.sendMessage(success);
							target.sendMessage(nameChanged);
						}

					} else {

						BaseComponent playerNotfound = new TextComponent(main.prefix + ChatColor.RED + "Le Joueur est introuvable !");
						player.sendMessage(playerNotfound);
					}
				}

			} else { player.sendMessage(Type); }

		}
	}
	// Petite Méthode pour remettre le Pseudo d'origine du Joueur //
}
