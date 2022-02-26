package de._coho04_.recraftet.discord;

import de._coho04_.recraftet.discord.listener.events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA bot;
    public static String BotToken ="OTQ3MTU3MTg1NDY3NjYyMzQ3.YhpK3g.L5j05-uHeoCIl6HTAnqyR_W8yAc";
    public static String GuildID ="923646470706565140";
    public static String WelcomeChannelID ="783384633911541771";
    public static String ReactionChannelID ="923647336528363620";
    public static String RulesChannelID ="783428555069587456";

    public static void main(String[] args) {
        Bot(BotToken);
    }

    public static void Bot(String BotToken) {
        try {
            bot = JDABuilder.createDefault(BotToken)
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ROLE_TAGS, CacheFlag.EMOTE, CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS)
                    .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_EMOJIS,
                            GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_INVITES, GatewayIntent.DIRECT_MESSAGE_TYPING,
                            GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.GUILD_WEBHOOKS, GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGE_TYPING)
                    .addEventListeners(
                            new events()
                    )
                    .setAutoReconnect(true)
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
