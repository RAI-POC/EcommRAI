import org.bson.Document;

class Order {
    private int orderNumber;
    private String orderType;
    private int nodeId;
    private float orderTotal;

    public Order (int orderNumber, String orderType, int nodeId, float orderTotal) {
        this.setOrderNumber(orderNumber);
        this.setNodeId(nodeId);
        this.setOrderTotal(orderTotal);
        this.setOrderType(orderType);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "OrderInfo - orderNumber: " +this.getOrderNumber()
         + " Order Type: " + this.getOrderType()
                + " Node Id: " + this.getNodeId()
                + " Order Total: " + this.getOrderTotal();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Document getOrderInfoAsDocument() {
        Document orderInfoDocument = new Document("_id", getOrderNumber())
                .append("orderNumber", getOrderNumber())
                .append("orderType", getOrderType() )
                .append("store", getNodeId())
                .append("orderTotal", getOrderTotal());
        return orderInfoDocument;
    };
}
