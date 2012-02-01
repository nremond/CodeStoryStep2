package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn.CONJURED_MANA_CAKE;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ConjuredManaCakeTest extends AbstractInnTest {

	@Test
	public void conjured_mana_cake_quality_should_degrade_twice_as_fast_after_passed_date() {
		final Item dexterityVest = getInn().getItem(CONJURED_MANA_CAKE);
		quality_should_degrade_twice_as_fast_after_passed_date(dexterityVest);
	}

	@Test
	public void quality_should_degrade_twice_as_fast_as_normal_items() {
		final Item dexterityVest = getInn().getItem(CONJURED_MANA_CAKE);

		int previousQuality = dexterityVest.getQuality();
		getInn().updateQuality();
		assertThat(dexterityVest.getQuality()).isEqualTo(previousQuality - 2);
	}
}
