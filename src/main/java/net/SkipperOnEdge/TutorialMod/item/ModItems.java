package net.SkipperOnEdge.TutorialMod.item;

import net.SkipperOnEdge.TutorialMod.TutorialMod;
import net.SkipperOnEdge.TutorialMod.item.custom.ChiselItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);
    // A deferred register is a list given to minecraft at the time it is needed
    // This will be a list of items in our mod.

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(128)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
