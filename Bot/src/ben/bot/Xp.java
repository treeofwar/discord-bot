package ben.bot;

import java.util.HashMap;


import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Xp extends ListenerAdapter {
	HashMap<Member, Integer> playerXp = new HashMap<>();
	HashMap<Member, Integer> playerTimer = new HashMap<>();
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
	      for(Member member : event.getChannel().getMembers()) {
	            if(!playerXp.containsKey(member))playerXp.put(member, 20);
	        }
	     
	    int newpoint = getplayerXp(event.getMember()) + 10;
		setPlayerXp(event.getMember(), newpoint);
	  
		String[] args = event.getMessage().getContentRaw().split(" ");
		if(args[0].equalsIgnoreCase(Main.prefix + "xp")) {
			event.getChannel().sendMessage("you have " + getplayerXp(event.getMember()) + " xp").queue();
		
		if(canGetXp(event.getMember())) {
			randXp(event.getMember());
			setPlayerTime(event.getMember(), 60);
			}
		}
		else if(args[0].equalsIgnoreCase(Main.prefix + "dj")) {
			if(getplayerXp(event.getMember()) >= 100) {
				event.getChannel().sendMessage("you have reached level 1 so you gained access to a new role dj").queue();
				//int newpoints = getplayerXp(event.getMember()) - 20;
				//setPlayerXp(event.getMember(), newpoints);
				 event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(746555681535950919L)).complete();
				
			}
		}
		else if(getplayerXp(event.getMember()) == 100) {
			event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(747193813738455181L)).complete();
			EmbedBuilder lvl1 = new EmbedBuilder();
			lvl1.setTitle("(☞ﾟヮﾟ)☞LEVEL  1  REACHED ☜(ﾟヮﾟ☜)");
			lvl1.setDescription("you have reached level 1 congradulations! you now have access to !dj");
			lvl1.setColor(0x343deb);
			lvl1.setFooter("created by Ben", event.getMember().getUser().getAvatarUrl());
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(lvl1.build()).queue();
			lvl1.clear();
		}
		else if(getplayerXp(event.getMember()) == 200) {
			event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(747564698198605855L)).complete();
			EmbedBuilder lvl2 = new EmbedBuilder();
			lvl2.setTitle("(☞ﾟヮﾟ)☞LEVEL  1  REACHED ☜(ﾟヮﾟ☜)");
			lvl2.setDescription("you have reached level 2 congradulations you now have access to !games!");
			lvl2.setColor(0x343deb);
			lvl2.setFooter("created by Ben", event.getMember().getUser().getAvatarUrl());
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(lvl2.build()).queue();
			lvl2.clear();
		}
		
}


public void randXp(Member member) {
	if(!playerXp.containsKey(member))
		setPlayerXp(member, 0);
	setPlayerXp(member, getplayerXp(member) + 10);
	
	
}

private int getplayerXp(Member member) {
	return playerXp.get(member);
}
private void setPlayerXp(Member member, int num) {
	playerXp.put(member, num);
}
private int getPlayerTime(Member member) {
	return playerTimer.get(member);
}
private void setPlayerTime(Member member, int num) {
	playerTimer.put(member, num);
}
private boolean canGetXp(Member member) {
	if(!playerTimer.containsKey(member)) {
		return true;
	
	}
	else {
		return false;
	}
}
public void startTimer() {
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			for(Member memeber: playerTimer.keySet() ) {
				setPlayerTime(memeber, getPlayerTime(memeber) -1);
				if(getPlayerTime(memeber)==0) {
					playerTimer.remove(memeber);
				}
			}
		}
	};
	timer.schedule(task, 1000, 1000);
}
}

