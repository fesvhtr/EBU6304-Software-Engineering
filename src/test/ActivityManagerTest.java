package test;

import entity.ActivityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for ActivityManager class.
 */
public class ActivityManagerTest {
    /**
     * Test for getInstance method.
     */
    @Test
    public void testGetInstance() {
        ActivityManager instance1 = ActivityManager.getInstance();
        ActivityManager instance2 = ActivityManager.getInstance();

        Assertions.assertSame(instance1, instance2);
    }

}
