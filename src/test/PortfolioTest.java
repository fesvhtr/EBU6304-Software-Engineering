package test;

import entity.Portfolio;
import entity.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Portfolio class.
 */
public class PortfolioTest {
    private Type type;
    private Portfolio portfolio;

    /**
     * Set up test fixture.
     */
    @BeforeEach
    public void setup() {
        type = new Type("Type1");
        portfolio = new Portfolio(type, "Title", "2023-05-25", "10MB", "/path/to/file");
    }

    /**
     * test for getter and setter.
     */
    @Test
    public void test() {
        Assertions.assertEquals(type, portfolio.getType());
        Assertions.assertEquals("Title", portfolio.getTitle());
        Assertions.assertEquals("2023-05-25", portfolio.getUploadDate());
        Assertions.assertEquals("10MB", portfolio.getSize());
        Assertions.assertEquals("/path/to/file", portfolio.getStoreFilePath());
        portfolio.setTitle("New Title");
        portfolio.setUploadDate("2023-05-26");
        portfolio.setSize("20MB");
        portfolio.setStoreFilePath("/new/path/to/file");
        Assertions.assertEquals("New Title", portfolio.getTitle());
        Assertions.assertEquals("2023-05-26", portfolio.getUploadDate());
        Assertions.assertEquals("20MB", portfolio.getSize());
        Assertions.assertEquals("/new/path/to/file", portfolio.getStoreFilePath());
    }

}
