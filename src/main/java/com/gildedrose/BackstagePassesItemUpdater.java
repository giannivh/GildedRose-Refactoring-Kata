package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class BackstagePassesItemUpdater extends NormalItemUpdater {

    BackstagePassesItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseItemQuality();
        if (item.sellIn < 11) {
            increaseItemQuality();
        }
        if (item.sellIn < 6) {
            increaseItemQuality();
        }
        decreaseSellIn();
        if (isExpired()) {
            dropItemQuality();
        }
    }
}
