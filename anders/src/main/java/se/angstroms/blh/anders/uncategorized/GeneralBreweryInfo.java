package se.angstroms.blh.anders.uncategorized;

import org.blh.core.unit.Factor;
import se.angstroms.blh.anders.uncategorized.iocv.InputtedValue;

/**
 * Some data unique to each brewery.
 *
 * @author thinner
 */
public class GeneralBreweryInfo {
    private InputtedValue<Factor> effiency;

    public Factor getEffiency() {
        return effiency.get();
    }
}