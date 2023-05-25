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

    @BeforeEach
    public void setup() {
        // 创建 ModuleTypeManager 实例
        moduleTypeManager = ModuleTypeManager.getInstance();

        // 创建两个 Type 实例
        type1 = new Type("Type1");
        type2 = new Type("Type2");
    }

    @Test
    public void testAddItem() {
        // 添加 type1
        moduleTypeManager.addItem(type1);

        // 验证 ModuleTypeManager 的列表中是否包含 type1
        List<Object> itemList = moduleTypeManager.getList();
        Assertions.assertTrue(itemList.contains(type1));
    }

    @Test
    public void testRemoveItem() {
        // 添加 type1 和 type2
        moduleTypeManager.addItem(type1);
        moduleTypeManager.addItem(type2);

        // 移除 type1
        moduleTypeManager.removeItem(type1);

        // 验证 ModuleTypeManager 的列表中是否不再包含 type1
        List<Object> itemList = moduleTypeManager.getList();
        Assertions.assertFalse(itemList.contains(type1));
    }


}
