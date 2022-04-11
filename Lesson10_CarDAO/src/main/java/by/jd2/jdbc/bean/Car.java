package by.jd2.jdbc.bean;


import by.jd2.jdbc.annotation.MyColumn;
import by.jd2.jdbc.annotation.MyTable;
import lombok.Data;
import lombok.ToString;

@MyTable("Car")
@ToString
@Data
public class Car {
    @MyColumn("id")
    private long id;
    @MyColumn("name")
    private String name;
    @MyColumn("color")
    private String color;
    @MyColumn("price")
    private double price;

    public Car() {
    }

    public Car(long id, String name, String color, double price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }
}
