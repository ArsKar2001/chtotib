package karmanchik.chtotib.data.daos;

import com.sun.istack.NotNull;
import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface JpaGroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> getByName(@NotNull String groupName);

    @Query("SELECT g FROM Group g " +
            "WHERE :chatUser member of g.chatUsers")
    Optional<Group> findByChatUser(ChatUser chatUser);
}
