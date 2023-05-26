package test;

import entity.Portfolio;
import entity.PortfolioManager;
import entity.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import entity.Portfolio;
import entity.PortfolioManager;
import org.junit.Assert;
import org.junit.Before;


import java.util.List;

/**
 * Test for PortfolioManager class.
 */
public class PortfolioManagerTest {
    private PortfolioManager portfolioManager;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        portfolioManager = PortfolioManager.getInstance();
    }

    /**
     * Test for addItem and removeItem method.
     */
    @Test
    public void test() {
        Type poster = new Type("Poster");
        Portfolio portfolio = new Portfolio(poster, "club poster", "2023-05-14-04:49", "0.01 MB", "data/portfolio\\screenshot.png");

        portfolioManager.addItem(portfolio);

        List<Portfolio> portfolioList = portfolioManager.getList();
        Assert.assertEquals(1, portfolioList.size());
        Assert.assertEquals(portfolio, portfolioList.get(0));

        portfolioManager.removeItem(portfolio);

        portfolioList = portfolioManager.getList();
        Assert.assertEquals(0, portfolioList.size());
    }
}
