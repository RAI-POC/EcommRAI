import org.bson.Document;

import java.sql.Timestamp;

public class Item {

    private int upc;
    private String description;
    private int qty;

    public Item (int upc, String itemDesc, int qty) {
        this.setItemDesc(itemDesc);
        this.setUpc(upc);
        this.setQty(qty);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Item - UPC: " + this.getUpc()
                + " Item Desc: " + this.getDescription()
                + " Quantity: " + this.getQty();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getUpc() {
        return upc;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setItemDesc(String itemDesc) {
        this.description = itemDesc;
    }

    public Document getItemInfoAsDocument() {
        Document itemInfoDocument = new Document("upc", getUpc())
                .append("itemdesc", getDescription())
                .append("qty", getQty());
        return itemInfoDocument;
    };

}
