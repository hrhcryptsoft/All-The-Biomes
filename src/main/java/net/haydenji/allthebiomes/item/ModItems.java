package net.haydenji.allthebiomes.item;

import net.haydenji.allthebiomes.AllTheBiomes;
import net.haydenji.allthebiomes.item.custom.Hammer;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TridentItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AllTheBiomes.MOD_ID);

public static final RegistryObject<Item> TWIG = ITEMS.register(
        "twig",()->new Item(new Item.Properties().setId(ITEMS.key("twig")))
);
    public static final RegistryObject<Item> BARRIER = ITEMS.register(
            "barrier",()->new Item(new Item.Properties().setId(ITEMS.key("barrier")))
    );

    public static final RegistryObject<Item> COOKED_LAPIS = ITEMS.register(
            "cooked_lapis",()->new Item(new Item.Properties().setId(ITEMS.key("cooked_lapis")))
    );

    public static final RegistryObject<Item> HAMMER = ITEMS.register(
            "hammer",()->new Hammer(new Item.Properties().setId(ITEMS.key("hammer"))
                    .attributes(TridentItem.createAttributes())
                    .rarity(Rarity.EPIC)
    ));

    public static void register(IEventBus eventBus){
    ITEMS.register(eventBus);
}

}
