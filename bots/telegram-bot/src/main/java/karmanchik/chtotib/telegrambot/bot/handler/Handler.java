package karmanchik.chtotib.telegrambot.bot.handler;

import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.data.enums.BotState;
import karmanchik.chtotib.data.enums.Role;
import karmanchik.chtotib.data.enums.UserState;
import karmanchik.chtotib.data.exception.ResourceNotFoundException;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.Serializable;
import java.util.List;

public interface Handler {
    List<PartialBotApiMethod<? extends Serializable>> handle(ChatUser chatUser, String message) throws ResourceNotFoundException;

    BotState operatedBotState();

    List<Role> operatedUserRoles();

    List<UserState> operatedUserSate();
}
