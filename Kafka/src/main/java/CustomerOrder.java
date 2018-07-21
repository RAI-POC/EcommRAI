import java.sql.Timestamp;

public class CustomerOrder {
    private int ordernumber;
    private Customer customer;
    private String ordertype;
    private FulfillmentOrder fulfillmentorder;
    private float ordertotal;
    private Timestamp statustime;

    public CustomerOrder(int custOrder, Customer customer, String orderType
            , FulfillmentOrder fulfillmentOrder, float orderTotal, Timestamp orderPlacedTs) {
         this.setCustomer(customer);
         this.setCustOrder(custOrder);
         this.setFulfillmentOrder(fulfillmentOrder);
         this.setOrderPlacedTs(orderPlacedTs);
         this.setOrderTotal(orderTotal);
         this.setOrderType(orderType);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Customer Order - OrderNumber: " + this.getCustOrder()
                + " Customer: " + this.getCustomer()
                + " Order Type: " + this.getOrderType()
                + " FulfillmentOrder: " + this.getFulfillmentOrder()
                + " Order Total: " + this.getOrderTotal()
                + " Order Placed Timestamp: " + this.getOrderPlacedTs();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Customer getCustomer() {
        return customer;
    }

    public float getOrderTotal() {
        return ordertotal;
    }

    public FulfillmentOrder getFulfillmentOrder() {
        return fulfillmentorder;
    }

    public int getCustOrder() {
        return ordernumber;
    }

    public String getOrderType() {
        return ordertype;
    }

    public Timestamp getOrderPlacedTs() {
        return statustime;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustOrder(int custOrder) {
        this.ordernumber = custOrder;
    }

    public void setFulfillmentOrder(FulfillmentOrder fulfillmentOrder) {
        this.fulfillmentorder = fulfillmentOrder;
    }

    public void setOrderPlacedTs(Timestamp orderPlacedTs) {
        this.statustime = orderPlacedTs;
    }

    public void setOrderTotal(float orderTotal) {
        this.ordertotal = orderTotal;
    }

    public void setOrderType(String orderType) {
        this.ordertype = orderType;
    }
}
