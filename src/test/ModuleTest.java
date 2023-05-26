package test;

import entity.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Module class.
 */
public class ModuleTest {
    private Module module;

    /**
     * Set up test fixture.
     */
    @BeforeEach
    public void setup() {
        module = new Module("Module1", "Type1", "Spec1", "Description1");
    }

    /**
     * Test for getters and setters.
     */
    @Test
    public void test() {
        String expectedName = "Module1";
        Assertions.assertEquals(expectedName, module.getName());
        String expectedType = "Type1";
        Assertions.assertEquals(expectedType, module.getType());
        String expectedDescription = "Description1";
        Assertions.assertEquals(expectedDescription, module.getDescription());
        String newName = "NewModule";
        module.setName(newName);
        Assertions.assertEquals(newName, module.getName());
        String newType = "NewType";
        module.setType(newType);
        Assertions.assertEquals(newType, module.getType());
        String newDescription = "NewDescription";
        module.setDescription(newDescription);
        Assertions.assertEquals(newDescription, module.getDescription());
    }

}
