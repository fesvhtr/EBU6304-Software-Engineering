package entity;

import constant.FileConstants;

/**
 * Manager for portfolios
 */
public class PortfolioManager extends Manager implements FileConstants {
    private static PortfolioManager singletonInstance;

    /**
     * Constructor of PortfolioManager class.
     */
    private PortfolioManager() {
        super(PORTFOLIO_FILE_NAME, Portfolio.class);
    }

    /**
     * Get the singleton instance of PortfolioManager class.
     *
     * @return The singleton instance of PortfolioManager class.
     */
    public static PortfolioManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new PortfolioManager();
        }
        return singletonInstance;
    }

}
