package com.gildedrose;

/**
 * @author gvhoecke {@literal <gianni@giannivanhoecke.com>}
 */
public class ConjuredItemUpdater extends NormalItemUpdater {

    ConjuredItemUpdater(Item item) {
        super(item);
    }

    @Override
    protected int getQualityWeight() {
        return super.getQualityWeight() * 2;
    }
}
