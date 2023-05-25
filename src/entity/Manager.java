package entity;

import util.FileOperator;

import java.util.List;

/**
 * Manager for items
 */
public class Manager {
    protected List<Object> list;
    protected String fileName;

    /**
     * Constructor of Manager class.
     * @param fileName Name of the file.
     * @param c Class of the item.
     */
    public Manager(String fileName, Class<?> c)
    {
        list = FileOperator.loadData(fileName, c);
        this.fileName = fileName;
    }

    /**
     * Add an item to the list.
     * @param item Item to be added.
     */
    public void addItem(Object item){
        list.add(item);
        FileOperator.writeData(item, fileName);
    }

    /**
     * Remove an item from the list.
     * @param item Item to be removed.
     */
    public void removeItem(Object item){
        list.remove(item);
        FileOperator.writeData(list, fileName);
    }

    /**
     * Get the list of items.
     * @return The list of items.
     */
    public List getList(){
        return this.list;
    }
}
