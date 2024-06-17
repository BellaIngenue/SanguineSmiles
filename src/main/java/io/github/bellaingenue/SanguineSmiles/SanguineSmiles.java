package io.github.bellaingenue.SanguineSmiles;

import io.github.bellaingenue.SanguineSmiles.commands.*;
import io.github.bellaingenue.SanguineSmiles.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;

public class SanguineSmiles {

    private final Dotenv config;
    private final ShardManager  shardManager;
    private final String [] activities={"the clouds sway", "your memories float by", "your happiest moments", "the trees move", ""};
    private int currentIndex = 0;

    public SanguineSmiles() throws LoginException {
        // Load Environment
        config = Dotenv.configure().load();
        String token = config.get("BOT_TOKEN");


        // Build Shard Manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        new Timer().schedule(new TimerTask(){
            public void run(){
                builder.setActivity(Activity.watching(activities[currentIndex]));
                currentIndex = (currentIndex+1)%activities.length;
            }
        },0,1800000);

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        shardManager = builder.build();

        // Register Listeners
        shardManager.addEventListener(
                new EventListener(),
                new CommandManager(),
                new Mindfulness(),
                new RandomQuote(),
                new BreathingActivity(),
                new UserInformation()
        );
    }

    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }


    public static void main(String[] arguments) throws Exception
    {
        try {
            SanguineSmiles bot = new SanguineSmiles();
        } catch (LoginException e){
            System.out.println("ERROR");
        }

    }

}
