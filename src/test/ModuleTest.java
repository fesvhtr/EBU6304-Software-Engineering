package test;

import entity.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ModuleTest {
    private Module module;

    @BeforeEach
    public void setup() {
        // 创建 Module 实例
        module = new Module("Module1", "Type1", "Spec1", "Description1");
    }


    @Test
    public void testGetName() {
        // 验证 getName 方法返回的 name 是否符合预期
        String expectedName = "Module1";
        Assertions.assertEquals(expectedName, module.getName());
    }

    @Test
    public void testGetType() {
        // 验证 getType 方法返回的 type 是否符合预期
        String expectedType = "Type1";
        Assertions.assertEquals(expectedType, module.getType());
    }

    @Test
    public void testGetDescription() {
        // 验证 getDescription 方法返回的 description 是否符合预期
        String expectedDescription = "Description1";
        Assertions.assertEquals(expectedDescription, module.getDescription());
    }

    @Test
    public void testSetName() {
        // 修改 name
        String newName = "NewModule";
        module.setName(newName);

        // 验证 getName 方法返回的 name 是否已被修改
        Assertions.assertEquals(newName, module.getName());
    }

    @Test
    public void testSetType() {
        // 修改 type
        String newType = "NewType";
        module.setType(newType);

        // 验证 getType 方法返回的 type 是否已被修改
        Assertions.assertEquals(newType, module.getType());
    }

    @Test
    public void testSetDescription() {
        // 修改 description
        String newDescription = "NewDescription";
        module.setDescription(newDescription);

        // 验证 getDescription 方法返回的 description 是否已被修改
        Assertions.assertEquals(newDescription, module.getDescription());
    }


}
