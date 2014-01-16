package org.blh.core.recipe;

import org.blh.core.ingredients.Yeast;
import org.blh.core.units.Unit;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class YeastAddition<T extends Unit<? extends Number>> {

	private final Yeast yeast;
	private final T amount;

	public YeastAddition(Yeast yeast, T amount) {
		this.yeast = yeast;
		this.amount = amount;
	}

	public Yeast getYeast() {
		return yeast;
	}

	public T getAmount() {
		return amount;
	}

}
