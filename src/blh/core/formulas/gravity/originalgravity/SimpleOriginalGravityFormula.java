package blh.core.formulas.gravity.originalgravity;

import blh.core.formulas.Formula;
import blh.core.ingredients.Malt;
import blh.core.recipe.GristPart;
import blh.core.uncategorized.FullContext;
import blh.core.units.ExtractPotential;
import blh.core.units.Factor;
import blh.core.units.Unit;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Gallons;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import blh.core.units.weight.Lbs;
import java.util.List;

/**
 *
 * Taken from
 * http://byo.com/european-pale-lager/item/409-calculating-gravity-bitterness-and-color-techniques and
 * http://brewgr.com/calculations/original-gravity
 *
 * SGP(GP/gal) = [W(lb.) * EP(GP/lb.) * EE] / V(gallons)
 *
 * SGP(GP/l) = [W(kg) * EP(GP/kg) * EE] / V(l)
 *
 * SG = SGP / 1000 + 1
 * 
 * 
 * 
 * 1 Lb  = 0.45359237 kilograms = a kg
 * 1 gal = 3.78541178 liters    = b liters
 * SGP(GP/(b*l)     = [W(a*kg) * EP(GP/(b*l)) * EE] / V(b*l)
 * SGP(GP/(gal*b*l) = [(lbs*a*kg) * (GP/(lbs*a*kg)) * EE] / (gal*b*l)
 *                  = [(lbs*a*kg) * (GP/( 1 *a*kg)) * EE] / (gal*b*l)
 *                  = [(lbs*a*kg) * EE] / (gal*b*l) * (GP/( 1 *a*kg))
 *                  = [(lbs*a*kg) * EE * GP] / (gal*b*l*a*kg)
 *                  = (lbs * EE * GP) / (gal * b * l)
 * 
 * GP / (gal * b * l) = (lbs * EE * GP) / (gal * b * l)
 * GP = lbs * EE * GP
 * 
 *
 * @author thinner
 */
public class SimpleOriginalGravityFormula implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        return calc(context.getIngredientsList().getFermentables(), getVolume(context), context.extractionEfficiency.value());
    }

    public SpecificGravity calc(List<GristPart> gristParts, Liters preBoilVolume, Factor efficiency) {
        double a = 0;
        for (GristPart gp : gristParts) {
            Factor eff = (gp.getMalt().type == Malt.TYPE.EXTRACT) ? new Factor(1) : efficiency;
            a += calcForOneGristPart(gp.getAmount(), gp.getMalt().extractPotential, eff);
        }

        return new GravityPoints(a / preBoilVolume.value()).toSpecificGravity();
    }

    protected Liters getVolume(FullContext context) {
        return context.postBoilVolume.value();
    }

    public SpecificGravity calc(List<GristPart> gristParts, Gallons preBoilVolume, Factor efficiency) {
        double a = 0;
        for (GristPart gp : gristParts) {
            Lbs grainWeight = new Lbs(gp.getAmount());
            Factor eff = (gp.getMalt().type == Malt.TYPE.EXTRACT) ? new Factor(1) : efficiency;
            LbsExtractPotential ep = new LbsExtractPotential(gp.getMalt().extractPotential);

            a += calcForOneGristPart(grainWeight, ep, eff);
        }

        return new GravityPoints(a / preBoilVolume.value()).toSpecificGravity();
    }

    private double calcForOneGristPart(Lbs grainWeight, LbsExtractPotential extractPotential, Factor extractionEfficiency) {
        return grainWeight.value() * extractPotential.value() * extractionEfficiency.value();
    }

    private double calcForOneGristPart(Kilograms grainWeight, ExtractPotential extractPotential, Factor extractionEfficiency) {
        return grainWeight.value() * extractPotential.value() * extractionEfficiency.value();
    }

    private class LbsExtractPotential extends Unit<Double> {

        /**
         *  EE(GP/Kg) = GP / Kg
         *  EE(GP/Lbs) = GP / Lbs
         * 
         *  EE(GP/Lbs) = GP / (2.20462262 * Kg)
         *  EE(GP/Kg)  = GP / (Lbs / 2.20462262)
         */
        public LbsExtractPotential(ExtractPotential value) {
            super(value.getGravityPoints().value() * new Lbs(value.getWeight()).value());
        }
    }
}
