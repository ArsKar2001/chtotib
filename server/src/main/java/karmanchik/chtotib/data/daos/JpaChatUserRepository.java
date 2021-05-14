package karmanchik.chtotib.data.daos;

import com.sun.istack.NotNull;
import karmanchik.chtotib.data.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface JpaChatUserRepository extends JpaRepository<ChatUser, Integer> {
    Optional<ChatUser> findByChatIdAndUserName(@NotNull Long chatId, @NotNull String userName);
}
