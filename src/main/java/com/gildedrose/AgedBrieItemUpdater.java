package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class AgedBrieItemUpdater extends NormalItemUpdater {

    AgedBrieItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseItemQuality();
        decreaseSellIn();
        if (isExpired()) {
            increaseItemQuality();
        }
    }
}
