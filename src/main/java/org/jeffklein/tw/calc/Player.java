package org.jeffklein.tw.calc;

/**
 * Created with IntelliJ IDEA.
 * User: jklein
 * Date: 9/28/12
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private Integer mobCount;
    private Integer attackStat;
    private Integer defenseStat;
    private Inventory inventory;

    public Player() {
        this.mobCount = 0;
        this.inventory = new Inventory();
    }

    public Integer getMobCount() {
        return mobCount;
    }

    public void setMobCount(Integer mobCount) {
        this.mobCount = mobCount;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
