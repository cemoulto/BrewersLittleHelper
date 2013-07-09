package blh.core.recipe;

import blh.core.units.ExtractPotential;
import blh.core.units.color.ColorPotential;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.weight.Grams;
import blh.core.units.weight.Kilograms;
import blh.core.units.weight.Lbs;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author thinner
 */
public class IngredientsList {

    private List<GristPart> fermentables;
    private List<HopAddition> hopAdditions;

    public List<GristPart> getFermentables() {
        return fermentables;
    }

    public List<HopAddition> getHopAdditions() {
        return new LinkedList<HopAddition>(hopAdditions);
    }

    public Kilograms getTotalGrainWeight() {
        double totalWeight = 0;
        for (GristPart fermentable : fermentables) {
            totalWeight += fermentable.getMalt().color.value();
        }
        return new Kilograms(totalWeight);
    }

    public GravityPoints getTotalGravityPoints() {
        double specificGravityPoints = 0;
        for (GristPart fermentable : fermentables) {
            specificGravityPoints += fermentable.getMalt().extractPotential.value();
        }

        return new GravityPoints(specificGravityPoints);
    }

    public ColorPotential getTotalColorPotential() {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential.add(fermentable.getMalt().color, new Lbs(fermentable.getAmount()));
        }
        return potential;
    }

    public Grams getTotalHopWeight() {
        double totalGrams = 0;
        for (HopAddition addition : hopAdditions) {
            totalGrams += addition.getAmount().value();
        }
        return new Grams(totalGrams);
    }
}
