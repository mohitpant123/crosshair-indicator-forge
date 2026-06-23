package com.crosshairindicator;

import com.crosshairindicator.client.CrosshairEventHandler;
import com.crosshairindicator.client.KeyBindings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("crosshairindicator")
public class CrosshairIndicatorMod {

    public static final String MOD_ID = "crosshairindicator";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public CrosshairIndicatorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KeyBindings.TOGGLE_KEY);
        MinecraftForge.EVENT_BUS.register(new CrosshairEventHandler());
        LOGGER.info("Crosshair Indicator initialized. Press H to toggle.");
    }
}
