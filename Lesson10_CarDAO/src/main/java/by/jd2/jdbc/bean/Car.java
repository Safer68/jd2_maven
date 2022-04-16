package by.jd2.jdbc.bean;


import by.jd2.jdbc.annotation.MyColumn;
import by.jd2.jdbc.annotation.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MyTable("Car")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @MyColumn("id")
    private long id;
    @MyColumn("name")
    private String name;
    @MyColumn("color")
    private String color;
    @MyColumn("price")
    private double price;

}
