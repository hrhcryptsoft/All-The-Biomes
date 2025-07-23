package net.haydenji.allthebiomes.block;

import net.haydenji.allthebiomes.AllTheBiomes;
import net.haydenji.allthebiomes.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AllTheBiomes.MOD_ID);


public static final RegistryObject<Block>GHOSTBUSTERS_LOGO_BLOCK = registerBlock("gb_logo_block",
        ()-> new Block(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AllTheBiomes.MOD_ID, "gb_logo_block")))
                .strength(-1f)
                .sound(SoundType.SOUL_SAND)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .explosionResistance(18000000)));

public static final RegistryObject<Block>COOKED_LAPIS_BLOCK = registerBlock("cooked_lapis_block",
        ()->new Block(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AllTheBiomes.MOD_ID, "cooked_lapis_block")))
                .strength(20f)
                .explosionResistance(1200)
                .sound(SoundType.ANCIENT_DEBRIS)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,()->new BlockItem(block.get(), new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AllTheBiomes.MOD_ID, name)))));
    }
    public static void register(IEventBus eventBus){BLOCKS.register(eventBus);}
}
