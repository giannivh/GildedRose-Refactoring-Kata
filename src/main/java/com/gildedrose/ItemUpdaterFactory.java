package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class ItemUpdaterFactory {

    public static ItemUpdater createUpdater(Item item) {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros":
                return new LegendaryExtendedItem(item);
            case "Aged Brie":
                return new AgedBrieItemUpdater(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassesItemUpdater(item);
            default:
                return new NormalItemUpdater(item);
        }
    }
}
