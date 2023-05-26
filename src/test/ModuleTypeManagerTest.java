package test;

import entity.ModuleTypeManager;
import entity.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModuleTypeManagerTest {
    private ModuleTypeManager moduleTypeManager;
    private Type type1;
    private Type type2;

    /**
     * Set up test fixture.
     */
    @BeforeEach
    public void setup() {
        moduleTypeManager = ModuleTypeManager.getInstance();

        type1 = new Type("Type1");
        type2 = new Type("Type2");
    }

    /**
     * Test for addItem and removeItem method.
     */
    @Test
    public void test() {
        moduleTypeManager.addItem(type1);
        List<Object> itemList = moduleTypeManager.getList();
        Assertions.assertTrue(itemList.contains(type1));
        moduleTypeManager.addItem(type2);
        moduleTypeManager.removeItem(type1);
        itemList = moduleTypeManager.getList();
        Assertions.assertFalse(itemList.contains(type1));
    }

}
