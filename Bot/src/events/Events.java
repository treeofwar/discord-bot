package events;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Events extends ListenerAdapter {
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		String user = event.getMember().getAsMention();
		JDA client = event.getJDA();
		 List<TextChannel> channels = client.getTextChannelsByName("General", true);
			for(TextChannel ch : channels) {
				ch.sendMessage("welcome im your TA's bot please contact him about getting a role " + user).queue();
				
			}
			event.getGuild().modifyMemberRoles(event.getMember(), event.getGuild().getRolesByName("second gen kids", true)).queue();
		
		
	}

}
