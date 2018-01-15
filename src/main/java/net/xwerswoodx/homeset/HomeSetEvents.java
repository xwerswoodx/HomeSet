package net.xwerswoodx.homeset;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HomeSetEvents {
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			ConfigHandler.setHome(player, "back", false);
		}
	}
}
