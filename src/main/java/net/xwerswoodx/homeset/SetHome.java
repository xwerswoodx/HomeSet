package net.xwerswoodx.homeset;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class SetHome implements ICommand {

	private final List alias;
	public SetHome() {
		alias = new ArrayList<>();
		alias.add("sethome");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "sethome";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "sethome <name>";
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
			String homeName = "home";
			if (args.length > 0)
				homeName = args[0];
			
			EntityPlayer player = (EntityPlayer)sender;
			/*
			int world = player.dimension;
			double x = sender.getPosition().getX();
			double y = sender.getPosition().getY();
			double z = sender.getPosition().getZ();
			ConfigHandler.setWorldValue(player, homeName, world);
			ConfigHandler.setPosValue(player, homeName, "X", x);
			ConfigHandler.setPosValue(player, homeName, "Y", y);
			ConfigHandler.setPosValue(player, homeName, "Z", z);
			sender.sendMessage(new TextComponentString("Home " + homeName + " set on " + x + ", " + y + ", " + z + "."));
			*/
			ConfigHandler.setHome(player, homeName, true);
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
