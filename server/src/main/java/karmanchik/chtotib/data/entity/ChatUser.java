package karmanchik.chtotib.data.entity;

import karmanchik.chtotib.data.enums.BotState;
import karmanchik.chtotib.data.enums.Role;
import karmanchik.chtotib.data.enums.UserState;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "chat_users")
@EqualsAndHashCode(callSuper = true)
public class ChatUser extends BaseEntity {
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "bot_state_id")
    private BotState botState;

    @Column(name = "user_state_id")
    private UserState userState;

    @Column(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public ChatUser() {}
}
