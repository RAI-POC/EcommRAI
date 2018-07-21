import java.util.List;

public class FulfillmentOrder {
    private int number;
    private List<OrderLine> orderline;
    private Node node;

    public FulfillmentOrder(int foNumber, List<OrderLine> orderLines, Node node) {
        this.setFoNumber(foNumber);
        this.setNode(node);
        this.setOrderLines(orderLines);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Fulfillment Order - Fulfillment Number: " + this.getFoNumber()
                + " orderLines: " + this.getOrderLines()
                + " node: " + this.getNode() ;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int getFoNumber() {
        return this.number;
    }

    public List<OrderLine> getOrderLines() {
        return orderline;
    }

    public Node getNode() {
        return node;
    }

    public void setFoNumber(int foNumber) {
        this.number = foNumber;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderline = orderLines;
    }
}
