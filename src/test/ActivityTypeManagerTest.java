package test;

import entity.ActivityTypeManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityTypeManagerTest {

    @Test
    public void testGetInstance() {
        // 获取 ActivityTypeManager 的实例
        ActivityTypeManager instance1 = ActivityTypeManager.getInstance();
        ActivityTypeManager instance2 = ActivityTypeManager.getInstance();

        // 验证两个实例是否相同
        Assertions.assertSame(instance1, instance2);
    }


}
