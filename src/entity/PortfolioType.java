package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for portfolios
 */
public class PortfolioType {
    private List<Type> types;

    private static PortfolioType singletonInstance;

    /**
     * Get the singleton instance of PortfolioType class.
     * @return The singleton instance of PortfolioType class.
     */
    public static PortfolioType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new PortfolioType();
        }
        return singletonInstance;
    }

    /**
     * Constructor of PortfolioType class.
     */
    private PortfolioType() {
        types = FileOperator.loadData("PortfolioTypes.json", Type.class);
    }

    /**
     * Add a portfolio type to the list.
     * @return
     */
    public List<Type> getTypes() {
        return types;
    }
}