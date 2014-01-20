package org.blh.core.units.pressure;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:04 AM
 */
public class Bar extends NumericalUnit {

    public Bar(BigDecimal value) {
        super(value);
    }

    public Bar(double value) {
        super(value);
    }
}
