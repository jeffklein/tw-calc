package org.jeffklein.tw.calc.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeffklein.tw.calc.*;
import org.testng.annotations.Test;

import java.util.Map;

@Test
//@ContextConfiguration(locations = "classpath:/com/guidewire/platform/plapp1/spring-beans-plapp1.xml")
public class CalculatorTest /*extends AbstractTestNGSpringContextTests*/ {

    private static final Log log = LogFactory.getLog(CalculatorTest.class);

    private Player createPlayer() {
        Player player = new Player();
        player.setMobCount(93677);
        player.setAttackStat(0);
        player.setDefenseStat(26);
        Inventory inventory = player.getInventory();
        inventory.setQuantityForType(InventoryItem.SHANK, 1);
        inventory.setQuantityForType(InventoryItem.SATURDAY_NIGHT_SPECIAL, 2);
        inventory.setQuantityForType(InventoryItem.GARROTE, 2165);
        inventory.setQuantityForType(InventoryItem.NINE_MM_HANDGUN, 0);
        inventory.setQuantityForType(InventoryItem.RIOT_SHIELD, 2215);
        inventory.setQuantityForType(InventoryItem.MOLOTOV_COCKTAIL, 5);
        inventory.setQuantityForType(InventoryItem.GALESI_MODEL_503, 0);
        inventory.setQuantityForType(InventoryItem.KEVLAR_VEST, 0);
        inventory.setQuantityForType(InventoryItem.BRASS_KNUCKLES, 0);
        inventory.setQuantityForType(InventoryItem.FIFTY_SEVEN_MAGNUM, 4);
        inventory.setQuantityForType(InventoryItem.MEAT_CLEAVER, 0);
        inventory.setQuantityForType(InventoryItem.GRENADE, 4);
        inventory.setQuantityForType(InventoryItem.AK47, 3);
        inventory.setQuantityForType(InventoryItem.GERMAN_STILLETTO_KNIFE, 3175);
        inventory.setQuantityForType(InventoryItem.POTATO_MASHER, 2105);
        inventory.setQuantityForType(InventoryItem.SAWED_OFF_SHOTGUN, 3450);
        inventory.setQuantityForType(InventoryItem.GLOCK_31, 2415);
        inventory.setQuantityForType(InventoryItem.XM400_MINIGUN, 0);
        inventory.setQuantityForType(InventoryItem.SLUGGER, 3485);
        inventory.setQuantityForType(InventoryItem.STEEL_TOED_SHOES, 84247);
        inventory.setQuantityForType(InventoryItem.RPG, 0);
        inventory.setQuantityForType(InventoryItem.BODY_ARMOR, 90037);
        inventory.setQuantityForType(InventoryItem.LUPARA, 1835);
        inventory.setQuantityForType(InventoryItem.MACHETE, 3475);
        inventory.setQuantityForType(InventoryItem.TOMMY_GUN, 0);
        inventory.setQuantityForType(InventoryItem.CHAINSAW, 2470);
        inventory.setQuantityForType(InventoryItem.THREE_THIRTY_EIGHT_LAPUA_RIFLE, 21512);
        inventory.setQuantityForType(InventoryItem.KEVLAR_LINED_SUIT, 3640);
        inventory.setQuantityForType(InventoryItem.AR15_ASSAULT_RIFLE, 6110);
        inventory.setQuantityForType(InventoryItem.BERETTA_MODELO_38A, 3230);
        inventory.setQuantityForType(InventoryItem.BAZOOKA, 3425);
        inventory.setQuantityForType(InventoryItem.BREN_GUN, 67100);
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
                            item.getUpkeep() * quantities.getQuantityOwned()
                    );
                    totalDefense += (item.getDefense() * quantities.getQuantityUsed());
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityOwned());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalDefense + player.getDefenseStat(), totalUpkeep);
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
                            item.getUpkeep() * quantities.getQuantityOwned()
                    );
                    totalAttack += (item.getAttack() * quantities.getQuantityUsed());
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityOwned());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalAttack + player.getAttackStat(), totalUpkeep);
    }
}
