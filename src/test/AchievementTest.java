package test;

import entity.Achievement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AchievementTest {

    @Test
    public void testGettersAndSetters() {
        // 设置初始值
        String sourceType = "Type";
        String source = "Source";
        String description = "Description";

        // 创建 Achievement 对象
        Achievement achievement = new Achievement(sourceType, source, description);

        // 验证初始值是否正确
        Assertions.assertEquals(sourceType, achievement.getSourceType());
        Assertions.assertEquals(source, achievement.getSource());
        Assertions.assertEquals(description, achievement.getDescription());

        // 修改值并验证
        String newSourceType = "New Type";
        String newSource = "New Source";
        String newDescription = "New Description";

        achievement.setSourceType(newSourceType);
        achievement.setSource(newSource);
        achievement.setDescription(newDescription);

        Assertions.assertEquals(newSourceType, achievement.getSourceType());
        Assertions.assertEquals(newSource, achievement.getSource());
        Assertions.assertEquals(newDescription, achievement.getDescription());
    }
}
