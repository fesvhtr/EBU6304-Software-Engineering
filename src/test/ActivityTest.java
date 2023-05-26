package test;

import entity.Activity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for Activity class.
 */
public class ActivityTest {
    /**
     * Test for getters and setters.
     */
    @Test
    public void testGettersAndSetters() {
        String title = "AI";
        String role = "intern";
        String startDate = "2022-01-01";
        String endDate = "2022-02-01";
        String type = "Research Project";
        Activity activity = new Activity(title, role, startDate, endDate, type);

        Assertions.assertEquals(title, activity.getTitle());
        Assertions.assertEquals(role, activity.getRole());
        Assertions.assertEquals(startDate, activity.getStartDate());
        Assertions.assertEquals(endDate, activity.getEndDate());
        Assertions.assertEquals(type, activity.getType());

        String newTitle = "Hospital";
        String newRole = "Volenteer";
        String newStartDate = "2022-03-01";
        String newEndDate = "2022-04-01";
        String newType = "Volunteering";

        activity.setTitle(newTitle);
        activity.setRole(newRole);
        activity.setStartDate(newStartDate);
        activity.setEndDate(newEndDate);
        activity.setType(newType);

        Assertions.assertEquals(newTitle, activity.getTitle());
        Assertions.assertEquals(newRole, activity.getRole());
        Assertions.assertEquals(newStartDate, activity.getStartDate());
        Assertions.assertEquals(newEndDate, activity.getEndDate());
        Assertions.assertEquals(newType, activity.getType());
    }

}
