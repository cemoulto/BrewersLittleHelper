package org.blh.recipe.uncategorized;

import org.blh.core.units.Factor;

/**
 *
 * @author thinner
 */
public class GeneralBreweryInfo {
    private Input<Factor> effiency;

    public Factor getEffiency() {
        return effiency.value();
    }


}