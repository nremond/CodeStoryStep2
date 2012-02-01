package fr.xebia.katas.gildedrose;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;

public abstract class AbstractInnTest {
	
	private Inn inn;
	
	@Before
	public void setUp() {
		inn = new Inn();
	}
	
	protected Inn getInn() {
		return inn;
	}

	protected void quality_of_item_never_more_than_50(final Item item) {
		boolean qualityIncrease = true;
		while (qualityIncrease) {
			int previousQuality = item.getQuality();
			assertThat(previousQuality).isLessThanOrEqualTo(50);
			inn.updateQuality();
			int newQuality = item.getQuality();
			qualityIncrease = newQuality > previousQuality;
		}
	}

	protected void quality_should_degrade_by_one(final Item item) {
		assertThat(item.getSellIn()).isGreaterThan(0);
		final int previousQuality = item.getQuality();
		inn.updateQuality();
		assertThat(item.getQuality()).isEqualTo(previousQuality - 1);
	}
	
	protected void quality_should_degrade_twice_as_fast_after_passed_date(final Item item) {
		int sellIn = item.getSellIn();
		int beforePassedDateDecrement = -1;
		while (sellIn > 0) {
			final int before = item.getQuality();
			getInn().updateQuality();
			sellIn = item.getSellIn();
			beforePassedDateDecrement = before - item.getQuality();
		}

		final int before = item.getQuality();
		getInn().updateQuality();
		final int after = item.getQuality();

		assertThat(before - after).isEqualTo(beforePassedDateDecrement * 2);
	}

}
