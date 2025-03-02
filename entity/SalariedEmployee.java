package entity;

public class SalariedEmployee extends StaffMember {
    private  double salary;
    private  double bonus;
    public SalariedEmployee(int id ,String name, String address , double salary , double bonus){
        super(id , name , address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public double pay() {
        return salary + bonus;
    }

    @Override
    public String toString() {
        return "Entity.SalariedEmployee{" +
                "salary=" + salary +
                ", bonus=" + bonus +
                ", id =" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
