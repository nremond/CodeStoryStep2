package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class BackstagePassesTest extends AbstractInnTest {
	@Test
	public void quality_is_never_more_than_50() {
		final Item item = getInn().getItem(
				BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);

		quality_of_item_never_more_than_50(item);
	}

	/**
	 * “Backstage passes”, like aged brie, increases in Quality as it’s SellIn
	 * value approaches; Quality increases by 2 when there are 10 days or less
	 * and by 3 when there are 5 days or less but Quality drops to 0 after the
	 * concert
	 */
	@Test
	public void quality_increase_as_spec_says() {
		final Item item = getInn().getItem(
				BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT);
		
		int sellIn = item.getSellIn();
		while (sellIn >= -5) {
			int prev = item.getQuality();
			getInn().updateQuality();
			int current = item.getQuality();
			
			if (sellIn <= 0) {
				assertThat(current).isEqualTo(0);
			} else if (sellIn > 0 && sellIn <= 5) {
				assertThat(prev-current).isEqualTo(-3);
			} else if (sellIn <= 10) {
				assertThat(prev-current).isEqualTo(-2);
			}
			sellIn = item.getSellIn();
		}
	}
}
