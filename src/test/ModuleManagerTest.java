package test;

import entity.Module;
import entity.ModuleManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test for ModuleManager class.
 */
public class ModuleManagerTest {
    private ModuleManager moduleManager;
    private Module module1;
    private Module module2;

    /**
     * Set up test fixture.
     */
    @BeforeEach
    public void setup() {
        moduleManager = ModuleManager.getInstance();

        module1 = new Module("Module1", "Type1", "Spec1", "Description1");
        module2 = new Module("Module2", "Type2", "Spec2", "Description2");
    }

    /**
     * Test for addItem method.
     */
    @Test
    public void test() {
        moduleManager.addItem(module1);

        List<Object> itemList = moduleManager.getList();

        Assertions.assertTrue(itemList.contains(module1));

        moduleManager.addItem(module2);

        moduleManager.removeItem(module1);

        itemList = moduleManager.getList();

        Assertions.assertFalse(itemList.contains(module1));
    }

}

