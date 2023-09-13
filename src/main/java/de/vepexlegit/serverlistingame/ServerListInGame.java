package de.vepexlegit.serverlistingame;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ServerListInGame.MODID, version = ServerListInGame.VERSION)
public class ServerListInGame {
    public static final String MODID = "serverlistingame";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void onGuiScreenInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiIngameMenu) {
            int x = event.gui.width / 2 - 99;
            int y = event.gui.height / 4 + 128;
            event.buttonList.add(new GuiButton(100, x, y, "ServerList"));
        }
    }

    @SubscribeEvent
    public void onGuiScreenActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.gui instanceof GuiIngameMenu && event.button.id == 100) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(event.gui));
        }
    }
}
