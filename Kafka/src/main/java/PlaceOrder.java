public class PlaceOrder {
    private CustomerOrder customerorder;

    public PlaceOrder (CustomerOrder customerOrder) {
        this.setCustomerOrder(customerOrder);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Place Order: " + this.getCustomerOrder().toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public CustomerOrder getCustomerOrder() {
        return customerorder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerorder = customerOrder;
    }
}
