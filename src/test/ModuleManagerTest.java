package test;

import entity.Module;
import entity.ModuleManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModuleManagerTest {
    private ModuleManager moduleManager;
    private Module module1;
    private Module module2;

    @BeforeEach
    public void setup() {
        // 创建 ModuleManager 实例
        moduleManager = ModuleManager.getInstance();

        // 创建两个 Module 实例
        module1 = new Module("Module1", "Type1", "Spec1", "Description1");
        module2 = new Module("Module2", "Type2", "Spec2", "Description2");
    }

    @Test
    public void testAddItem() {
        // 添加 module1
        moduleManager.addItem(module1);

        // 验证 ModuleManager 的列表中是否包含 module1
        List<Object> itemList = moduleManager.getList();
        Assertions.assertTrue(itemList.contains(module1));
    }

    @Test
    public void testRemoveItem() {
        // 添加 module1 和 module2
        moduleManager.addItem(module1);
        moduleManager.addItem(module2);

        // 移除 module1
        moduleManager.removeItem(module1);

        // 验证 ModuleManager 的列表中是否不再包含 module1
        List<Object> itemList = moduleManager.getList();
        Assertions.assertFalse(itemList.contains(module1));
    }


}

