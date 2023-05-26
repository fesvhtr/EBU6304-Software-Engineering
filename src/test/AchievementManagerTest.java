package test;

import entity.AchievementManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for AchievementManager class.
 */
public class AchievementManagerTest {

    /**
     * Test for getInstance method.
     */
    @Test
    public void testGetInstance() {
        AchievementManager instance1 = AchievementManager.getInstance();
        AchievementManager instance2 = AchievementManager.getInstance();

        Assertions.assertSame(instance1, instance2);
    }

}
