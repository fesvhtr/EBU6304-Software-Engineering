package test;

import entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleTest {
    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role("Manager", "Managing team", "2021-01-01", "2022-01-01");
    }

    @Test
    public void testGetStartDate() {
        String startDate = role.getStartDate();
        Assertions.assertEquals("2021-01-01", startDate);
    }

    @Test
    public void testGetTitle() {
        String title = role.getTitle();
        Assertions.assertEquals("Manager", title);
    }

    @Test
    public void testGetEndDate() {
        String endDate = role.getEndDate();
        Assertions.assertEquals("2022-01-01", endDate);
    }

    @Test
    public void testGetDescription() {
        String description = role.getDescription();
        Assertions.assertEquals("Managing team", description);
    }

    @Test
    public void testSetTitle() {
        role.setTitle("Supervisor");
        String title = role.getTitle();
        Assertions.assertEquals("Supervisor", title);
    }

    @Test
    public void testSetStartDate() {
        role.setStartDate("2022-01-02");
        String startDate = role.getStartDate();
        Assertions.assertEquals("2022-01-02", startDate);
    }

    @Test
    public void testSetEndDate() {
        role.setEndDate("2023-01-01");
        String endDate = role.getEndDate();
        Assertions.assertEquals("2023-01-01", endDate);
    }

    @Test
    public void testSetDescription() {
        role.setDescription("Supervising team");
        String description = role.getDescription();
        Assertions.assertEquals("Supervising team", description);
    }
}
