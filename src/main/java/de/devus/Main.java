package de.devus;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    public static JDA shardMan;
    public static JDABuilder builder;

    public static final String prefix = "-";

    public static void main(String[] args) throws LoginException, IOException {

        builder = JDABuilder.createDefault("TOKEN");

        builder.setActivity(Activity.playing("Missing in Action"));

        builder.addEventListeners(new Command());

        shardMan = builder.build();

        System.out.println("[Chuck Jokes] Ready for Duty!");
    }

    /*
    Prefix: -
    Commands:
    -help: Sends Embed with all Categories in a Selection Menu -> When selected send joke from this cat.
    -joke: Sends a Random Joke
    -joke {categroy}: Sends Joke from the Category
    -jokes {count} {category}:
    -jokes {count}:
     */

}
