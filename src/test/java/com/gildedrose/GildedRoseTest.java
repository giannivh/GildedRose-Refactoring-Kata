package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

class GildedRoseTest {

    @Test
    void updateQualityLowersSellInAndQualityByOneForNormalItems() {
        // given
        String itemName = "Normal Item";
        int itemSellIn  = 3;
        int itemQuality = 3;
        Item item = createNormalItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.name,    is(equalTo(itemName)));
        assertThat(item.sellIn,  is(equalTo(itemSellIn - 1)));
        assertThat(item.quality, is(equalTo(itemQuality - 1)));
    }

    @Test
    void updateQualityLowersQualityTwiceAsFastForExpiredItems() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 3;
        Item item = createNormalItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality - 2)));
    }

    @Test
    void updateQualityNeverReturnsNegativeQuality() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 0;
        Item item = createNormalItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(0)));
    }

    @Test
    void updateQualityIncreasesQualityByOneForAgedBrieItems() {
        // given
        int itemSellIn  = 3;
        int itemQuality = 3;
        Item item = createAgedBrieItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality + 1)));
    }

    @Test
    void updateQualityIncreasesQualityTwiceAsFastForExpiredAgedBrieItems() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 3;
        Item item = createAgedBrieItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality + 2)));
    }

    @Test
    void updateQualityNeverReturnsQualityAboveFiftyForAgedBrieItems() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 50;
        Item item = createAgedBrieItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(50)));
    }

    @Test
    void updateQualityNeverSellsOrDecreasesQualityForLegendaryItems() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 80;
        Item item = createLegendaryItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.sellIn,  is(equalTo(itemSellIn)));
        assertThat(item.quality, is(equalTo(itemQuality)));
    }

    @Test
    void updateQualityIncreasesQualityByOneForBackstagePassesItemsWhenSellInIsOverTenDays() {
        // given
        int itemSellIn  = 11;
        int itemQuality = 3;
        Item item = createBackstagePassesItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality + 1)));
    }

    @Test
    void updateQualityIncreasesQualityByTwoForBackstagePassesItemsWhenSellInIsTenDaysOrLess() {
        // given
        int itemSellIn  = 10;
        int itemQuality = 3;
        Item item = createBackstagePassesItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality + 2)));
    }

    @Test
    void updateQualityIncreasesQualityByThreeForBackstagePassesItemsWhenSellInIsFiveDaysOrLess() {
        // given
        int itemSellIn  = 5;
        int itemQuality = 3;
        Item item = createBackstagePassesItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(itemQuality + 3)));
    }

    @Test
    void updateQualityDropsQualityToZeroForBackstagePassesItemsWhenSellInIsAfterConcert() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 3;
        Item item = createBackstagePassesItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(0)));
    }

    @Test
    void updateQualityNeverReturnsQualityAboveFiftyForBackstagePassesItems() {
        // given
        int itemSellIn  = 3;
        int itemQuality = 49;
        Item item = createBackstagePassesItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.quality, is(equalTo(50)));
    }

    @Test
    void updateQualityLowersSellInByOneAndQualityByTwoForConjuredItems() {
        // given
        int itemSellIn  = 3;
        int itemQuality = 3;
        Item item = createConjuredItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.sellIn,  is(equalTo(itemSellIn - 1)));
        assertThat(item.quality, is(equalTo(itemQuality - 2)));
    }

    @Test
    void updateQualityLowersSellInByOneAndQualityByFourForExpiredConjuredItems() {
        // given
        int itemSellIn  = 0;
        int itemQuality = 6;
        Item item = createConjuredItem(itemSellIn, itemQuality);

        // when
        runGildedRoseApp(item);

        // then
        assertThat(item.sellIn,  is(equalTo(itemSellIn - 1)));
        assertThat(item.quality, is(equalTo(itemQuality - 4)));
    }

    // util

    private void runGildedRoseApp(Item... items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    private Item createNormalItem(int sellIn, int quality) {
        return new Item("Normal Item", sellIn, quality);
    }

    private Item createAgedBrieItem(int sellIn, int quality) {
        return new Item("Aged Brie", sellIn, quality);
    }

    private Item createLegendaryItem(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    private Item createBackstagePassesItem(int sellIn, int quality) {
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    private Item createConjuredItem(int sellIn, int quality) {
        return new Item("Conjured Mana Cake", sellIn, quality);
    }
}
