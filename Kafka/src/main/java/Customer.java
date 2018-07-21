public class Customer {

    private String name;
    private String phone;
    private String email;
    private Address address;

    public Customer (String name, String phone, String email, Address address){
        this.setAddress(address);
        this.setEmail(email);
        this.setName(name);
        this.setPhone(phone);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Customer - Name: " + this.getName() + " Phone: " + this.getPhone()
                + " Email: " + this.getEmail() + " Address: " + this.getAddress();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }
}
