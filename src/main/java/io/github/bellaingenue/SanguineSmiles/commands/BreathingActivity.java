package io.github.bellaingenue.SanguineSmiles.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BreathingActivity extends ListenerAdapter {

    String[] gif = {"breathe1.gif","breathe2.gif", "breathe3.gif", "breathe4.gif","breathe5.gif", "breathe6.gif", "breathe7.gif","breathe8.gif"};


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("breathe")){
            Random rand = new Random();
            int n = rand.nextInt(gif.length-1);
            String random = gif[n];
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Breathing Exercise");
            embed.setDescription("Let's do a Breathing Exercise Together!!");
            embed.addField("Mindful Activity:",random, false);
            embed.setImage(String.valueOf(this.getClass().getResource(random)));
            embed.setThumbnail(String.valueOf(this.getClass().getResource("Logo.png")));
            embed.setColor(Color.white);
            embed.setFooter("Bot created by @bellaingenue");
            event.replyEmbeds(embed.build()).queue();
            embed.clear();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("breathe", "Use this Command to get a Breathing Exercise!"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
