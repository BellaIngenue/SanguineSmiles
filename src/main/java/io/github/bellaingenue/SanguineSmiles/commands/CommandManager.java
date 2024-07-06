package io.github.bellaingenue.SanguineSmiles.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("help")){
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("SanguineSmiles Help Desk");
            embed.setDescription("Get Help and Resources, and Learn all about my Commands!!");
            embed.addField("Basic Information", "Use the Slash Commands to get information and use commands!!", false);
            embed.addField("Mindful Practices", "Use the Mindfulness Command to get a Random Mindfulness Exercise!", false);
            embed.addField("Breathing Exercises", "Use the Breathe Command to get a Random Breathing Exercise!", false);
            embed.addField("Random Quote", "Use the Fact Command to get a Random Fact of the Day!", false);
            embed.addField("Random Happy Quote", "Use the Quote Command to get a Random Quote!", false);
            // embed.setImage("https://github.com/BellaIngenue/SanguineSmiles/blob/master/src/main/assets/Logo_Large.png");
            // embed.setThumbnail("https://github.com/BellaIngenue/SanguineSmiles/blob/master/src/main/assets/Logo.png");
            embed.setImage("Logo_Large.png");
            embed.setThumbnail("https://github.com/BellaIngenue/SanguineSmiles/blob/master/src/main/assets/Logo.png");
            embed.setColor(Color.lightGray);
            embed.setFooter("Bot created by @bellaingenue");
            event.replyEmbeds(embed.build()).queue();
            embed.clear();

        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("help", "Use this Command to get Help and Resources!!"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
/*
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("help", "Get Help and Resources!!"));
        event.getJDA().updateCommands().addCommands(commandData).queue();

    }
*/

}
