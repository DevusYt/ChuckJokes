package de.devus;

import net.dv8tion.jda.api.EmbedBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Embeds {

    public static EmbedBuilder amountToHigh(int amount) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle("Your amount of " + amount + " is too high");
        builder.setFooter("The maximum is 10");

        return builder;
    }

    public static EmbedBuilder noCategoryFound(String category) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle("The Category " + category + " is non existent");
        builder.setFooter("Type -help to see all Categories");

        return builder;
    }

    public static EmbedBuilder amountUsage() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle("Use `-jokes {amount}`");

        return builder;
    }

    public EmbedBuilder helpEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Chuck Jokes");
        try {
            InputStream path = getClass().getClassLoader().getResourceAsStream("Chuck-Norris.jpg");
            BufferedImage img = ImageIO.read(path);
            builder.setColor(Color.decode(ImageDominantColor.getHexColor(img)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        builder.addField("Single Joke", "`-joke / -joke {category}`", true);
        builder.addBlankField(true);
        builder.addField("Multiple Jokes", "`-jokes {amount} / -jokes {amount} {category}`", true);
        builder.addField("All Categories:", "`-categories`", true);

        builder.setFooter("Thank you for using Chuck Jokes");

        return builder;
    }

}
