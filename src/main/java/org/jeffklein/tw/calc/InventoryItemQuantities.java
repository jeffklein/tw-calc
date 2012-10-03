package org.jeffklein.tw.calc;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 10/2/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryItemQuantities {
    private InventoryItem item;
    private int quantityOwned;
    private int quantityUsed;
    private int quantityUnused;

    public InventoryItemQuantities(InventoryItem item, int quantityOwned, int quantityUsed, int quantityUnused) {
        this.item = item;
        this.quantityOwned = quantityOwned;
        this.quantityUsed = quantityUsed;
        this.quantityUnused = quantityUnused;
    }

    public String toString() {
        return item.getInventoryItemClass()+" "+item.name()+" owned: "+quantityOwned+" used: "+quantityUsed+" unused: "+quantityUnused+" attack: "+quantityUsed*item.getAttack()+" defense: "+quantityUsed*item.getDefense();
    }

    public InventoryItem getItem() {
        return item;
    }

    public int getQuantityOwned() {
        return quantityOwned;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public int getQuantityUnused() {
        return quantityUnused;
    }
}
