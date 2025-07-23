package net.haydenji.allthebiomes.item;

import net.haydenji.allthebiomes.AllTheBiomes;
import net.haydenji.allthebiomes.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AllTheBiomes.MOD_ID);

    public static final RegistryObject<CreativeModeTab>MISCELLANEOUS_TAB=
            CREATIVE_MODE_TABS.register(
                    "miscellaneous_tab",
                    ()-> CreativeModeTab
                            .builder().icon(()-> new ItemStack(ModItems.BARRIER.get()))
                            .title(Component.translatable("creativetab.allthebiomes.miscellaneous"))
                            .displayItems((itemDisplayParameters,output)->{
                                output.accept(ModItems.BARRIER.get());
                                output.accept(ModItems.TWIG.get());
                                output.accept(ModItems.COOKED_LAPIS.get());
                                output.accept(ModItems.HAMMER.get());
                                output.accept(ModBlocks.COOKED_LAPIS_BLOCK.get());
                                output.accept(ModBlocks.GHOSTBUSTERS_LOGO_BLOCK.get());
                            }).build()
            );
    public static void register(IEventBus eventBus){CREATIVE_MODE_TABS.register(eventBus);}
}
