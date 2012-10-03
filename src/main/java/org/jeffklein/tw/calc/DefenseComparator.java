package org.jeffklein.tw.calc;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 10/2/12
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefenseComparator implements Comparator<InventoryItem>{
    public int compare(InventoryItem o1, InventoryItem o2) {
        if(o1.getDefense() < o2.getDefense()) {
            return 1;
        }
        if(o1.getDefense() > o2.getDefense()) {
            return -1;
        }
        if(o1.getDefense() == o2.getDefense()) {
            if(o1.getUpkeep() < o2.getUpkeep()) {
                return -1;
            }
            if(o1.getUpkeep() > o2.getUpkeep()) {
                return 1;
            }
        }
        return 0;
    }
}
