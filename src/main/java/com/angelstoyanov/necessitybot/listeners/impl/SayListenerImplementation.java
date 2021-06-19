package com.angelstoyanov.necessitybot.listeners.impl;

import com.angelstoyanov.necessitybot.listeners.SayListener;
import org.apache.commons.lang3.StringUtils;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class SayListenerImplementation implements SayListener {

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(!messageCreateEvent.getMessage().getAuthor().isYourself()) {
            if (messageCreateEvent.getMessageContent().startsWith("!say")) {
                String t = messageCreateEvent.getMessageContent();
                Message m = messageCreateEvent.getMessage();
                m.delete();
                t = StringUtils.substringAfter(t, "!say").trim();
                messageCreateEvent.getChannel().sendMessage(t);
            }
        }
    }
}
