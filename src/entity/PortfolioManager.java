package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for portfolios
 */
public class PortfolioManager {
    private List<Portfolio> portfolios;
    private static PortfolioManager singletonInstance;

    /**
     * Constructor of PortfolioManager class.
     */
    private PortfolioManager(){ portfolios = FileOperator.loadData("Portfolios.json", Portfolio.class); }

    /**
     * Get the singleton instance of PortfolioManager class.
     * @return The singleton instance of PortfolioManager class.
     */
    public static PortfolioManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new PortfolioManager();
        }
        return singletonInstance;
    }

    /**
     * Add a portfolio to the list.
     * @param portfolio The portfolio to be added.
     */
    public void addPortfolio(Portfolio portfolio){
        portfolios.add(portfolio);
        FileOperator.writeData(portfolios, "Portfolios.json");
    }

    /**
     * Delete a portfolio from the list.
     * @param portfolio The portfolio to be deleted.
     */
    public void delPortfolio(Portfolio portfolio){
        portfolios.remove(portfolio);
        FileOperator.writeData(portfolios, "Portfolios.json");
    }

    /**
     * Get the list of portfolios.
     * @return The list of portfolios.
     */
    public List<Portfolio> getPortfolios() {return portfolios;}

}
