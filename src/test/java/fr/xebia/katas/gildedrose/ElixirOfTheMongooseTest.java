package fr.xebia.katas.gildedrose;

import static fr.xebia.katas.gildedrose.Inn.ELIXIR_OF_THE_MONGOOSE;

import org.junit.Test;

public class ElixirOfTheMongooseTest extends AbstractInnTest {
	
	@Test
	public void quality_should_degrade_by_one() {
		final Item item = getInn().getItem(ELIXIR_OF_THE_MONGOOSE);
		quality_should_degrade_by_one(item);
	}

	@Test
	public void quality_should_degrade_twice_as_fast_after_passed_date() {
		final Item dexterityVest = getInn().getItem(ELIXIR_OF_THE_MONGOOSE);
		quality_should_degrade_twice_as_fast_after_passed_date(dexterityVest);
	}
}
