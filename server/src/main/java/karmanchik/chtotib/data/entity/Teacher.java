package karmanchik.chtotib.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "teacher")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<ChatUser> chatUsers;

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "teachers")
    private List<Replacement> replacements;

    public Teacher() {

    }

}
