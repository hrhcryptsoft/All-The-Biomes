package net.haydenji.allthebiomes.datagen;

import net.haydenji.allthebiomes.AllTheBiomes;
import net.haydenji.allthebiomes.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput Output, CompletableFuture<HolderLookup.Provider> Lookup, @Nullable ExistingFileHelper existingFileHelper){
        super(Output, Lookup, AllTheBiomes.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvieder){
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.COOKED_LAPIS_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.COOKED_LAPIS_BLOCK.get());
    }
}
