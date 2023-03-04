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

public class ChangeSkin extends Command {

	  /* Récupère la class "Main" et créer la commande et sa permission */
	  private final ProxyMain main;
	  public ChangeSkin(ProxyMain pluginMain) {

		super("changeskin", "EvhoProxy.changeskin", "skin");
		this.main = pluginMain;
	  }
	  /* Récupère la class "Main" et créer la commande et sa permission */

	 // Erreur de Commande
	 BaseComponent Type = new TextComponent(ProxyMain.getInstance().prefix + ChatColor.RED + "Essayez /nickame|nick <newName> ou <player> [newName] ou alors <reset>");

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
		  }

		  if(args.length == 1) {

			  if(args[0].equalsIgnoreCase("reset")) {

				  resetSkin(sender, args);
				  return;
			  }

		  } else if(args.length >= 2) {

			  if(args[1].equalsIgnoreCase("reset")) {

				  resetSkin(sender, args);
				  return;
			  }
		  }

		  SkinChange(sender, args);
	 }

	/********************************************************/
	/* PARTIE COMMANDE POUR CUSTOMISÉ LE PSEUDO D'UN JOUEUR */
	/********************************************************/


	//Petite Méthode pour changer le Skin du Joueur //
	public void SkinChange(CommandSender commandSender, String[] strings) {

		BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /changeskin|skin <skinName> ou /changeskin|skin <player> [skinName] ou alors /changeskin|skin <reset>");
		BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");

		ProxiedPlayer player;

		/***************************************/

		if(commandSender instanceof ProxiedPlayer) player = (ProxiedPlayer)commandSender;
		else {

			commandSender.sendMessage(error);
			return;
		}

		/***************************************/

		if(strings.length == 0) player.sendMessage(Type);
		else {

			if(strings.length == 1) {

					String string = main.methodCustom.format(strings[0]);

					if(main.playerSkinName.containsKey(player) && main.playerSkinName.get(player).equalsIgnoreCase(string)) {

						BaseComponent alreadySkin = new TextComponent(main.prefix + ChatColor.RED + "Votre Skin est déja celui que vous voulez définir !");

						player.sendMessage(alreadySkin);
						return;
					}


					if(ChatColor.stripColor(string).length() <= 16) {

						PlayerChanger.Change_SkinName(player.getServer().getInfo(), player, string);

						BaseComponent skinChanged = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez changer votre Skin : " + ChatColor.GOLD + string);
						player.sendMessage(skinChanged);

					} else {

						BaseComponent newSkin = new TextComponent(main.prefix + ChatColor.RED + "Le nom du nouveau Skin que vous avez définit contient " + ChatColor.GOLD + ChatColor.stripColor(string).length() + ChatColor.RED + " caractères");
						BaseComponent lengthMax = new TextComponent(main.prefix + ChatColor.RED + "Mais il doit être inférieur ou égal à " + ChatColor.GOLD + 16 + ChatColor.RED + " caractères");

						player.sendMessage(newSkin);
						player.sendMessage(lengthMax);
					}

			} else if(strings.length == 2) {

				if (!player.hasPermission("EvhoProxy.changeskin.other")) {

				   BaseComponent incorrectPerm = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission requise");
				   player.sendMessage(incorrectPerm);

				} else {

					if(ProxyServer.getInstance().getPlayer(strings[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
						String string = main.methodCustom.format(strings[1]);

						if(main.playerSkinName.containsKey(target) && main.playerSkinName.get(target).equalsIgnoreCase(string)) {

							BaseComponent alreadySkin = new TextComponent(main.prefix + ChatColor.RED + "Le Skin du Joueur est déja celui que vous voulez définir !");

							player.sendMessage(alreadySkin);
							return;
						}


						if(ChatColor.stripColor(string).length() <= 16) {

						   PlayerChanger.Change_SkinName(player.getServer().getInfo(), target, string);

						   BaseComponent skinChanged = new TextComponent(main.prefix + ChatColor.YELLOW + commandSender.getName() + ChatColor.GREEN + " a changer votre Skin : " + ChatColor.GOLD + string);
						   BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez changer le Skin de " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " : " + ChatColor.GOLD + string);

						   player.sendMessage(success);
						   target.sendMessage(skinChanged);

						} else {

							BaseComponent newSkin = new TextComponent(main.prefix + ChatColor.RED + "Le nom du nouveau Skin que vous avez définit contient " + ChatColor.GOLD + ChatColor.stripColor(string).length() + ChatColor.RED + " caractères");
							BaseComponent lengthMax = new TextComponent(main.prefix + ChatColor.RED + "Mais il doit être inférieur ou égal à " + ChatColor.GOLD + 16 + ChatColor.RED + " caractères");

							player.sendMessage(newSkin);
							player.sendMessage(lengthMax);
						}

					} else {

					  BaseComponent Playernotfound = new TextComponent(main.prefix + ChatColor.RED + "Le Joueur est introuvable !");
					  player.sendMessage(Playernotfound);
					}
				}

			} else player.sendMessage(Type);
		}
	}
	//Petite Méthode pour changer le Skin du Joueur //



	//Petite Méthode pour remettre le Skin d'origine du Joueur //
	public void resetSkin(CommandSender commandSender, String[] strings) {

		BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous devez être en jeux !");
		ProxiedPlayer player;

		/***************************************/

		if(commandSender instanceof ProxiedPlayer) player = (ProxiedPlayer)commandSender;
		else {

			commandSender.sendMessage(error);
			return;
		}

		/***************************************/

		if(strings.length == 0) player.sendMessage(Type);
		else {

			if(strings.length == 1) {

				if(main.playerSkinName.containsKey(player) && main.playerSkinName.get(player).equalsIgnoreCase(player.getName())) {

					BaseComponent alreadyReset = new TextComponent(main.prefix + ChatColor.RED + "Votre Skin est déja par défaut");
					player.sendMessage(alreadyReset);

				} else {

					PlayerChanger.Change_SkinName(player.getServer().getInfo(), player, player.getName());

					BaseComponent skinReset = new TextComponent(main.prefix + ChatColor.GREEN + "Votre Skin a été définit par défaut");

					player.sendMessage(skinReset);
				}

			} else if(strings.length == 2) {

				if(!player.hasPermission("EvhoProxy.changeskin.other")) {

					BaseComponent incorrectPerm = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission requise");
					player.sendMessage(incorrectPerm);

				} else {

					if(ProxyServer.getInstance().getPlayer(strings[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);

						if(main.playerSkinName.containsKey(target) && main.playerSkinName.get(target).equalsIgnoreCase(target.getName())) {

							BaseComponent alreadyReset = new TextComponent(main.prefix + ChatColor.RED + "Le Skin du Joueur est déja par défaut");
							player.sendMessage(alreadyReset);

						} else {

							PlayerChanger.Change_SkinName(player.getServer().getInfo(), target, target.getName());

							BaseComponent skinChanged = new TextComponent(main.prefix + ChatColor.YELLOW + commandSender.getName() + ChatColor.GREEN + " a définit votre Skin par défaut");
							BaseComponent success = new TextComponent(main.prefix + ChatColor.GREEN + "Vous avez définit le Skin de " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " par défaut");

							player.sendMessage(success);
							target.sendMessage(skinChanged);
						}

					} else {

						BaseComponent playerNotfound = new TextComponent(main.prefix + ChatColor.RED + "Le Joueur est introuvable !");
						player.sendMessage(playerNotfound);
					}
				}

			} else player.sendMessage(Type);

		}
	}
	//Petite Méthode pour remettre le Skin d'origine du Joueur //
}
