package org.jeffklein.tw.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 9/28/12
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public enum InventoryItemClass {

    MELEE,
    WEAPON,
    ARMOR;

    private List<InventoryItem> inventoryItemsInClassSortedByAttack = null;
    private List<InventoryItem> inventoryItemsInClassSortedByDefense = null;

    public List<InventoryItem> getInventoryItemsInClassSortedByAttack() {
        if(this.inventoryItemsInClassSortedByAttack == null) {
            this.inventoryItemsInClassSortedByAttack = new ArrayList<InventoryItem>();
            for (InventoryItem item : InventoryItem.values()) {
                if(item.getInventoryItemClass() == this) {
                    this.inventoryItemsInClassSortedByAttack.add(item);
                }
            }
            Collections.sort(this.inventoryItemsInClassSortedByAttack, new AttackComparator());
        }
        return Collections.unmodifiableList(this.inventoryItemsInClassSortedByAttack);
    }

    public List<InventoryItem> getInventoryItemsInClassSortedByDefense() {
        if(this.inventoryItemsInClassSortedByDefense == null) {
            this.inventoryItemsInClassSortedByDefense = new ArrayList<InventoryItem>();
            for (InventoryItem item : InventoryItem.values()) {
                if(item.getInventoryItemClass() == this) {
                    this.inventoryItemsInClassSortedByDefense.add(item);
                }
            }
            Collections.sort(this.inventoryItemsInClassSortedByDefense, new DefenseComparator());
        }
        return Collections.unmodifiableList(this.inventoryItemsInClassSortedByDefense);
    }
}
