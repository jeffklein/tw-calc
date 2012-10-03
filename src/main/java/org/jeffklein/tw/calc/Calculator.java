package org.jeffklein.tw.calc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 9/28/12
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {
    public Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> calculateInventoryItemUsageForAttack(Player player) {
        Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> quantitiesByTypeByClass = new HashMap<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>>();
        Inventory inventory = player.getInventory();
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            Integer mobRemaining = player.getMobCount();
            List<InventoryItem> itemsInClassByAttack = itemClass.getInventoryItemsInClassSortedByAttack();
            if (mobRemaining > 0) {
                Map<InventoryItem, InventoryItemQuantities> quantitiesByType = new HashMap<InventoryItem, InventoryItemQuantities>();
                for (InventoryItem item : itemsInClassByAttack) {
                    Integer itemCount = inventory.getQuantityForType(item);
                    if (itemCount <= mobRemaining) {
                        InventoryItemQuantities quantities = new InventoryItemQuantities(item, itemCount, itemCount, 0);
                        quantitiesByType.put(item, quantities);
                        mobRemaining -= itemCount;
                    }
                    else {
                        InventoryItemQuantities quantities = new InventoryItemQuantities(item, itemCount, mobRemaining, itemCount-mobRemaining);
                        quantitiesByType.put(item, quantities);
                        mobRemaining -= mobRemaining;
                    }
                }
                quantitiesByTypeByClass.put(itemClass, quantitiesByType);
            }
        }
        return quantitiesByTypeByClass;
    }

    public Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> calculateInventoryItemUsageForDefense(Player player) {
        Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> quantitiesByTypeByClass = new HashMap<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>>();
        Inventory inventory = player.getInventory();
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            Integer mobRemaining = player.getMobCount();
            List<InventoryItem> itemsInClassByAttack = itemClass.getInventoryItemsInClassSortedByDefense();
            if (mobRemaining > 0) {
                Map<InventoryItem, InventoryItemQuantities> quantitiesByType = new HashMap<InventoryItem, InventoryItemQuantities>();
                for (InventoryItem item : itemsInClassByAttack) {
                    Integer itemCount = inventory.getQuantityForType(item);
                    if (itemCount <= mobRemaining) {
                        InventoryItemQuantities quantities = new InventoryItemQuantities(item, itemCount, itemCount, 0);
                        quantitiesByType.put(item, quantities);
                        mobRemaining -= itemCount;
                    }
                    else {
                        InventoryItemQuantities quantities = new InventoryItemQuantities(item, itemCount, mobRemaining, itemCount-mobRemaining);
                        quantitiesByType.put(item, quantities);
                        mobRemaining -= mobRemaining;
                    }
                }
                quantitiesByTypeByClass.put(itemClass, quantitiesByType);
            }
        }
        return quantitiesByTypeByClass;
    }
}
