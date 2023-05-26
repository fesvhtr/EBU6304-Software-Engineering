package test;

import entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Role class.
 */
public class RoleTest {
    private Role role;

    /**
     * Set up test fixture.
     */
    @BeforeEach
    public void setUp() {
        role = new Role("Manager", "Managing team", "2021-01-01", "2022-01-01");
    }

    /**
     * Test for getters and setters.
     */
    @Test
    public void testGetStartDate() {
        String startDate = role.getStartDate();
        Assertions.assertEquals("2021-01-01", startDate);
        String title = role.getTitle();
        Assertions.assertEquals("Manager", title);
        String endDate = role.getEndDate();
        Assertions.assertEquals("2022-01-01", endDate);
        String description = role.getDescription();
        Assertions.assertEquals("Managing team", description);
        role.setTitle("Supervisor");
        title = role.getTitle();
        Assertions.assertEquals("Supervisor", title);
        role.setStartDate("2022-01-02");
        startDate = role.getStartDate();
        Assertions.assertEquals("2022-01-02", startDate);
        role.setEndDate("2023-01-01");
        endDate = role.getEndDate();
        Assertions.assertEquals("2023-01-01", endDate);
        role.setDescription("Supervising team");
        description = role.getDescription();
        Assertions.assertEquals("Supervising team", description);
    }
}
