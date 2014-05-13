package org.blh.formuladecorator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.blh.core.unit.Factor;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.distance.Meters;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.attempts.composite.Recipe;
import org.blh.formuladecorator.formulas.observable.gravity.ObservableSimpleOriginalGravityFormula;

/**
 * Should this object be mutable? Or should all its members be mutable?
 *
 * @author thinner
 */
public class FullContext {

    private final ObjectProperty<Recipe> recipeProperty;
    private GeneralBreweryInfo brewery;
    private Equipment equipment;
    /////////////
    private InputtedOrCalculatedValue<Liters> preMashVolume;
    private InputtedOrCalculatedValue<Minutes> boilTime;
    private InputtedOrCalculatedValue<SpecificGravity> preBoilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> boilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> postBoilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> originalGravity;
    private InputtedOrCalculatedValue<SpecificGravity> finalGravity;
    private InputtedOrCalculatedValue<ABV> alcoholContent;
    private InputtedOrCalculatedValue<Factor> yeastApparentAttenuation;
    private InputtedOrCalculatedValue<MaltColorUnit> maltColorUnit;
    private InputtedOrCalculatedValue<ColorPotential> totalColorPotential;
    private InputtedOrCalculatedValue<Factor> extractionEfficiency;
    private InputtedOrCalculatedValue<Kilograms> totalGrainWeight;
    private InputtedOrCalculatedValue<Grams> totalHopWeight;
    ///////////////
    private Input<Meters> elevation;
    /**
     * How many percent of the volume is lost when cooling.
     */
    private Input<Factor> coolingLoss;

    public FullContext() {
		recipeProperty = new SimpleObjectProperty<>();

		extractionEfficiency = new InputtedOrCalculatedValue<>(new Factor(0.8));
        originalGravity = new InputtedOrCalculatedValue<>(new ObservableSimpleOriginalGravityFormula(this));
    }

    public Recipe getRecipe() {
        return recipeProperty.get();
    }

    public void setRecipe(Recipe recipe) {
        this.recipeProperty.setValue(recipe);
    }

	public ObjectProperty<Recipe> recipeProperty() {
		return recipeProperty;
	}

    public GeneralBreweryInfo getBrewery() {
        return brewery;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public InputtedOrCalculatedValue<Liters> getPreMashVolume() {
        return preMashVolume;
    }

    public InputtedOrCalculatedValue<Minutes> getBoilTime() {
        return boilTime;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getPreBoilGravity() {
        return preBoilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getBoilGravity() {
        return boilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getPostBoilGravity() {
        return postBoilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getOriginalGravity() {
        return originalGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getFinalGravity() {
        return finalGravity;
    }

    public InputtedOrCalculatedValue<ABV> getAlcoholContent() {
        return alcoholContent;
    }

    public InputtedOrCalculatedValue<Factor> getYeastApparentAttenuation() {
        return yeastApparentAttenuation;
    }

    public InputtedOrCalculatedValue<MaltColorUnit> getMaltColorUnit() {
        return maltColorUnit;
    }

    public InputtedOrCalculatedValue<ColorPotential> getTotalColorPotential() {
        return totalColorPotential;
    }

    public InputtedOrCalculatedValue<Factor> getExtractionEfficiency() {
        return extractionEfficiency;
    }

    public InputtedOrCalculatedValue<Kilograms> getTotalGrainWeight() {
        return totalGrainWeight;
    }

    public InputtedOrCalculatedValue<Grams> getTotalHopWeight() {
        return totalHopWeight;
    }

    public Input<Meters> getElevation() {
        return elevation;
    }

    public Input<Factor> getCoolingLoss() {
        return coolingLoss;
    }

    public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.value().value();
        double totalGravityDifference = postBoilGravity.value().value() - preBoilGravity.value().value();

        return new SpecificGravity(totalGravityDifference * timePercent);
    }
}