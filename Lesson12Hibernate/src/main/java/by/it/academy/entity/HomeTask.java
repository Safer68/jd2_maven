package by.it.academy.entity;

import by.it.academy.embed.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@PrimaryKeyJoinColumn(name = "TaskId")
public class HomeTask extends Task {
    @Column
    private String startDate;
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "LOKKING_NAME")),
            @AttributeOverride(name = "surname", column = @Column(name = "LOKKING_SURNAME"))
    })
    @Embedded
    private Person looking;
    @Embedded
    private Person executor;
}
