package de.goldendeveloper.recraftet.discord;

import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import de.goldendeveloper.recraftet.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;

public class Events extends ListenerAdapter {

    @Override
    public void onShutdown(@NotNull ShutdownEvent e) {
        if (Main.getDeployment()) {
            WebhookEmbedBuilder embed = new WebhookEmbedBuilder();
            embed.setAuthor(new WebhookEmbed.EmbedAuthor(Main.getDiscord().getBot().getSelfUser().getName(), Main.getDiscord().getBot().getSelfUser().getAvatarUrl(), "https://Golden-Developer.de"));
            embed.addField(new WebhookEmbed.EmbedField(false, "[Status]", "Offline"));
            embed.addField(new WebhookEmbed.EmbedField(false, "Gestoppt als", Main.getDiscord().getBot().getSelfUser().getName()));
            embed.addField(new WebhookEmbed.EmbedField(false, "Server", Integer.toString(Main.getDiscord().getBot().getGuilds().size())));
            embed.addField(new WebhookEmbed.EmbedField(false, "Status", "\uD83D\uDD34 Offline"));
            embed.addField(new WebhookEmbed.EmbedField(false, "Version", Main.getDiscord().getProjektVersion()));
            embed.setFooter(new WebhookEmbed.EmbedFooter("@Golden-Developer", Main.getDiscord().getBot().getSelfUser().getAvatarUrl()));
            embed.setTimestamp(new Date().toInstant());
            embed.setColor(0xFF0000);
            new WebhookClientBuilder(Main.getConfig().getDiscordWebhook()).build().send(embed.build()).thenRun(() -> System.exit(0));
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        User _Coho04_ = e.getJDA().getUserById("513306244371447828");
        User zRazzer = e.getJDA().getUserById("428811057700536331");
        String cmd = e.getName();
        if (e.getName().equalsIgnoreCase(Discord.cmdShutdown)) {
            if (e.getUser() == zRazzer || e.getUser() == _Coho04_) {
                e.getInteraction().reply("Der Bot wird nun heruntergefahren").queue();
                e.getJDA().shutdown();
            } else {
                e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
            }
        } else if (e.getName().equalsIgnoreCase(Discord.cmdRestart)) {
            if (e.getUser() == zRazzer || e.getUser() == _Coho04_) {
                try {
                    e.getInteraction().reply("Der Discord Bot wird nun neugestartet!").queue();
                    Process p = Runtime.getRuntime().exec("screen -AmdS " + Main.getDiscord().getProjektName() + " java -Xms1096M -Xmx1096M -jar " + Main.getDiscord().getProjektName() + "-" + Main.getDiscord().getProjektVersion() + ".jar restart");
                    p.waitFor();
                    e.getJDA().shutdown();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
            }
        } else if (cmd.equalsIgnoreCase(Discord.cmdHelp)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("**Help Commands**");
            embed.setColor(Color.MAGENTA);
            for (Command cm : Main.getDiscord().getBot().retrieveCommands().complete()) {
                embed.addField("/" + cm.getName(), cm.getDescription(), true);
            }
            embed.setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());
            e.getInteraction().replyEmbeds(embed.build()).addActionRow(
                    Button.link("https://wiki.Golden-Developer.de/", "Online Übersicht"),
                    Button.link("https://support.Golden-Developer.de", "Support Anfragen")
            ).queue();
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.isFromGuild()) {
            Guild guild = e.getGuild();
            if (guild.getId().equalsIgnoreCase(Main.GuildID)) {
                Member member = e.getMember();
                Role role = reactionRole(e.getMessageId(), e.getGuild(), e.getReaction());
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
                Role role = reactionRole(e.getMessageId(), e.getGuild(), e.getReaction());
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

    public static Role reactionRole(String messageId, Guild guild, MessageReaction emote) {
        Role role = null;
        if (messageId.equalsIgnoreCase("947201275827740672")) {
            switch (emote.getEmoji().getAsReactionCode()) {
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
            switch (emote.getEmoji().getAsReactionCode()) {
                case "\uD83D\uDC40" -> role = guild.getRoleById("784334914379841536");
                case "\uD83D\uDC65" -> role = guild.getRoleById("784339056712220702");
                case "\uD83D\uDD25" -> role = guild.getRoleById("784339132096315402");
            }
        } else if (messageId.equalsIgnoreCase("947208245443981412")) {
            if (emote.getEmoji().getAsReactionCode().equalsIgnoreCase("✅")) {
                role = guild.getRoleById("783639878461030440");
            }
        }
        return role;
    }
}
