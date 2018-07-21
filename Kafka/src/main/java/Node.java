public class Node {
    private int nodeid;
    private String country;

    public Node(int nodeID, String country) {
        this.setCountry(country);
        this.setNodeID(nodeID);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Node - nodeID: " + this.getNodeID()
                + " Country: " + this.getCountry();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNodeID() {
        return nodeid;
    }

    public void setNodeID(int nodeID) {
        this.nodeid = nodeID;
    }
}
