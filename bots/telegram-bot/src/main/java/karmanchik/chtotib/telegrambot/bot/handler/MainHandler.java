package karmanchik.chtotib.telegrambot.bot.handler;

import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.telegrambot.bot.command.MainCommand;
import karmanchik.chtotib.data.exception.ResourceNotFoundException;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class MainHandler implements Handler {
    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(ChatUser chatUser, String message) throws ResourceNotFoundException {
        if (MainCommand.isCommand(message)) {
            return switch (Objects.requireNonNull(MainCommand.get(message))) {
                case COMMAND_1 -> getTimetableNextDay(chatUser);
                case COMMAND_2 -> getTimetableFull(chatUser);
                case COMMAND_3 -> getTimetableOther(chatUser);
                case COMMAND_4 -> editProfile(chatUser);
            };
        }
        return Collections.emptyList();
    }

    protected abstract List<PartialBotApiMethod<? extends Serializable>> editProfile(ChatUser chatUser);

    protected abstract List<PartialBotApiMethod<? extends Serializable>> getTimetableOther(ChatUser chatUser);

    protected abstract List<PartialBotApiMethod<? extends Serializable>> getTimetableFull(ChatUser chatUser);

    protected abstract List<PartialBotApiMethod<? extends Serializable>> getTimetableNextDay(ChatUser chatUser);
}
