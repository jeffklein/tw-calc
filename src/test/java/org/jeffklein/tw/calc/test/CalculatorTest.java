package org.jeffklein.tw.calc.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeffklein.tw.calc.Calculator;
import org.jeffklein.tw.calc.Inventory;
import org.jeffklein.tw.calc.InventoryItem;
import org.jeffklein.tw.calc.InventoryItemClass;
import org.jeffklein.tw.calc.InventoryItemQuantities;
import org.jeffklein.tw.calc.Player;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Test
//@ContextConfiguration(locations = "classpath:/com/guidewire/platform/plapp1/spring-beans-plapp1.xml")
public class CalculatorTest /*extends AbstractTestNGSpringContextTests*/ {

    private static final Log log = LogFactory.getLog(CalculatorTest.class);

    private Player createPlayer() {
        Player player = new Player();
        player.setMobCount(87000);
        player.setAttackStat(0);
        player.setDefenseStat(0);
        Inventory inventory = player.getInventory();
        inventory.setQuantityForType(InventoryItem.SHANK, 1);
        inventory.setQuantityForType(InventoryItem.SATURDAY_NIGHT_SPECIAL, 2);
        inventory.setQuantityForType(InventoryItem.GARROTE, 955);
        inventory.setQuantityForType(InventoryItem.RIOT_SHIELD, 1185);
        inventory.setQuantityForType(InventoryItem.MOLOTOV_COCKTAIL, 5);
        inventory.setQuantityForType(InventoryItem.BRASS_KNUCKLES, 1355);
        inventory.setQuantityForType(InventoryItem.FIFTY_SEVEN_MAGNUM, 4);
        inventory.setQuantityForType(InventoryItem.GRENADE, 4);
        inventory.setQuantityForType(InventoryItem.AK47, 3);
        inventory.setQuantityForType(InventoryItem.GERMAN_STILLETTO_KNIFE, 1400);
        inventory.setQuantityForType(InventoryItem.POTATO_MASHER, 865);
        inventory.setQuantityForType(InventoryItem.SAWED_OFF_SHOTGUN, 1485);
        inventory.setQuantityForType(InventoryItem.GLOCK_31, 1355);
        inventory.setQuantityForType(InventoryItem.SLUGGER, 1705);
        inventory.setQuantityForType(InventoryItem.STEEL_TOED_SHOES, 82540);
        inventory.setQuantityForType(InventoryItem.BODY_ARMOR, 85335);
        inventory.setQuantityForType(InventoryItem.LUPARA, 880);
        inventory.setQuantityForType(InventoryItem.MACHETE, 1455);
        inventory.setQuantityForType(InventoryItem.TOMMY_GUN, 55550);
        inventory.setQuantityForType(InventoryItem.CHAINSAW, 1300);
        inventory.setQuantityForType(InventoryItem.THREE_THIRTY_EIGHT_LAPUA_RIFLE, 58090);
        inventory.setQuantityForType(InventoryItem.KEVLAR_LINED_SUIT, 1665);
        inventory.setQuantityForType(InventoryItem.AR15_ASSAULT_RIFLE, 1950);
        inventory.setQuantityForType(InventoryItem.BERETTA_MODELO_38A, 1310);
        inventory.setQuantityForType(InventoryItem.BAZOOKA, 1470);
        inventory.setQuantityForType(InventoryItem.BREN_GUN, 26720);
        return player;
    }


    @Test
    public void testCalculateDefense() {
        Player player = createPlayer();
        Calculator calc = new Calculator();
        Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> quantitiesByClass = calc.calculateInventoryItemUsageForDefense(player);
        System.out.format("%-10s%-30s%10s%10s%10s%10s%10s%n",
                "CLASS",
                "ITEM",
                "OWNED",
                "USED",
                "UNUSED",
                "DEFENSE",
                "UPKEEP");
        System.out.println("------------------------------------------------------------------------------------------");
        int totalDefense = 0;
        int totalUpkeep = 0;
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            Map<InventoryItem, InventoryItemQuantities> quantitiesByType = quantitiesByClass.get(itemClass);
            for (InventoryItem item : itemClass.getInventoryItemsInClassSortedByAttack()) {
                InventoryItemQuantities quantities = quantitiesByType.get(item);
                if (quantities.getQuantityOwned() > 0) {
                    System.out.format("%-10s%-30s%10d%10d%10d%10d%10d%n",
                            itemClass,
                            item.name(),
                            quantities.getQuantityOwned(),
                            quantities.getQuantityUsed(),
                            quantities.getQuantityUnused(),
                            item.getAttack() * quantities.getQuantityUsed(),
                            item.getUpkeep() * quantities.getQuantityUsed()
                    );
                    totalDefense += (item.getDefense() * quantities.getQuantityUsed());
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityOwned());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalDefense+player.getDefenseStat(), totalUpkeep);
    }


    @Test
    public void testCalculateAttack() {
        Player player = createPlayer();
        Calculator calc = new Calculator();
        Map<InventoryItemClass, Map<InventoryItem, InventoryItemQuantities>> quantitiesByClass = calc.calculateInventoryItemUsageForAttack(player);
        System.out.format("%-10s%-30s%10s%10s%10s%10s%10s%n",
                "CLASS",
                "ITEM",
                "OWNED",
                "USED",
                "UNUSED",
                "ATTACK",
                "UPKEEP");
        System.out.println("------------------------------------------------------------------------------------------");
        int totalAttack = 0;
        int totalUpkeep = 0;
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            Map<InventoryItem, InventoryItemQuantities> quantitiesByType = quantitiesByClass.get(itemClass);
            for (InventoryItem item : itemClass.getInventoryItemsInClassSortedByAttack()) {
                InventoryItemQuantities quantities = quantitiesByType.get(item);
                if (quantities.getQuantityOwned() > 0) {
                    System.out.format("%-10s%-30s%10d%10d%10d%10d%10d%n",
                            itemClass,
                            item.name(),
                            quantities.getQuantityOwned(),
                            quantities.getQuantityUsed(),
                            quantities.getQuantityUnused(),
                            item.getAttack() * quantities.getQuantityUsed(),
                            item.getUpkeep() * quantities.getQuantityUsed()
                    );
                    totalAttack += (item.getAttack() * quantities.getQuantityUsed());
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityOwned());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalAttack+player.getAttackStat(), totalUpkeep);
    }
}
