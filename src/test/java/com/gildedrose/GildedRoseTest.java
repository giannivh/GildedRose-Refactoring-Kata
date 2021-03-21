package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

class GildedRoseTest {

    @Test
    void updateQualityLowersSellInAndQualityForNormalItems() {
        // given
        String itemName = "Normal item";
        int itemSellIn  = 5;
        int itemQuality = 5;
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.name,    is(equalTo(itemName)));
        assertThat(item.sellIn,  is(equalTo(4)));
        assertThat(item.quality, is(equalTo(4)));
    }

    // util

    private void runGildedRoseApp(Item... items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }
}
