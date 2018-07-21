import org.bson.Document;

import java.sql.Timestamp;

public class Status {

    private int orderNumber;
    private String status;
    private int store;
    private Timestamp currentStatusTs;

    public Status(int orderNumber, String status, int store, Timestamp currentStatusTs) {
        this.setCurrentStatusTs(currentStatusTs);
        this.setOrderNumber(orderNumber);
        this.setStatus(status);
        this.setStore(store);
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Status - orderNumber: " + this.getOrderNumber()
                + " Status: " + this.getStatus()
                + " Store: " + this.getStore()
                + " Status Ts: " + this.getCurrentStatusTs();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getStore() {
        return store;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCurrentStatusTs() {
        return currentStatusTs;
    }

    public void setCurrentStatusTs(Timestamp currentStatusTs) {
        this.currentStatusTs = currentStatusTs;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStore(int store) {
        this.store = store;
    }
    public Document getStatusAsDocument() {
        Document statusInfoDocument = new Document("_id", getOrderNumber())
                .append("orderNumber", getOrderNumber())
                .append("status", getStatus())
                .append("store", getStore())
                .append("currentStatusTs", getCurrentStatusTs());
        return statusInfoDocument;
    };

}
