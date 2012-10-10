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

//  @Autowired(required = true)
//  @Qualifier("plApp1")
//  private Inventory inventory = new Inventory();


    private Player createPlayer() {
        Player player = new Player();
        player.setMobCount(54900);
        player.setAttackStat(11);
        player.setDefenseStat(11);
        Inventory inventory = player.getInventory();
        inventory.setQuantityForType(InventoryItem.SHANK, 1);
        inventory.setQuantityForType(InventoryItem.SATURDAY_NIGHT_SPECIAL, 2);
        inventory.setQuantityForType(InventoryItem.GARROTE, 430);
        inventory.setQuantityForType(InventoryItem.RIOT_SHIELD, 670);
        inventory.setQuantityForType(InventoryItem.KEVLAR_VEST, 500);
        inventory.setQuantityForType(InventoryItem.BRASS_KNUCKLES, 690);
        inventory.setQuantityForType(InventoryItem.GERMAN_STILLETTO_KNIFE, 415);
        inventory.setQuantityForType(InventoryItem.POTATO_MASHER, 345);
        inventory.setQuantityForType(InventoryItem.SAWED_OFF_SHOTGUN, 510);
        inventory.setQuantityForType(InventoryItem.GLOCK_31, 805);
        inventory.setQuantityForType(InventoryItem.SLUGGER, 295);
        inventory.setQuantityForType(InventoryItem.STEEL_TOED_SHOES, 53510);
        inventory.setQuantityForType(InventoryItem.BODY_ARMOR, 54550);
        inventory.setQuantityForType(InventoryItem.LUPARA, 275);
        inventory.setQuantityForType(InventoryItem.MACHETE, 320);
        inventory.setQuantityForType(InventoryItem.TOMMY_GUN, 20000);
        inventory.setQuantityForType(InventoryItem.CHAINSAW, 775);
        inventory.setQuantityForType(InventoryItem.THREE_THIRTY_EIGHT_LAPUA_RIFLE, 53425);
        inventory.setQuantityForType(InventoryItem.KEVLAR_LINED_SUIT, 350);
        inventory.setQuantityForType(InventoryItem.AR15_ASSAULT_RIFLE, 445);
        inventory.setQuantityForType(InventoryItem.BERETTA_MODELO_38A, 180);
        inventory.setQuantityForType(InventoryItem.BAZOOKA, 250);
        inventory.setQuantityForType(InventoryItem.BREN_GUN, 1020);
        return player;
    }

    @Test
    public void testCalcuateAttack() {
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
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityUsed());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalAttack+player.getAttackStat(), totalUpkeep);
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
                    totalUpkeep += (item.getUpkeep() * quantities.getQuantityUsed());
                    totalDefense += (item.getDefense() * quantities.getQuantityUsed());
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.format("%70s%10d%10d%n", "TOTAL:", totalDefense+player.getDefenseStat(), totalUpkeep);
    }
}
