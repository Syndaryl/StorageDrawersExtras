package com.jaquadro.minecraft.storagedrawersextra.core;

import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.api.storage.EnumBasicDrawer;
import com.jaquadro.minecraft.storagedrawers.config.ConfigManager;
import com.jaquadro.minecraft.storagedrawersextra.StorageDrawersExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.BlockTrimExtra;
import com.jaquadro.minecraft.storagedrawersextra.block.EnumMod;
import com.jaquadro.minecraft.storagedrawersextra.block.EnumVariant;
import com.jaquadro.minecraft.storagedrawersextra.config.ConfigManagerExt;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes
{
    public static ItemStack makeBasicDrawerItemStack (EnumBasicDrawer info, String material, int count) {
        ItemStack stack = new ItemStack(ModBlocks.extraDrawers, count, info.getMetadata());

        NBTTagCompound data = new NBTTagCompound();
        data.setString("material", material);
        stack.setTagCompound(data);

        return stack;
    }

    public void init () {
        ConfigManager config = StorageDrawers.config;
        ConfigManagerExt configExt = StorageDrawersExtra.config;

        for (EnumVariant variant : EnumVariant.values()) {
            if (variant == EnumVariant.DEFAULT)
                continue;

            EnumMod mod = variant.getMod();
            if (mod == null || !mod.isEnabled(configExt.getModToggleState(mod)))
                continue;

            ItemStack plankStack = null;
            if (variant.getPlankResource() != null) {
                Block block = Block.getBlockFromName(variant.getPlankResource().toString());
                if (block != null)
                    plankStack = new ItemStack(block, 1, variant.getPlankMeta());
            }

            ItemStack slabStack = null;
            if (variant.getSlabResource() != null) {
                Block block = Block.getBlockFromName(variant.getSlabResource().toString());
                if (block != null)
                    slabStack = new ItemStack(block, 1, variant.getSlabMeta());
            }

            String material = variant.getResource().toString();

            if (config.isBlockEnabled(EnumBasicDrawer.FULL1.getUnlocalizedName()) && plankStack != null) {
                ItemStack result = makeBasicDrawerItemStack(EnumBasicDrawer.FULL1, material, config.getBlockRecipeOutput(EnumBasicDrawer.FULL1.getUnlocalizedName()));
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "xxx", " y ", "xxx", 'x', plankStack, 'y', "chestWood"));
            }
            if (config.isBlockEnabled(EnumBasicDrawer.FULL2.getUnlocalizedName()) && plankStack != null) {
                ItemStack result = makeBasicDrawerItemStack(EnumBasicDrawer.FULL2, material, config.getBlockRecipeOutput(EnumBasicDrawer.FULL2.getUnlocalizedName()));
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "xyx", "xxx", "xyx", 'x', plankStack, 'y', "chestWood"));
            }
            if (config.isBlockEnabled(EnumBasicDrawer.FULL4.getUnlocalizedName()) && plankStack != null) {
                ItemStack result = makeBasicDrawerItemStack(EnumBasicDrawer.FULL4, material, config.getBlockRecipeOutput(EnumBasicDrawer.FULL4.getUnlocalizedName()));
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "yxy", "xxx", "yxy", 'x', plankStack, 'y', "chestWood"));
            }
            if (config.isBlockEnabled(EnumBasicDrawer.HALF2.getUnlocalizedName()) && slabStack != null) {
                ItemStack result = makeBasicDrawerItemStack(EnumBasicDrawer.HALF2, material, config.getBlockRecipeOutput(EnumBasicDrawer.HALF2.getUnlocalizedName()));
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "xyx", "xxx", "xyx", 'x', slabStack, 'y', "chestWood"));
            }
            if (config.isBlockEnabled(EnumBasicDrawer.HALF4.getUnlocalizedName()) && slabStack != null) {
                ItemStack result = makeBasicDrawerItemStack(EnumBasicDrawer.HALF4, material, config.getBlockRecipeOutput(EnumBasicDrawer.HALF4.getUnlocalizedName()));
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "yxy", "xxx", "yxy", 'x', slabStack, 'y', "chestWood"));
            }
            if (config.isBlockEnabled("trim") && plankStack != null) {
                ItemStack result = new ItemStack(ModBlocks.extraTrim[variant.getGroupIndex()], config.getBlockRecipeOutput("trim"), variant.getGroupMeta());
                GameRegistry.addRecipe(new ShapedOreRecipe(result, "xyx", "yyy", "xyx", 'x', "stickWood", 'y', plankStack));
            }
        }
    }
}
