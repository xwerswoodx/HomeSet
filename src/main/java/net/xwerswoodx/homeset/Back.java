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

public class Back implements ICommand {
	
	private final List alias;
	public Back() {
		alias = new ArrayList<>();
		alias.add("back");
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "back";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "back";
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
			if (!ConfigHandler.hasHome(player))
				return;
			else if (!ConfigHandler.hasSpecificHome(player, "back"))
				return;
				
			double x = ConfigHandler.getPosValue(player, "back", "X");
			double y = ConfigHandler.getPosValue(player, "back", "Y");
			double z = ConfigHandler.getPosValue(player, "back", "Z");
			World world = ConfigHandler.getWorldValue(player, "back");
			int dimension = ConfigHandler.getDimensionValue(player, "back");
//			float dir = ConfigHandler.getDirValue(player, "back", "D");

			ConfigHandler.setHome(player, "back", false);
			if (player.dimension != dimension)
				player.changeDimension(dimension);
			player.sendMessage(new TextComponentString("You have teleported to " + x + ", " + y + ", " + z + "."));
//			player.setRotationYawHead(dir);
			player.setPositionAndUpdate(x, y, z);
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
