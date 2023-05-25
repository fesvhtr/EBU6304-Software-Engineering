package test;

import entity.Skill;
import org.junit.Assert;
import org.junit.Test;

public class SkillTest {

    @Test
    public void testSkill() {
        String type = "Programming";
        String sourceType = "Online Course";
        String source = "Udemy";
        String description = "Learn Java programming";

        Skill skill = new Skill(type, sourceType, source, description);

        Assert.assertEquals(type, skill.getType());
        Assert.assertEquals(sourceType, skill.getSourceType());
        Assert.assertEquals(source, skill.getSource());
        Assert.assertEquals(description, skill.getDescription());

        // Modify skill attributes
        String newType = "Database";
        String newSourceType = "Book";
        String newSource = "SQL for Beginners";
        String newDescription = "Learn SQL basics";

        skill.setType(newType);
        skill.setSourceType(newSourceType);
        skill.setSource(newSource);
        skill.setDescription(newDescription);

        Assert.assertEquals(newType, skill.getType());
        Assert.assertEquals(newSourceType, skill.getSourceType());
        Assert.assertEquals(newSource, skill.getSource());
        Assert.assertEquals(newDescription, skill.getDescription());
    }
}
