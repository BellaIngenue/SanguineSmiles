package io.github.bellaingenue.SanguineSmiles.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFact extends ListenerAdapter {


    public static String randomFactoid() throws IOException {
        FileInputStream facts = new FileInputStream("facts.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(facts));
        ArrayList<String> array = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null)
            array.add(line);
        Random rand = new Random();
        int randomIndex = rand.nextInt(array.size());
        return array.get(randomIndex);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("fact")){
            EmbedBuilder embed = new EmbedBuilder();
            String randomFact = null;
            try {
                randomFact = RandomFact.randomFactoid();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            embed.setTitle("Random Fact");
            embed.setDescription("Let's get a Random Fact!");
            embed.addField("Random Fact: ", randomFact, false);
            embed.setImage(String.valueOf(this.getClass().getResource("facts.gif")));
            embed.setThumbnail("https://github.com/BellaIngenue/SanguineSmiles/blob/master/Logo.png");
            embed.setColor(Color.gray);
            embed.setFooter("Bot created by @bellaingenue");
            event.replyEmbeds(embed.build()).queue();
            embed.clear();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("fact", "Use this Command to get a Random Fact!"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
