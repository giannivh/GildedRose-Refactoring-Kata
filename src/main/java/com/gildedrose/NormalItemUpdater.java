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

    protected int getQualityWeight() {
        return 1;
    }

    // util

    private static final int MIN_QUALITY_ALLOWED =  0;
    private static final int MAX_QUALITY_ALLOWED = 50;
    private static final int EXPIRY_THRESHOLD    =  0;

    protected void decreaseItemQuality() {
        item.quality = Math.max(MIN_QUALITY_ALLOWED, item.quality - getQualityWeight());
    }

    protected void increaseItemQuality() {
        item.quality = Math.min(MAX_QUALITY_ALLOWED, item.quality + getQualityWeight());
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
