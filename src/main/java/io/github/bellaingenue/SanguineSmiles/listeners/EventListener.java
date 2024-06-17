package io.github.bellaingenue.SanguineSmiles.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();

        String message = user.getAsMention() + " reacted to message with " + emoji + " in channel " + channelMention + "!";
        DefaultGuildChannelUnion defaultChan = event.getGuild().getDefaultChannel();
        defaultChan.asTextChannel().sendMessage(message).queue();
    }
}
