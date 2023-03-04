/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

package fr.TheSakyo.EvhoProxy.commands;

import java.util.HashMap;
import java.util.Map;

import fr.TheSakyo.EvhoProxy.ProxyMain;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/* PARTIE IMPORTATIONS + PACKAGE DE LA CLASS */

public class Vanish extends Command {
	
   /* R�cup�re la class "Main" et cr�er la commande */
   private ProxyMain main;
   public Vanish(ProxyMain pluginMain) {
     super("vanish", "EvhoProxy.vanish", "v");
     this.main = pluginMain;
   }
   /* R�cup�re la class "Main" et cr�er la commande */
   
   //Variable "Map" �yant les joueurs "Vanish"
   Map<ProxiedPlayer, Boolean> vanishP = new HashMap<ProxiedPlayer, Boolean>();
   //Variable "Map" �yant les joueurs "Vanish"


	/*******************************************/
	/* PARTIE COMMANDE POUR "VANISH" UN JOUEUR */
	/*******************************************/
	  
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		String vanishTitleString = ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "[" + ChatColor.RED.toString() + ChatColor.BOLD.toString() + "VANISH" + ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "]" + ChatColor.RESET + " ";

		if((sender instanceof ProxiedPlayer p)) {

			BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /vanish ou /v [<on/off>] [<player>] !");

			if(!super.hasPermission(p)) {

			   BaseComponent error = new TextComponent(main.prefix + ChatColor.RED + "Vous n'avez pas la permission !");
			   p.sendMessage(error);
			   return;
			 }


			if(args.length != 0) {

				if(args.length == 1) {

					if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

						BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GRAY + "Un Administrateur vient d'activé votre Vanish !");

						BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GRAY + "Un Administrateur vient de désactivé votre Vanish !");


						BaseComponent vanishOnSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish activé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");

						BaseComponent vanishOffSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish désactivé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");


						if(vanishedPlayer(target) == true) {

							p.sendMessage(vanishOffSuccess);
							target.sendMessage(vanishOff);

							return;

						}

						else if(vanishedPlayer(target) == false) {

							p.sendMessage(vanishOnSuccess);
							target.sendMessage(vanishOn);

							return;
						}

					 } else if(args[0].equalsIgnoreCase("on")) {

						BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GRAY + "Vous êtes en Vanish !");

						BaseComponent vanishOnAlready = new TextComponent(main.prefix + ChatColor.RED + "Vous êtes déjà en Vanish !");

						if(Playervanished(p) != true) {

							vanishedPlayer(p, true);

							p.sendMessage(vanishOn);

						} else { p.sendMessage(vanishOnAlready); }

						return;

					} else if(args[0].equalsIgnoreCase("off")) {

						BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GRAY + "Vous n'êtes plus en Vanish !");

						BaseComponent vanishOffAlready = new TextComponent(main.prefix + ChatColor.RED + "Vous n'êtes pas en Vanish !");

						if(Playervanished(p) != false) {

							vanishedPlayer(p, false);

							p.sendMessage(vanishOff);

						} else { p.sendMessage(vanishOffAlready); }

						return;

					} else {

						BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
						sender.sendMessage(notfound);
						return;
					 }

				} else if(args.length == 2) {

					if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

						BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GRAY + "Un Administrateur vient d'activé votre Vanish !");

						BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GRAY + "Un Administrateur vient de désactivé votre Vanish !");


						BaseComponent vanishOnSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish activé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");

						BaseComponent vanishOffSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish désactivé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");


						if(args[1].equalsIgnoreCase("on")) {

							BaseComponent vanishOnAlready = new TextComponent(main.prefix + ChatColor.RED + "Le joueur demandé déjà en Vanish !");

							if(Playervanished(target) != true) {

								vanishedPlayer(target, true);

								p.sendMessage(vanishOnSuccess);

								target.sendMessage(vanishOn);

							} else { p.sendMessage(vanishOnAlready); }

							return;

						} else if(args[1].equalsIgnoreCase("off")) {

							BaseComponent vanishOffAlready = new TextComponent(main.prefix + ChatColor.RED + "Le joueur demandé n'est pas en Vanish !");

							if(Playervanished(target) != false) {

								vanishedPlayer(target, false);

								p.sendMessage(vanishOffSuccess);

								target.sendMessage(vanishOff);

							} else { p.sendMessage(vanishOffAlready); }

							return;

						} else {

							BaseComponent argerror = new TextComponent(main.prefix + ChatColor.RED + "Utilisez comme deuxième argument : <on> ou <off> !");

						   p.sendMessage(argerror);
						   return;
						}

					 } else {

						BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
						sender.sendMessage(notfound);
						return;
					 }

				} else {

					p.sendMessage(Type);
					return;
				}
			}


			BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GRAY + "Vous êtes en Vanish !");

			BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GRAY + "Vous n'êtes plus en Vanish !");


			if(vanishedPlayer(p) == true) { p.sendMessage(vanishOff); return; }

			else if(vanishedPlayer(p) == false) { p.sendMessage(vanishOn); return; }
		}

		if((sender instanceof CommandSender)) {

			BaseComponent Type = new TextComponent(main.prefix + ChatColor.RED + "Essayez /vanish [<on/off>] <player> ou /v [<on/off>] <player> !");

			if(args.length == 1) {

				 if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);


					BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GOLD + "La Console" + ChatColor.GRAY + " vient d'activé votre Vanish !");

					BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GOLD + "La Console" + ChatColor.GRAY + " vient de désactivé votre Vanish !");


					BaseComponent vanishOnSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish activé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");

					BaseComponent vanishOffSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish désactivé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");


					if(vanishedPlayer(target) == true) {

						sender.sendMessage(vanishOffSuccess);
						target.sendMessage(vanishOff);

						return;

					}

					else if(vanishedPlayer(target) == false) {

						sender.sendMessage(vanishOnSuccess);
						target.sendMessage(vanishOn);

						return;
					}


				 } else {

					BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
					sender.sendMessage(notfound);
					return;
				 }

			} else if(args.length == 2) {

				if(ProxyServer.getInstance().getPlayer(args[0]) != null) {

					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

					BaseComponent vanishOn = new TextComponent(vanishTitleString + ChatColor.GOLD + "La Console" + ChatColor.GRAY + " vient d'activé votre Vanish !");

					BaseComponent vanishOff = new TextComponent(vanishTitleString + ChatColor.GOLD + "La Console" + ChatColor.GRAY + " vient de désactivé votre Vanish !");


					BaseComponent vanishOnSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish activé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");

					BaseComponent vanishOffSuccess = new TextComponent(vanishTitleString + ChatColor.GREEN + "Vanish désactivé pour " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " !");


					if(args[1].equalsIgnoreCase("on")) {

						BaseComponent vanishOnAlready = new TextComponent(main.prefix + ChatColor.RED + "Le joueur demandé déjà en Vanish !");

						if(Playervanished(target) != true) {

							vanishedPlayer(target, true);

							sender.sendMessage(vanishOnSuccess);

							target.sendMessage(vanishOn);

						} else { sender.sendMessage(vanishOnAlready); }

						return;

					} else if(args[1].equalsIgnoreCase("off")) {

						BaseComponent vanishOffAlready = new TextComponent(main.prefix + ChatColor.RED + "Le joueur demandé n'est pas en Vanish !");

						if(Playervanished(target) != false) {

							vanishedPlayer(target, false);

							sender.sendMessage(vanishOffSuccess);

							target.sendMessage(vanishOff);

						} else { sender.sendMessage(vanishOffAlready); }

						return;

					} else {

					   BaseComponent argerror = new TextComponent(main.prefix + ChatColor.RED + "Utilisez comme deuxième argument : <on> ou <off> !");

					   sender.sendMessage(argerror);
					   return;
					}

				} else {

					BaseComponent notfound = new TextComponent(main.prefix + ChatColor.RED + "Le joueur est introuvable !");
					sender.sendMessage(notfound);
					return;
				}

			} else {

				sender.sendMessage(Type);
				return;

			}
		}

	}

	/*******************************************/
	/* PARTIE COMMANDE POUR "VANISH" UN JOUEUR */
	/*******************************************/


	// Petite Méthode pour vérifier si un joueur est "Vanish" //
	private boolean Playervanished(ProxiedPlayer p) {

		if(!vanishP.containsKey(p)) vanishP.put(p, false);

		if(vanishP.get(p) == true) { return true; }

		else if(vanishP.get(p) == false) { return false; }

		return false;
	}
	// Petite Méthode pour vérifier si un joueur est "Vanish" //


	// Petite Méthode pour "Vanish" un joueur (avec param�tre boolean) //
	@SuppressWarnings("all")
	private boolean vanishedPlayer(ProxiedPlayer p, boolean bool) {

		if(bool == false) {

			ProxyServer.getInstance().getPluginManager().dispatchCommand(main.console, "lpb user " + p.getName() + " permission unset EvhoProxy.vanished");

			if(vanishP.containsKey(p)) vanishP.remove(p);

			vanishP.put(p, false);

			return false;

		} else if(bool == true) {

			ProxyServer.getInstance().getPluginManager().dispatchCommand(main.console, "lpb user " + p.getName() + " permission set EvhoProxy.vanished");

			if(vanishP.containsKey(p)) vanishP.remove(p);

			vanishP.put(p, true);

			return true;
		}
		return true;
	}
	// Petite Méthode pour "Vanish" un joueur (avec paramétre boolean) //



	// Petite Méthode pour "Vanish" un joueur //
	private boolean vanishedPlayer(ProxiedPlayer p) {

		User user = main.luckapi.getPlayerAdapter(ProxiedPlayer.class).getUser(p);


		if(main.methodcustom.hasLuckPermission(user, "EvhoProxy.vanished") == true) {

			ProxyServer.getInstance().getPluginManager().dispatchCommand(main.console, "lpb user " + p.getName() + " permission unset EvhoProxy.vanished");

			if(vanishP.containsKey(p)) vanishP.remove(p);

			vanishP.put(p, false);

			return true;

		} else if(main.methodcustom.hasLuckPermission(user, "EvhoProxy.vanished") == false) {

			ProxyServer.getInstance().getPluginManager().dispatchCommand(main.console, "lpb user " + p.getName() + " permission set EvhoProxy.vanished");

			if(vanishP.containsKey(p)) vanishP.remove(p);

			vanishP.put(p, true);

			return false;
		}
		return false;
	}
	// Petite Méthode pour "Vanish" un joueur //
}
