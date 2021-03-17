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
    @Value("${bot_name}")
    private String botName;
    @Value("${bot_name}")
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
            // Return info
            message.setText(cityService.getInfo(update.getMessage().getText()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}