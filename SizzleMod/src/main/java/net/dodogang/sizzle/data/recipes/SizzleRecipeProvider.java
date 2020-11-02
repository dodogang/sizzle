package net.dodogang.sizzle.data.recipes;

import net.dodogang.sizzle.common.Sizzle;
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

    }

    private void generic2x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("##")
                           .patternLine("##")
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
