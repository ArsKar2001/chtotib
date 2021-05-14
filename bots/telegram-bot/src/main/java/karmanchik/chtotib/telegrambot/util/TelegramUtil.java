package karmanchik.chtotib.telegrambot.util;

import karmanchik.chtotib.data.entity.ChatUser;
import karmanchik.chtotib.data.models.BaseModel;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramUtil {
    public static SendMessageBuilder createMessageTemplate(ChatUser chatUser) {
        return SendMessage.builder()
                .chatId(chatUser.getChatId().toString())
                .parseMode(ParseMode.HTML);
    }

    public static ReplyKeyboardMarkupBuilder createReplyKeyboardMarkup() {
        return ReplyKeyboardMarkup.builder()
                .resizeKeyboard(true)
                .selective(false)
                .oneTimeKeyboard(false);
    }

    public static KeyboardRow createKeyboardRow(Object[] values) {
        KeyboardRow row = new KeyboardRow();
        for (Object o : values) {
            row.add(o.toString());
        }
        return row;
    }

    public static List<List<InlineKeyboardButton>> createInlineKeyboardButtons(List<? extends BaseModel> models, Integer countButtonInRow) {
        List<List<InlineKeyboardButton>> listList = new ArrayList<>();
        List<InlineKeyboardButton> buttonsLine = new ArrayList<>();

        models.stream()
                .map(model -> TelegramUtil.createInlineKeyboardButton(model.getName(), model.getId().toString()).build())
                .forEach(button -> {
                    buttonsLine.add(button);
                    if (buttonsLine.size() % countButtonInRow == 0) {
                        listList.add(new ArrayList<>(buttonsLine));
                        buttonsLine.clear();
                    }
                });
        listList.add(buttonsLine);
        return listList;
    }

    private static InlineKeyboardButton.InlineKeyboardButtonBuilder createInlineKeyboardButton(String text, String callback) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callback);
    }
}
