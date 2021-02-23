package ben.bot;

import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		String fo = "fo";	
		if(args[0].equalsIgnoreCase(Main.prefix + fo.toLowerCase())) {
				EmbedBuilder info = new EmbedBuilder();
				info.setTitle("(☞ﾟヮﾟ)☞TA Bot 9000 description☜(ﾟヮﾟ☜)");
				info.setDescription("Still making it will update when it works what i want it to do");
				info.setColor(0x343deb);
				info.setFooter("created by Ben", event.getMember().getUser().getAvatarUrl());
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(info.build()).queue();
				info.clear();
		}
			else if(args[0].equalsIgnoreCase(Main.prefix + "clears")) {
				if(args.length < 2 ) {
					event.getChannel().sendMessage("too many arguments").queue();
					
				}
				else {
					try {
						List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
						event.getChannel().deleteMessages(messages).queue();
					}
					catch(IllegalArgumentException e) {
						if(e.toString().startsWith("java.lan.IllegalARgumentException")) {
							event.getChannel().sendMessage("wrong").queue();
						}
					}
					
					
				}
			}
			String messagesent = event.getMessage().getContentRaw();
			if(messagesent.equalsIgnoreCase(Main.prefix + "dropbox link")) {
				event.getChannel().sendMessage("https://dropbox.cse.sc.edu/login/index.php").queue();
			}
			
			
	
	
	
	
	
	
	
}
}
