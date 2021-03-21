package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityForItem(item);
        }
    }
    
    private void updateQualityForItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
                increaseItemQuality(item);
                decreaseSellIn(item);
                if (isExpired(item)) {
                    increaseItemQuality(item);
                }
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                increaseItemQuality(item);
                if (item.sellIn < 11) {
                    increaseItemQuality(item);
                }
                if (item.sellIn < 6) {
                    increaseItemQuality(item);
                }
                decreaseSellIn(item);
                if (isExpired(item)) {
                    item.quality = 0;
                }
                break;
            default:
                decreaseItemQuality(item);
                decreaseSellIn(item);
                if (isExpired(item)) {
                    decreaseItemQuality(item);
                }
                break;
        }
    }

    private void decreaseItemQuality(Item item) {
        if (item.quality > 0) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                return;
            }
            item.quality = item.quality - 1;
        }
    }

    private void increaseItemQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseSellIn(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            item.sellIn = item.sellIn - 1;
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}