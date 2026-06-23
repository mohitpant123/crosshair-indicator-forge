package com.crosshairindicator.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static final KeyBinding TOGGLE_KEY = new KeyBinding(
        "key.crosshairindicator.toggle",
        KeyConflictContext.IN_GAME,
        InputMappings.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        "key.categories.crosshairindicator"
    );
}
