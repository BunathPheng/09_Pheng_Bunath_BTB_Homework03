package entity;
public class Volunteer extends StaffMember {
    private double salary;
    public Volunteer(int id, String name, String address , double salary) {
        super(id, name, address);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double pay() {
        return salary;
    }

    @Override
    public String toString() {
        return "Entity.Volunteer{" +
                "salary=" + salary +
                ", id =" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
