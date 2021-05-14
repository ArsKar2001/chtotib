package karmanchik.chtotib.data.daos;


import com.sun.istack.NotNull;
import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface JpaTeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> getByName(@NotNull String name);

    @Query("SELECT g FROM Teacher g " +
            "WHERE :chatUser member of g.chatUsers")
    Optional<Teacher> findByChatUser(ChatUser chatUser);
}
