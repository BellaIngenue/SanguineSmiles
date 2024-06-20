package io.github.bellaingenue.SanguineSmiles.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mindfulness extends ListenerAdapter{

    String[] exercise = {"Explore a fruit: Use Observe and Describe to explore an orange. What are the visual features, what are the visual features, what does it feel like, and what does it smell like?",
            "Create with Play-Doh: Study the Play-Doh container, noticing the writing, colors and design elements. Slowly peel the lid off and notice the smell. What comes to mind?",
            "Connect with a Sound: Put on music; Listen to the sound of waves, nature or another soothing sound; or pick up on the naturally occuring sounds around you",
            "Mindful Eating: Strive to eat Mindfully. Notice your food, seeing the shape, colors and textures. Smell the aromas. Take it all in before experiencing your first small bite",
            "Mindfulness apps: Search your smartphone, tablet, or computer for free or inexpensive mindfulness apps. Practice each one you flnd several times, and share them with your friends and family",
            "Breathing colors: Choose two different colors, one to breathe in and one to breathe out",
            "Body scan: Use Observe and Describe to scan your body from top to bottom, noting areas of tension and discomfort. Gently dismiss judgments that arise, and take a deep breath into each area of the body where this discomfort exists"};


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("mindfulness")){
            Random rand = new Random();
            int n = rand.nextInt(exercise.length-1);
            String random = exercise[n];
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Mindful Activity");
            embed.setDescription("Let's do a Mindful Activity Together!!");
            embed.addField("Mindful Activity:",random, false);
            embed.setImage(String.valueOf(this.getClass().getResource("mindful.gif")));
            embed.setThumbnail("https://github.com/BellaIngenue/SanguineSmiles/blob/master/Logo.png");
            embed.setColor(Color.white);
            embed.setFooter("Bot created by @bellaingenue");
            event.replyEmbeds(embed.build()).queue();
            embed.clear();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("mindfulness", "Use this Command to get a Random Mindful Activity!"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
