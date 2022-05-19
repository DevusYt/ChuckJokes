package de.devus;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Command extends ListenerAdapter {

    private static final String[] categories = new String[] {
            "animal",
            "career",
            "celebrity",
            "dev",
            "explicit",
            "fashion",
            "food",
            "history",
            "money",
            "movie",
            "music",
            "political",
            "religion",
            "science",
            "sport",
            "travel"
    };

    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentStripped().split(" ");
        String message = event.getMessage().getContentStripped();
        TextChannel channel = event.getTextChannel();

        if(Objects.requireNonNull(event.getMember()).getUser().isBot()) {
            return;
        }

        if(message.startsWith(Main.prefix + "joke")) {

            if(Objects.equals(args[0], Main.prefix + "jokes")) {
                if(args.length >= 3) {
                    // Amount + Category

                    int amount;
                    try {
                        amount = Integer.parseInt(args[1]);
                    } catch (NumberFormatException exc) {
                        amount = 0;
                        channel.sendMessageEmbeds(Embeds.amountUsage().build()).queue();
                    }

                    if(amountToHigh(amount)) {
                        channel.sendMessageEmbeds(Embeds.amountToHigh(amount).build()).queue();
                        return;
                    }

                    if(!Arrays.toString(categories).contains(args[2].toLowerCase())) {
                        channel.sendMessageEmbeds(Embeds.noCategoryFound(args[2]).build()).queue();
                        return;
                    }

                    for (int i = 0; i < amount; i++) {
                        try {
                            channel.sendMessage(HttpRequests.multipleJokesWithCategory(amount, args[2].toLowerCase()).get(i)).queue();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (args.length == 2) {
                    // Amout
                    int amount;
                    try {
                        amount = Integer.parseInt(args[1]);
                    } catch (NumberFormatException exc) {
                        //TODO: Add embed
                        amount = 0;
                        channel.sendMessageEmbeds(Embeds.amountUsage().build()).queue();
                    }

                    if(amountToHigh(amount)) {
                        channel.sendMessageEmbeds(Embeds.amountToHigh(amount).build()).queue();
                        return;
                    }

                    for (int i = 0; i < amount; i++) {
                        try {
                            channel.sendMessage(HttpRequests.multipleJokesNoCategory(amount).get(i)).queue();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    // Random Joke
                    try {
                        channel.sendMessage(HttpRequests.randomJokeNoCategory()).queue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                if(args.length == 1) {
                    // Random Joke
                    try {
                        channel.sendMessage(HttpRequests.randomJokeNoCategory()).queue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Joke with Category

                    if(Arrays.toString(categories).contains(args[1].toLowerCase())) {
                        try {
                            channel.sendMessage(HttpRequests.singleJokeWithCategory(args[1].toLowerCase())).queue();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        channel.sendMessageEmbeds(Embeds.noCategoryFound(args[1]).build()).queue();
                    }
                }
            }

            return;
        }

        if(event.getMessage().getContentStripped().startsWith(Main.prefix + "help")) {
            Embeds embeds = new Embeds();

            channel.sendMessageEmbeds(embeds.helpEmbed().build()).queue();
            return;
        }

        if(event.getMessage().getContentStripped().startsWith(Main.prefix+ "categories")) {
            StringBuilder stringBuilder = new StringBuilder();

            for (String category : categories) {
                stringBuilder.append(category.toUpperCase()).append("\n");
            }

            channel.sendMessage("All Categories: " + stringBuilder.toString()).complete();
        }

        if(event.getMessage().getContentStripped().startsWith(Main.prefix + "debug")) {
            if (event.getMember().getId().equals("695629580014321747")) {
                for(Guild guild : Main.shardMan.getGuilds()) {
                    System.out.println(guild.getName());
                }
            }
        }

    }

    private static boolean amountToHigh(int amount) {
        return amount >= 11;
    }

}
