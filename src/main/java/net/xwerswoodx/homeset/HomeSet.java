package net.xwerswoodx.homeset;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = HomeSet.MODID, version = HomeSet.VERSION)
public class HomeSet
{
    public static final String MODID = "homeset";
    public static final String VERSION = "0.3";
    
    @EventHandler
    public void onInitilization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HomeSetEvents());
    }
    
    @EventHandler
    public void onStart(FMLServerStartingEvent event) {
    	event.registerServerCommand(new SetHome());
    	event.registerServerCommand(new DelHome());
    	event.registerServerCommand(new Home());
    	event.registerServerCommand(new Back());
    }
}
