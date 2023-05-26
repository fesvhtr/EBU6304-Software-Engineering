package test;

import entity.ActivityTypeManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for ActivityTypeManager class.
 */
public class ActivityTypeManagerTest {
    /**
     * Test for getInstance method.
     */
    @Test
    public void testGetInstance() {
        ActivityTypeManager instance1 = ActivityTypeManager.getInstance();
        ActivityTypeManager instance2 = ActivityTypeManager.getInstance();

        Assertions.assertSame(instance1, instance2);
    }


}
