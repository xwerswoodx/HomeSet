package net.xwerswoodx.homeset;

import java.io.File;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static Configuration config;
	private final static String file = "config/homeset.cfg";
	
	public static void init() {
		config = new Configuration(new File(file));
		try {
			config.load();
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static void deleteCategory(EntityPlayer player, String homeName) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.hasCategory(getConfigName(player, homeName)))
				config.removeCategory(new ConfigCategory(getConfigName(player, homeName)));
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static double getPosValue(EntityPlayer player, String homeName, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(getConfigName(player, homeName)).containsKey(key)) {
				double value = config.get(getConfigName(player, homeName), key, (double)1).getDouble();
				return value;				
			}
			return (double)0;
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return (double)0;
	}
	
	public static float getDirValue(EntityPlayer player, String homeName, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(getConfigName(player, homeName)).containsKey(key)) {
				float value = (float)config.get(getConfigName(player, homeName), key, (double)1).getDouble();
				return value;				
			}
			return (float)1;
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return (float)1;
	}
	
	public static World getWorldValue(EntityPlayer player, String homeName) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(getConfigName(player, homeName)).containsKey("W")) {
				int value = config.get(getConfigName(player, homeName), "W", 0).getInt();
				return DimensionManager.getWorld(value);
			}
			return DimensionManager.getWorld(0);
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return DimensionManager.getWorld(0);
	}
	
	public static int getDimensionValue(EntityPlayer player, String homeName) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(getConfigName(player, homeName)).containsKey("W")) {
				int value = config.get(getConfigName(player, homeName), "W", 0).getInt();
				return value;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return 0;
	}
	
	public static void setPosValue(EntityPlayer player, String homeName,String key, double value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			double set = config.get(getConfigName(player, homeName), key, (double)1).getDouble();
			config.getCategory(getConfigName(player, homeName)).get(key).set(value);
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static void setDirValue(EntityPlayer player, String homeName,String key, double value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			double set = config.get(getConfigName(player, homeName), key, (double)1).getDouble();
			config.getCategory(getConfigName(player, homeName)).get(key).set(value);
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static void setWorldValue(EntityPlayer player, String homeName, int value) {
		config = new Configuration(new File(file));
		try {
			config.load();			
			int set = config.get(getConfigName(player, homeName), "W", 0).getInt();
			config.getCategory(getConfigName(player, homeName)).get("W").set(value);
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static String getConfigName(EntityPlayer player) {
//		return player.getEntityWorld().getSaveHandler().getWorldDirectory().toString().split("\\")[3].replace(" ", "_") + ":" + player.getUniqueID().toString();
//		return player.getUniqueID().toString();
		return player.getEntityWorld().getSaveHandler().getWorldDirectory().getName().toLowerCase() + ":" + player.getUniqueID().toString();
	}
	
	public static String getConfigName(EntityPlayer player, String homeName) {
		return getConfigName(player) + ":" + homeName;
	}
	
	public static void showHomes(EntityPlayer player) {
		config = new Configuration(new File(file));
		String homes = null;
		try {
			config.load();
			Set<String> names = config.getCategoryNames();
			for (String name : names) {
				if (name.startsWith(getConfigName(player) + ":")) {
					String home = name.split(":")[2];
					if (!home.equalsIgnoreCase("back"))
						homes = homes == null ? home : homes + ", " + home;
				}
			}
			if (homes != null)
				player.sendMessage(new TextComponentString("Your current homes: " + homes));
			else
				player.sendMessage(new TextComponentString("You don't have any home."));
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
	}
	
	public static boolean hasSpecificHome(EntityPlayer player, String home) {
		config = new Configuration(new File(file));
		try {
			config.load();
			Set<String> names = config.getCategoryNames();
			if (names.size() > 0) {
				for (String name : names) {
					if (name.startsWith(getConfigName(player) + ":")) {
						if (name.split(":")[2].equalsIgnoreCase(home))
							return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return false;
	}

	public static boolean hasHome(EntityPlayer player) {
		config = new Configuration(new File(file));
		try {
			config.load();
			Set<String> names = config.getCategoryNames();
			if (names.size() > 0) {
				for (String name : names) {
					if (name.startsWith(getConfigName(player) + ":"))
						return true;
				}
			}
		} catch (Exception e) {
			System.out.println("Cannot load configuration file!");
		} finally {
			config.save();
		}
		return false;
	}
	
	public static void delHome(EntityPlayer player, String homeName, boolean giveInfo) {
		if (hasSpecificHome(player, homeName)) {
			deleteCategory(player, homeName);
			if (giveInfo)
				player.sendMessage(new TextComponentString("Home " + homeName + " removed."));
		}
	}

	public static void setHome(EntityPlayer player, String homeName, boolean giveInfo) {
		int dimension = player.dimension;
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		double z = player.getPosition().getZ();
		setWorldValue(player, homeName, dimension);
		setPosValue(player, homeName, "X", x);
		setPosValue(player, homeName, "Y", y);
		setPosValue(player, homeName, "Z", z);
		//setDirValue(player, homeName, "D", (double)player.getRotationYawHead());
		
		if (giveInfo)
			player.sendMessage(new TextComponentString("Home " + homeName + " set on " + x + ", " + y + ", " + z + "."));
	}
	
}
