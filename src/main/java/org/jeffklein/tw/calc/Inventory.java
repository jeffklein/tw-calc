package org.jeffklein.tw.calc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 9/28/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Inventory {

    private Map<InventoryItemClass, Map<InventoryItem, Integer>> quantityByTypeByClass = new HashMap<InventoryItemClass, Map<InventoryItem, Integer>>();

    /**
     * initialize all inventory quantities to zero.
     */
    public Inventory() {
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            Map<InventoryItem, Integer> quantityByType = new HashMap<InventoryItem, Integer>();
            for (InventoryItem item : InventoryItem.values()) {
                quantityByType.put(item, 0);
            }
            quantityByTypeByClass.put(itemClass, quantityByType);
        }
    }

    public void setQuantityForType(InventoryItem item, Integer quantity) {
        Map<InventoryItem, Integer> quantityByType = this.quantityByTypeByClass.get(item.getInventoryItemClass());
        quantityByType.put(item, quantity);
    }

    public Integer getQuantityForType(InventoryItem item) {
        Map<InventoryItem, Integer> quantityByType = this.quantityByTypeByClass.get(item.getInventoryItemClass());
        return quantityByType.get(item);
    }

    public Map<InventoryItemClass, Map<InventoryItem, Integer>> getQuantityByTypeByClass() {
        return Collections.unmodifiableMap(quantityByTypeByClass);
    }
}
