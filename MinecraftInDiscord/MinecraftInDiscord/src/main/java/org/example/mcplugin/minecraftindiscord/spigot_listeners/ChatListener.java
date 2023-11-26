package org.example.mcplugin.minecraftindiscord.spigot_listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.example.mcplugin.minecraftindiscord.MinecraftInDiscord;

import java.awt.*;
import java.util.Random;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        EmbedBuilder embed = new EmbedBuilder();
        Player player = e.getPlayer();
        if(player.isOp()){
            if(e.getMessage().contains("!testmcside")){
                embed.setColor(Color.BLUE);
                embed.setAuthor(player.getName());
                embed.setDescription(player.getName() + " heeft zojuist het command " + e.getMessage() + " gebruikt!");
                TextChannel channel = MinecraftInDiscord.getJda().getTextChannelById(MinecraftInDiscord.MC_CHANNEL_ID);
                channel.sendMessageEmbeds(embed.build()).queue();
            } else {
                Random random = new Random();
                float r = random.nextFloat();
                float g = random.nextFloat();
                float b = random.nextFloat();
                Color randomColor = new Color(r, g, b);

                embed.setColor(randomColor);
                embed.setAuthor(player.getName(), null, "https://crafatar.com/avatars/uuid/2d0cefc3-3522-4b2b-bb7f-6f12c0fb8975" + player.getUniqueId());
                embed.setDescription(e.getMessage());

                TextChannel channel = MinecraftInDiscord.getJda().getTextChannelById(MinecraftInDiscord.MC_CHANNEL_ID);
                channel.sendMessageEmbeds(embed.build()).queue();
            }
        } else {
            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color randomColor = new Color(r, g, b);

            embed.setColor(randomColor);
            embed.setAuthor(player.getName(), null, "https://crafatar.com/avatars/uuid/2d0cefc3-3522-4b2b-bb7f-6f12c0fb8975" + player.getUniqueId());
            embed.setDescription(e.getMessage());

            TextChannel channel = MinecraftInDiscord.getJda().getTextChannelById(MinecraftInDiscord.MC_CHANNEL_ID);
            channel.sendMessageEmbeds(embed.build()).queue();
        }
    }
}
