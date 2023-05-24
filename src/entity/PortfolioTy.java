package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for portfolios
 */
public class PortfolioTy {
    private List<Type> types;

    private static PortfolioTy singletonInstance;

    /**
     * Get the singleton instance of PortfolioType class.
     * @return The singleton instance of PortfolioType class.
     */
    public static PortfolioTy getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new PortfolioTy();
        }
        return singletonInstance;
    }

    /**
     * Constructor of PortfolioType class.
     */
    private PortfolioTy() {
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