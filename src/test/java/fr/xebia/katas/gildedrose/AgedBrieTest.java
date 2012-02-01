package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn.AGED_BRIE;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class AgedBrieTest extends AbstractInnTest {

	@Test
	public void quality_should_degrade_twice_as_fast_after_passed_date() {
		final Item dexterityVest = getInn().getItem(AGED_BRIE);
		quality_should_degrade_twice_as_fast_after_passed_date(dexterityVest);
	}
	
	@Test
	public void quality_should_increase() {
		final Item item = getInn().getItem(AGED_BRIE);
		assertThat(item.getSellIn()).isGreaterThan(0);
		final int previousQuality = item.getQuality();
		getInn().updateQuality();
		assertThat(item.getQuality()).isGreaterThan(previousQuality);
	}
	
	@Test
	public void quality_is_never_more_than_50() {
		final Item item = getInn().getItem(AGED_BRIE);
		quality_of_item_never_more_than_50(item);
	}
}
