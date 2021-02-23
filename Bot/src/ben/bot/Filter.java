package ben.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Filter extends ListenerAdapter {

	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split("  ");
		String[] lists = { "do while loop" };
		String bad = e.getMessage().getContentRaw();
		for(int i =0; i<message.length;i++) {
			for(int j = 0; j < lists.length;j++) {
			if(message[i].equalsIgnoreCase(lists[j])) {
				e.getMessage().delete().queue();
				e.getChannel().sendMessage("i think you mean a while loop").queue();
			}
		}
	}
}
	
	
	
}
