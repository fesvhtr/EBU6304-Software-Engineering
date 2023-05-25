package test;

import entity.AchievementManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AchievementManagerTest {

    @Test
    public void testGetInstance() {
        AchievementManager instance1 = AchievementManager.getInstance();
        AchievementManager instance2 = AchievementManager.getInstance();

        // 验证两个实例是同一个实例
        Assertions.assertSame(instance1, instance2);
    }

}
