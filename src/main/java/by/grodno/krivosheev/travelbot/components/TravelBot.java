package by.grodno.krivosheev.travelbot.components;

import by.grodno.krivosheev.travelbot.services.CityService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TravelBot extends TelegramLongPollingBot {
    @Value("${BOT_NAME}")
    private String botName;
    @Value("${BOT_TOKEN}")
    private String botToken;
    private final CityService cityService;

    public TravelBot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            String msgText = update.getMessage().getText();
            if (msgText.equals("/start")) {
                message.setText("Напиши город и узнай информацию о нём.");
            } else {
                message.setText(cityService.getInfo(msgText));
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
