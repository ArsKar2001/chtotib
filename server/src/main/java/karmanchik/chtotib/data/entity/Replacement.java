package karmanchik.chtotib.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "replacement")
@EqualsAndHashCode(callSuper = true)
public class Replacement extends BaseEntity {
    @Column(name = "pair_number")
    private Integer pairNumber;

    @Column(name = "date_value")
    private LocalDate date;

    @Column(name = "auditorium", nullable = false)
    private String auditorium;

    @Column(name = "discipline", nullable = false)
    private String discipline;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Teacher> teachers;

    public Replacement() {

    }
}
