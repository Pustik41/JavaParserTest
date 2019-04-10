class User {
    String name;
    int age;
    Srting phoneNumber;
    String city;
    String streed;
    int houseNumber;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, Srting phoneNumber, String city, String streed, int houseNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.streed = streed;
        this.houseNumber = houseNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Srting getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreed() {
        return streed;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}