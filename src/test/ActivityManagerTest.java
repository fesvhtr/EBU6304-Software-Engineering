package test;

import entity.ActivityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityManagerTest {

    @Test
    public void testGetInstance() {
        // 获取 ActivityManager 的实例
        ActivityManager instance1 = ActivityManager.getInstance();
        ActivityManager instance2 = ActivityManager.getInstance();

        // 验证两个实例是否相同
        Assertions.assertSame(instance1, instance2);
    }

}
