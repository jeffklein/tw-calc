package org.jeffklein.tw.calc.test;

import org.jeffklein.tw.calc.Calculator;
import org.jeffklein.tw.calc.Inventory;
import org.jeffklein.tw.calc.InventoryItem;
import org.jeffklein.tw.calc.InventoryItemClass;
import org.jeffklein.tw.calc.Player;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.List;

@Test
//@ContextConfiguration(locations = "classpath:/com/guidewire/platform/plapp1/spring-beans-plapp1.xml")
public class InventoryTest /*extends AbstractTestNGSpringContextTests*/ {

    private static final Log log = LogFactory.getLog(InventoryTest.class);

//  @Autowired(required = true)
//  @Qualifier("plApp1")
//  private Inventory inventory = new Inventory();

    @Test
    public void testInventoryItemsSortedByAttack() {
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            List<InventoryItem> itemsByAttack = itemClass.getInventoryItemsInClassSortedByAttack();
            for (int i = 0 ; i < itemsByAttack.size() ; i++) {
                InventoryItem item = itemsByAttack.get(i);
                log.info(item.getInventoryItemClass().name()+" "+item.name()+" "+item.getAttack()+" "+item.getUpkeep());
                if (i+1 < itemsByAttack.size()) {
                    Assert.assertTrue(item.getAttack() >= itemsByAttack.get(i+1).getAttack());
                }
            }
        }
    }

    @Test
    public void testInventoryItemsSortedByDefense() {
        for (InventoryItemClass itemClass : InventoryItemClass.values()) {
            List<InventoryItem> itemsByDefense = itemClass.getInventoryItemsInClassSortedByDefense();
            for (int i = 0 ; i < itemsByDefense.size() ; i++) {
                InventoryItem item = itemsByDefense.get(i);
                log.info(item.getInventoryItemClass().name() + " " + item.name() + " " + item.getDefense() + " " + item.getUpkeep());
                if (i+1 < itemsByDefense.size()) {
                    Assert.assertTrue(item.getDefense() >= itemsByDefense.get(i+1).getDefense());
                }
            }
        }
    }
}
