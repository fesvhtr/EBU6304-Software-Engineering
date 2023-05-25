package test;

import entity.Portfolio;
import entity.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortfolioTest {
    private Type type;
    private Portfolio portfolio;

    @BeforeEach
    public void setup() {
        // 创建 Type 实例
        type = new Type("Type1");

        // 创建 Portfolio 实例
        portfolio = new Portfolio(type, "Title", "2023-05-25", "10MB", "/path/to/file");
    }

    @Test
    public void testGetters() {
        // 验证 getter 方法返回正确的值
        Assertions.assertEquals(type, portfolio.getType());
        Assertions.assertEquals("Title", portfolio.getTitle());
        Assertions.assertEquals("2023-05-25", portfolio.getUploadDate());
        Assertions.assertEquals("10MB", portfolio.getSize());
        Assertions.assertEquals("/path/to/file", portfolio.getStoreFilePath());
    }

    @Test
    public void testSetters() {
        // 修改属性值
        portfolio.setTitle("New Title");
        portfolio.setUploadDate("2023-05-26");
        portfolio.setSize("20MB");
        portfolio.setStoreFilePath("/new/path/to/file");

        // 验证 setter 方法是否正确设置属性值
        Assertions.assertEquals("New Title", portfolio.getTitle());
        Assertions.assertEquals("2023-05-26", portfolio.getUploadDate());
        Assertions.assertEquals("20MB", portfolio.getSize());
        Assertions.assertEquals("/new/path/to/file", portfolio.getStoreFilePath());
    }


}
