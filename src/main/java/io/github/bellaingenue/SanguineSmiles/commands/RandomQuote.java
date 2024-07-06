package io.github.bellaingenue.SanguineSmiles.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomQuote extends ListenerAdapter {
    String[] quotesOld = {
            "Be yourself; everyone else is already taken.― Oscar Wilde",
            "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
            "Be the change that you wish to see in the world. ― Mahatma Gandhi",
            "If you tell the truth, you don't have to remember anything. ― Mark Twain",
            "If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.― J.K. Rowling",
            "To live is the rarest thing in the world. Most people exist, that is all.― Oscar Wilde",
            "Without music, life would be a mistake. ― Friedrich Nietzsche",
            "Always forgive your enemies, nothing annoys them so much. ― Oscar Wilde",
            "Life isn't about getting and having, it's about giving and being. –Kevin Kruse",
            "Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill",
            "Strive not to be a success, but rather to be of value. –Albert Einstein",
    };
    public static String getRandomQuote(String filePath) throws IOException {
        List<String> quotes = new ArrayList<>();

        // Read all lines from the text file
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Concatenate lines to form quotes
        StringBuilder quoteBuilder = new StringBuilder();
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                // Add the built quote to the list
                if (!quoteBuilder.isEmpty()) {
                    quotes.add(quoteBuilder.toString().trim());
                    quoteBuilder.setLength(0);
                }
            } else {
                // Append the line to the current quote
                quoteBuilder.append(line).append(" ");
            }
        }
        // Add the last quote if any
        if (!quoteBuilder.isEmpty()) {
            quotes.add(quoteBuilder.toString().trim());
        }

        // Check if quotes were added
        if (quotes.isEmpty()) {
            throw new IOException("No quotes found in the file.");
        }

        // Get a random quote
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("quote")){
            String randomQuote = null;
            try {
                randomQuote = getRandomQuote("quotes.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Happiness Quotes");
            embed.setDescription("Let's get a Random Happiness Quote!!");
            embed.addField("Happiness Quote: ",randomQuote, false);
            embed.setThumbnail("https://github.com/BellaIngenue/SanguineSmiles/blob/master/src/main/assets/quotes.gif");
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
        commandData.add(Commands.slash("quote", "Use this Command to get a Random Happiness Quote!"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}