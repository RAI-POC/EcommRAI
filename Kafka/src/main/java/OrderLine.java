public class OrderLine {
    private String item;
    private float price;
    private double qty;
    private String upc;
    private String description;
    private int olnumber;

    public OrderLine(int itemNbr, float price, double qty, String upc, String description, int orderLineNumber) {
        this.setDescription(description);
        this.setItemNbr(item);
        this.setPrice(price);
        this.setQty(qty);
        this.setUpc(upc);
        this.setOrderLineNumber(orderLineNumber);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "OrderLine - Item Number: " + this.getItemNbr()
                + " Price: " + this.getPrice()
                + " Quantity: " + this.getQty()
                + " UPC: " + this.getUpc()
                + " Description: " + this.getDescription()
                + " OrderLine Number: " + this.getOrderLineNumber();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public double getQty() {
        return qty;
    }

    public float getPrice() {
        return price;
    }

    public String getItemNbr() {
        return item;
    }

    public int getOrderLineNumber() {
        return olnumber;
    }

    public String getDescription() {
        return description;
    }

    public String getUpc() {
        return upc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemNbr(String itemNbr) {
        this.item = itemNbr;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.olnumber = orderLineNumber;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}
