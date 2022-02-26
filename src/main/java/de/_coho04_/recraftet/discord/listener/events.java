package de._coho04_.recraftet.discord.listener;

import de._coho04_.recraftet.discord.Main;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class events extends ListenerAdapter {

/*    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        if (e.getGuild().getId().equalsIgnoreCase(Main.GuildID)) {
            TextChannel channel = e.getGuild().getTextChannelById(Main.WelcomeChannelID);
            if (channel != null) {
                MessageEmbed embed = new EmbedBuilder().setImage(e.getMember().getAvatarUrl()).build();
                channel.sendMessageEmbeds(embed).queue();
            }
        }
    }*/

/*    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.isFromGuild()) {
            if (e.getGuild().getId().equalsIgnoreCase(Main.GuildID)) {
                String cmd = e.getName();
                switch (cmd) {
                    case Main.cmdTestEmbeds -> {
                        e.getChannel().sendMessageEmbeds(new EmbedBuilder().setImage(e.getUser().getAvatarUrl()).build()).queue();
                    }
                }
            }
        }
    }*/

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.isFromGuild()) {
            Guild guild = e.getGuild();
            if (guild.getId().equalsIgnoreCase(Main.GuildID)) {
                Member member = e.getMember();
                Role role = reactionRole(e.getMessageId(), e.getGuild(), e.getReactionEmote());
                if (e.getChannel().getId().equalsIgnoreCase(Main.ReactionChannelID)) {
                    if (role != null) {
                        if (member != null) {
                            guild.addRoleToMember(member, role).queue();
                        }
                    }
                } else if (e.getChannel().getId().equalsIgnoreCase(Main.RulesChannelID)) {
                    if (role != null) {
                        if (member != null) {
                            guild.addRoleToMember(member, role).queue();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent e) {
        if (e.isFromGuild()) {
            Guild guild = e.getGuild();
            if (guild.getId().equalsIgnoreCase(Main.GuildID)) {
                Member member = e.getMember();
                Role role = reactionRole(e.getMessageId(), e.getGuild(), e.getReactionEmote());
                if (e.getChannel().getId().equalsIgnoreCase(Main.ReactionChannelID)) {
                    if (role != null) {
                        if (member != null) {
                            guild.removeRoleFromMember(member, role).queue();
                        }
                    }
                } else if (e.getChannel().getId().equalsIgnoreCase(Main.RulesChannelID)) {
                    if (role != null) {
                        if (member != null) {
                            guild.removeRoleFromMember(member, role).queue();
                        }
                    }
                }
            }
        }
    }

    public static Role reactionRole(String messageId, Guild guild, MessageReaction.ReactionEmote emote) {
        Role role = null;
        if (messageId.equalsIgnoreCase("947201275827740672")) {
            switch (emote.getAsReactionCode()) {
                case "\uD83D\uDC25" -> role = guild.getRoleById("783636915613204500");
                case "\uD83E\uDE90" -> role = guild.getRoleById("783636958010146827");
                case "\uD83C\uDFC0" -> role = guild.getRoleById("783636982961537025");
                case "\uD83C\uDF43" -> role = guild.getRoleById("783637086619697193");
                case "☘" -> role = guild.getRoleById("783637146404782080");
                case "\uD83D\uDC38" -> role = guild.getRoleById("783637198905933854");
                case "\uD83D\uDD2E" -> role = guild.getRoleById("783637375476957194");
                case "\uD83D\uDC19" -> role = guild.getRoleById("783637433765199892");
                case "\uD83E\uDD8B" -> role = guild.getRoleById("783637560990892052");
                case "\uD83D\uDC3B\u200D" -> role = guild.getRoleById("783637688473092106");
                case "\uD83D\uDC30" -> role = guild.getRoleById("783637721481871371");
                case "\uD83E\uDD87" -> role = guild.getRoleById("783639210347331604");
                case "\uD83D\uDD77️" -> role = guild.getRoleById("783637952080379924");
            }
        } else if (messageId.equalsIgnoreCase("947202239552958485")) {
            switch (emote.getAsReactionCode()) {
                case "\uD83D\uDC40" -> role = guild.getRoleById("784334914379841536");
                case "\uD83D\uDC65" -> role = guild.getRoleById("784339056712220702");
                case "\uD83D\uDD25" -> role = guild.getRoleById("784339132096315402");
            }
        } else if (messageId.equalsIgnoreCase("947208245443981412")) {
            if (emote.getAsReactionCode().equalsIgnoreCase("✅")) {
                role = guild.getRoleById("783639878461030440");
            }
        }
        return role;
    }
}
