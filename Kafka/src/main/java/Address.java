import org.apache.kafka.streams.StreamsConfig;

public class Address {
    private String country;
    private int zipcode;
    private String city;
    private String state;
    private String line2;
    private String line1;

    public Address (String country, int zipCode, String city, String state, String line1, String line2) {
        this.setCountry(country);
        this.setCity(city);
        this.setLine1(line1);
        this.setLine2(line2);
        this.setZipCode(zipCode);
        this.setState(state);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipcode = zipCode;
    }

    public int getZipCode() {
        return zipcode;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Address - Line1: " + this.getLine1() +
                " Line2: " + this.getLine2() + " City; " + this.getCity() +
                " State: " + this.getState() + " Country: " + this.getCountry() +
                " ZipCode: " + this.getZipCode();
    }
}
