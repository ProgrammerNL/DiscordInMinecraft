package org.example.mcplugin.minecraftindiscord.jda_listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.example.mcplugin.minecraftindiscord.MinecraftInDiscord;
import org.jetbrains.annotations.NotNull;
public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getChannel().equals(MinecraftInDiscord.getJda().getTextChannelById(MinecraftInDiscord.MC_CHANNEL_ID))) {
            if(!event.getMember().getUser().isBot()) {
                String username = event.getMember().getEffectiveName();
                String message = event.getMessage().getContentRaw();
                //TextChannel channel = jda.getTextChannelById(getConfig().getString("CHANNEL-ID"));

                if(event.getMessage().getContentRaw().equalsIgnoreCase("!test")){
                    if(event.getChannel().canTalk() == true){
                        Bukkit.broadcastMessage(ChatColor.RED + "DISCORD: " + username + " heeft het command " + message + " gebruikt!");
                    } else {
                        Bukkit.broadcastMessage(ChatColor.RED + "DISCORD: " + username + " heeft het command " + message + " gebruikt, maar heeft niet de juiste rechten.");
                    }
                } else {
                    Bukkit.broadcastMessage(ChatColor.BLUE + "DISCORD: " + username + " zegt " + message);
                }
            }
        }
    }
}
