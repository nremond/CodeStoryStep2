package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn._5_DEXTERITY_VEST;

import org.junit.Test;

public class DexterityVestInnTest extends AbstractInnTest {

	@Test
	public void dexterity_vest_quality_should_degrade_by_one() {
		final Item item = getInn().getItem(_5_DEXTERITY_VEST);
		quality_should_degrade_by_one(item);
	}

	@Test
	public void dexterity_vest_quality_should_degrade_twice_as_fast_after_passed_date() {
		final Item dexterityVest = getInn().getItem(_5_DEXTERITY_VEST);
		quality_should_degrade_twice_as_fast_after_passed_date(dexterityVest);
	}
}
