package de.vepexlegit.serverlistingame;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
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
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    public static Minecraft mc = Minecraft.getMinecraft();
    public static int i = -16;

    @SubscribeEvent
    public void onGuiScreenInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen screen = event.gui;
        if (screen instanceof GuiIngameMenu) {
            int x = screen.width / 2 - 100;
            int y = screen.height / 4 + 72 + i;
            event.buttonList.add(new GuiButton(100, x, y, "ServerList"));
        }
    }

    @SubscribeEvent
    public void onGuiInitPost(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen screen = event.gui;
        if (screen instanceof GuiIngameMenu) {
            event.buttonList.removeIf(guiButton -> guiButton.id == 7);
        }
    }

    @SubscribeEvent
    public void onGuiScreenActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        GuiScreen screen = event.gui;
        if (screen instanceof GuiIngameMenu && event.button.id == 100) {
            mc.displayGuiScreen(new GuiMultiplayer(screen));
        }
    }
}
