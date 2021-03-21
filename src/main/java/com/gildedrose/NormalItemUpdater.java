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

    private static final int MIN_QUALITY_ALLOWED =  0;
    private static final int MAX_QUALITY_ALLOWED = 50;
    private static final int EXPIRY_THRESHOLD    =  0;

    protected void decreaseItemQuality() {
        if (item.quality > MIN_QUALITY_ALLOWED) {
            item.quality = item.quality - 1;
        }
    }

    protected void increaseItemQuality() {
        if (item.quality < MAX_QUALITY_ALLOWED) {
            item.quality = item.quality + 1;
        }
    }

    protected void dropItemQuality() {
        item.quality = MIN_QUALITY_ALLOWED;
    }

    protected void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    protected boolean isExpired() {
        return item.sellIn < EXPIRY_THRESHOLD;
    }
}
