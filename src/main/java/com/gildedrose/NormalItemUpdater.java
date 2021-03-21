package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class NormalItemUpdater implements ItemUpdater {

    protected final Item item;

    NormalItemUpdater(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        decreaseItemQuality();
        decreaseSellIn();
        if (isExpired()) {
            decreaseItemQuality();
        }
    }

    // util

    protected void decreaseItemQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void increaseItemQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    protected void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

    protected void dropItemQuality() {
        item.quality = 0;
    }
}
