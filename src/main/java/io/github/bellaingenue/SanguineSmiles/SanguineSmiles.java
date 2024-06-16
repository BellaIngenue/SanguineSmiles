package io.github.bellaingenue.SanguineSmiles;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class SanguineSmiles {

    private final Dotenv config;
    private final ShardManager  shardManager;

    public SanguineSmiles() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("BOT_TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("the clouds sway"));
        shardManager = builder.build();
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
