package net.dodogang.sizzle.data.recipes;

import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.common.block.SzBlocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class SizzleRecipeProvider extends RecipeProvider {
    private Consumer<IFinishedRecipe> consumer;

    public SizzleRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.consumer = consumer;

        generic3x3("blaze_rod_block_3x3", Items.BLAZE_ROD, SzBlocks.BLAZE_ROD_BLOCK, 1);
        shapeless("blaze_rod_from_block", SzBlocks.BLAZE_ROD_BLOCK, Items.BLAZE_ROD, 9);

        generic2x2("polished_pumice_2x2", SzBlocks.PUMICE, SzBlocks.POLISHED_PUMICE, 4);
        generic3x1("pumice_slab_3x1", SzBlocks.PUMICE, SzBlocks.PUMICE_SLAB, 6);
        stairs("pumice_stairs_stairs", SzBlocks.PUMICE, SzBlocks.PUMICE_STAIRS, 4);
        generic3x2("pumice_wall_3x2", SzBlocks.PUMICE, SzBlocks.PUMICE_WALL, 6);
        generic3x1("polished_pumice_slab_3x1", SzBlocks.POLISHED_PUMICE, SzBlocks.POLISHED_PUMICE_SLAB, 6);
        stairs("polished_pumice_stairs_stairs", SzBlocks.POLISHED_PUMICE, SzBlocks.POLISHED_PUMICE_STAIRS, 4);
        generic3x2("polished_pumice_wall_3x2", SzBlocks.POLISHED_PUMICE, SzBlocks.POLISHED_PUMICE_WALL, 6);
    }

    private void generic2x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("##")
                           .patternLine("##")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void generic3x3(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .patternLine("###")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void shapeless(String id, IItemProvider from, IItemProvider to, int count) {
        ShapelessRecipeBuilder.shapelessRecipe(to, count)
                              .addIngredient(from)
                              .addCriterion("has_ingredient", hasItem(from))
                              .build(consumer, Sizzle.resLoc(id));
    }

    private void fence(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .key('/', Items.STICK)
                           .patternLine("#/#")
                           .patternLine("#/#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void generic1x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void generic1x3(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void generic3x1(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void generic3x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void stairs(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void step(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Sizzle.resLoc(id));
    }

    private void smelting(String id, IItemProvider from, IItemProvider to, double xp) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(from), to, (float) xp, 200)
                            .addCriterion("has_ingredient", hasItem(from))
                            .build(consumer, Sizzle.resLoc(id));
    }

    @Override
    public String getName() {
        return "Sizzle/Recipes";
    }
}
