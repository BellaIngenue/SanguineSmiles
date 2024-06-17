package io.github.bellaingenue.SanguineSmiles;

import io.github.bellaingenue.SanguineSmiles.commands.CommandManager;
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

public class SanguineSmiles {

    private final Dotenv config;
    private final ShardManager  shardManager;

    public SanguineSmiles() throws LoginException {
        // Load Environment
        config = Dotenv.configure().load();
        String token = config.get("BOT_TOKEN");

        // Build Shard Manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("the clouds sway"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        shardManager = builder.build();

        // Register Listeners
        shardManager.addEventListener(new EventListener(), new CommandManager());
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
