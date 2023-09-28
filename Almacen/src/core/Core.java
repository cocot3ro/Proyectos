package core;

import java.util.Map;

public final class Core {
    private final Inventory INVENTORY;
    private final DataBase DATABASE;
    private final Printer PRINTER;

    public Core() throws Exception {
        this.DATABASE = new DataBase();
        this.INVENTORY = new Inventory(DATABASE.getInventory());
        reloadInventory();
        this.PRINTER = new Printer();
    }

    public void reloadInventory() throws Exception {
        INVENTORY.loadInventory(DATABASE.getInventory());
    }

    public Map<String, Integer> getInventory() throws Exception {
        reloadInventory();
        return INVENTORY.getINVENTORY();
    }

    public void updateInventory(Map<String, Integer> newInventory) throws Exception {
        newInventory.replaceAll((k, v) -> Math.max(0, v));
        DATABASE.updateInventory(newInventory);
        reloadInventory();
    }

    public void traer(Map<String, Integer> newInventory) throws Exception {
        Map<String, Integer> currentInventory = getInventory();
        for (Map.Entry<String, Integer> entry : newInventory.entrySet()) {
            if (entry.getValue() > currentInventory.get(entry.getKey())) {
                newInventory.put(entry.getKey(), currentInventory.get(entry.getKey()));
            }
        }

        DATABASE.traer(newInventory);
        reloadInventory();
    }

    public void anhadir(Map<String, Integer> newInventory) throws Exception {
        newInventory.replaceAll((k, v) -> (Math.max(0, v)));
        DATABASE.anhadir(newInventory);
        reloadInventory();
    }

    // TODO
    public void print() {
    }

    public Map<String, Integer> getLimites() {
        return this.INVENTORY.getLimites();
    }

    public void setLimites(Map<String, Integer> newLimits) throws Exception {
        newLimits.replaceAll((k, v) -> Math.max(0, v));
        DATABASE.setLimites(newLimits);
        INVENTORY.setLimites(newLimits);
    }
}
