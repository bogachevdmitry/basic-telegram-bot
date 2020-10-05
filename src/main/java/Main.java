import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public class Main {
    public static void main(String[] args) {
        final String BOT_TOKEN = "BOT_TOKEN";

        TelegramBot bot = new TelegramBot(System.getenv(BOT_TOKEN));

        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                long chatId = update.message().chat().id();
                if (update.message().text().toLowerCase().contains("hi") ||
                        update.message().text().toLowerCase().contains("hello")) {
                    bot.execute(new SendMessage(
                            chatId,
                            String.format("Hello, %s!", update.message().chat().firstName())));
                } else {
                    bot.execute(new SendMessage(
                            chatId,
                            String.format("%s, how rude! You should begin with proper greetings.", update.message().chat().firstName())));
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
