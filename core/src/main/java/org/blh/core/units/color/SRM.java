package org.blh.core.units.color;

import org.blh.core.units.NumericalUnit;

/**
 * EBC = 1.97 * SRM -> SRM = EBC / 1.97
 *
 * @author thinner
 */
public class SRM extends NumericalUnit {

    public static final double CONVERSION_FACTOR = 1.97;

    public SRM(double value) {
        super(value);
    }

    public SRM(EBC ebc) {
        super(ebc.value() / CONVERSION_FACTOR);
    }

    public EBC toEBC() {
        return new EBC(this.value * CONVERSION_FACTOR);
    }
}
