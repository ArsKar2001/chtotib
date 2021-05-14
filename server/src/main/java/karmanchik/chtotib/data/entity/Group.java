package karmanchik.chtotib.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    private List<ChatUser> chatUsers;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Replacement> replacements;

    public Group() { }
}
