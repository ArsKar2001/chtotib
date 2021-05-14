package karmanchik.chtotib.telegrambot.bot.helper;

import karmanchik.chtotib.data.daos.JpaChatUserRepository;
import karmanchik.chtotib.data.daos.JpaGroupRepository;
import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.data.enums.Role;
import karmanchik.chtotib.data.enums.WeekType;
import karmanchik.chtotib.telegrambot.bot.Const;
import karmanchik.chtotib.telegrambot.bot.command.MainCommand;
import karmanchik.chtotib.telegrambot.util.TelegramUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Log4j2
@Component
@RequiredArgsConstructor
public class Helper {
    private final JpaGroupRepository groupRepository;
    private final JpaChatUserRepository userRepository;

    public static PartialBotApiMethod<? extends Serializable> selectRole(ChatUser chatUser) {
        return TelegramUtil.createMessageTemplate(chatUser)
                .text("Кто ты?")
                .replyMarkup(TelegramUtil.createReplyKeyboardMarkup()
                        .keyboardRow(TelegramUtil.createKeyboardRow(List.of(
                                Const.ROLE_STUDENT,
                                Const.ROLE_TEACHER).toArray()))
                        .oneTimeKeyboard(true)
                        .build())
                .build();
    }

    public static PartialBotApiMethod<? extends Serializable> inputMessage(ChatUser chatUser, String text) {
        return TelegramUtil.createMessageTemplate(chatUser)
                .text(text).build();
    }

    public static PartialBotApiMethod<? extends Serializable> mainMessage(ChatUser chatUser) {
        ReplyKeyboardMarkup markup = TelegramUtil.createReplyKeyboardMarkup().build();
        LocalDate nextSchoolDate = DateHelper.getNextSchoolDate();
        String weekType = DateHelper.getWeekType().equals(WeekType.DOWN) ? "нижняя" : "верхняя";
        String name = nextSchoolDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"));
        Role role = chatUser.getRole();


        markup.setKeyboard(List.of(
                TelegramUtil.createKeyboardRow(MainCommand.vals())));
        return role.equals(Role.STUDENT) ?
                TelegramUtil.createMessageTemplate(chatUser)
                        .text("1.\tРасписание на " + "<b>" + nextSchoolDate.format(DateTimeFormatter.ofPattern("dd MMMM", Helper.getLocale())) + "</b>" + " (" + name + ")\n" +
                                "2.\tРасписание на эту неделю (" + weekType + ")\n" +
                                "3.\tУзнать расписание педагога\n" +
                                "4.\tИзменить анкету")
                        .replyMarkup(markup).build() :
                TelegramUtil.createMessageTemplate(chatUser)
                        .text("1.\tРасписание на " + "<b>" + nextSchoolDate.format(DateTimeFormatter.ofPattern("dd MMMM", Helper.getLocale())) + "</b>" + " (" + name + ")\n" +
                                "2.\tРасписание на эту неделю (" + weekType + ")\n" +
                                "3.\tУзнать расписание группы\n" +
                                "4.\tИзменить анкету")
                        .replyMarkup(markup).build();
    }

    private static Locale getLocale() {
        return Locale.forLanguageTag("ru");
    }
}
