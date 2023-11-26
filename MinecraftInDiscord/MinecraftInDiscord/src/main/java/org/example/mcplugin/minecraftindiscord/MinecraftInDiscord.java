package org.example.mcplugin.minecraftindiscord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.block.data.type.Gate;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.mcplugin.minecraftindiscord.jda_listeners.MessageListener;
import org.example.mcplugin.minecraftindiscord.spigot_listeners.ChatListener;

import java.awt.*;

public final class MinecraftInDiscord extends JavaPlugin {

    public static long MC_CHANNEL_ID = 1114921521975148555L;
    //public static long GUILD_ID = 1114837203269451797;
    private static JDA jda;

    @Override
    public void onEnable() {
        loadconfig();
        try {

            jda = JDABuilder.createDefault(getConfig().getString("BOT-TOKEN"))
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.playing("Minecraft"))
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MessageListener())
                    .build().awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        sendStartEmbed();

    }

    @Override
    public void onDisable() {
        TextChannel channel = jda.getTextChannelById(getConfig().getString("CHANNEL-ID"));
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(30, 255, 0));
        embed.setTitle("De Minecraft server staat nu UIT!");
        channel.sendMessageEmbeds(embed.build()).queue();
    }

    private void loadconfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void sendStartEmbed() {
        TextChannel channel = jda.getTextChannelById(getConfig().getString("CHANNEL-ID"));
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(30, 255, 0));
        embed.setTitle("De Minecraft server staat nu AAN!");
        channel.sendMessageEmbeds(embed.build()).queue();
    }

    public static JDA getJda() {
        return jda;
    }

}
