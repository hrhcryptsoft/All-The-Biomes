package net.haydenji.allthebiomes.datagen;

import net.haydenji.allthebiomes.block.ModBlocks;
import net.haydenji.allthebiomes.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistoies){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),pRegistoies);
    }
    @Override
    protected void generate(){
        dropSelf(ModBlocks.GHOSTBUSTERS_LOGO_BLOCK.get());
//        dropSelf(ModBlocks.COOKED_LAPIS_BLOCK.get());
        this.add(ModBlocks.COOKED_LAPIS_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.COOKED_LAPIS_BLOCK.get(), ModItems.COOKED_LAPIS.get(), 10000, 10000));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item pItem, float min, float max){

        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(pItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                )

        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}