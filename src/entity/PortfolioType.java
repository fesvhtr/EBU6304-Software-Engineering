package entity;

import util.FileOperator;

import java.util.List;


public class PortfolioType {
    private List<Type> types;

    private static PortfolioType singletonInstance;

    // 实现单例模式：只有一个DeviceType被创建
    public static PortfolioType getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new PortfolioType();
        }
        return singletonInstance;
    }

    private PortfolioType() {
        types = FileOperator.loadData("PortfolioTypes.json", Type.class);
    }

    public List<Type> getTypes() {
        return types;
    }
}