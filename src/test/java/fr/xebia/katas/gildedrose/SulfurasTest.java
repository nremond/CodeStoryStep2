package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn.SULFURAS_HAND_OF_RAGNAROS;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SulfurasTest extends AbstractInnTest {
	
	@Test
	public void quality_should_never_degrade() {
		final Item item = getInn().getItem(SULFURAS_HAND_OF_RAGNAROS);
		int previous = item.getQuality();
		getInn().updateQuality();
		assertThat(item.getQuality()).isEqualTo(previous);
	}
	
	@Test
	public void item_cannot_be_sold() {
		final Item item = getInn().getItem(SULFURAS_HAND_OF_RAGNAROS);
		int previous = item.getSellIn();
		assertThat(previous).isEqualTo(0);
		getInn().updateQuality();
		assertThat(item.getSellIn()).isEqualTo(previous);
	}

}
