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
        if (item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        increaseItemQuality(item);
                    }

                    if (item.sellIn < 6) {
                        increaseItemQuality(item);
                    }
                }
            }
        } else {
            decreaseItemQuality(item);
        }

        decreaseSellIn(item);

        if (item.sellIn < 0) {
            if (item.name.equals("Aged Brie")) {
                increaseItemQuality(item);
            } else {
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    decreaseItemQuality(item);
                }
            }
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
}