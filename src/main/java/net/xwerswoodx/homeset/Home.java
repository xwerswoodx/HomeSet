package net.xwerswoodx.homeset;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandTP;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Home implements ICommand {
	
	private final List alias;
	public Home() {
		alias = new ArrayList<>();
		alias.add("home");
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "home";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "home <name>";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.alias;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)sender;
			
			
			if (args.length > 0) {
				String homeName = args[0];

				if (!ConfigHandler.hasHome(player))
					return;
				else if (!ConfigHandler.hasSpecificHome(player, homeName))
					return;
				
				double x = ConfigHandler.getPosValue(player, homeName, "X");
				double y = ConfigHandler.getPosValue(player, homeName, "Y");
				double z = ConfigHandler.getPosValue(player, homeName, "Z");
				World world = ConfigHandler.getWorldValue(player, homeName);
				int dimension = ConfigHandler.getDimensionValue(player, homeName);
//				float dir = ConfigHandler.getDirValue(player, homeName, "D");
				
//				System.out.println(homeName + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z) + " " + world.toString() + " " + String.valueOf(dimension));
				
	//			player.dimension = ConfigHandler.getDimensionValue(player, homeName);
				ConfigHandler.setHome(player, "back", false);
				if (player.dimension != dimension)
					player.changeDimension(dimension);
	//			player.setWorld(world);
	//			player.posX = x;
	//			player.posY = y;
	//			player.posZ = z;
				player.sendMessage(new TextComponentString("You have teleported to " + x + ", " + y + ", " + z + "."));
//				player.setRotationYawHead(dir);
				player.setPositionAndUpdate(x, y, z);
	//			player.onUpdate();
			} else {
				ConfigHandler.showHomes(player);
			}
		}
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return sender instanceof EntityPlayer ? true : false;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
