package entity;

import util.FileOperator;

import java.util.List;

public class Manager {
    protected List<Object> list;
    protected String fileName;

    public Manager(String fileName, Class<?> c){
        list = FileOperator.loadData(fileName, c);
        this.fileName = fileName;
    }

    public void addItem(Object item){
        list.add(item);
        FileOperator.writeData(item, fileName);
    }

    public void removeItem(Object item){
        list.remove(item);
        FileOperator.writeData(list, fileName);
    }

    public List getList(){
        return this.list;
    }
}
