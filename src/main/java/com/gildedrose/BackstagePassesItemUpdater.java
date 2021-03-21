package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class BackstagePassesItemUpdater extends NormalItemUpdater {

    private static final int ELEVEN_DAYS = 11;
    private static final int SIX_DAYS    = 6;

    BackstagePassesItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseItemQuality();
        increaseItemQualityWhenSellInIsLessThan(ELEVEN_DAYS);
        increaseItemQualityWhenSellInIsLessThan(SIX_DAYS);
        decreaseSellIn();
        if (isExpired()) {
            dropItemQuality();
        }
    }

    // util

    private void increaseItemQualityWhenSellInIsLessThan(int days) {
        if (item.sellIn < days) {
            increaseItemQuality();
        }
    }
}
