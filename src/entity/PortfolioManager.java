package entity;

import util.FileOperator;

import java.util.List;

public class PortfolioManager {
    private List<Portfolio> portfolios;
    private static PortfolioManager singletonInstance;

    private PortfolioManager(){ portfolios = FileOperator.loadData("Portfolios.json", Portfolio.class); }

    public static PortfolioManager getInstance(){
        if(singletonInstance == null){
            singletonInstance = new PortfolioManager();
        }
        return singletonInstance;
    }

    public void addPortfolio(Portfolio portfolio){
        portfolios.add(portfolio);
        FileOperator.writeData(portfolios, "Portfolios.json");
    }

    public void delPortfolio(Portfolio portfolio){
        portfolios.remove(portfolio);
        FileOperator.writeData(portfolios, "Portfolios.json");
    }

    public List<Portfolio> getPortfolios() {return portfolios;}

}
