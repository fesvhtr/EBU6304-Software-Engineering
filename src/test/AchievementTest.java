package test;

import entity.Achievement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for Achievement class.
 */
public class AchievementTest {
    /**
     * Test for getters and setters.
     */
    @Test
    public void testGettersAndSetters() {
        String sourceType = "Type";
        String source = "Source";
        String description = "Description";

        Achievement achievement = new Achievement(sourceType, source, description);

        Assertions.assertEquals(sourceType, achievement.getSourceType());
        Assertions.assertEquals(source, achievement.getSource());
        Assertions.assertEquals(description, achievement.getDescription());

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
