package Games;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ben.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import java.util.HashMap;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.entities.Invite.Guild;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
public class games extends ListenerAdapter {
	HashMap<Member, Integer> playerCoins = new HashMap<>();
	HashMap<Member, Integer> playerTimer2 = new HashMap<>();
	public static final int MAX_NUM = 2;
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
	      for(Member member : event.getChannel().getMembers()) {
	            if(!playerCoins.containsKey(member))playerCoins.put(member, 20);
	        }
	     
	    int newpoint = getplayerCoins(event.getMember()) + 10;
		setPlayerCoins(event.getMember(), newpoint);
	  
		String[] args = event.getMessage().getContentRaw().split(" ");
		if(args[0].equalsIgnoreCase(Main.prefix + "Coins")) {  
			event.getChannel().sendMessage("you have " + getplayerCoins(event.getMember()) + " coins").queue();
		
			if(canGetCoins(event.getMember())) {
				randXp(event.getMember());
				setPlayerTime(event.getMember(), 60);
				}
		}
		else if(args[0].equalsIgnoreCase(Main.prefix + "playgame")) {
			event.getChannel().sendMessage("Welcome to the game here is how you play you just type in !letsplay and what you think the number is 1 or 0 and you will gain or lose a random amount").queue();
		}
			else if(args[0].equalsIgnoreCase(Main.prefix + "letsplay")) {
				Random r = new Random();
				if(args.length < 2 || args.length > 2) {
					event.getChannel().sendMessage("you suck you need to guess the number").queue();
				}      
				else if(args.length == 2) {
							int cash = getplayerCoins(event.getMember());
							int Rbet = r.nextInt(MAX_NUM);	
							int WL = r.nextInt(30);
							if((Integer.parseInt(args[1]) == Rbet)) {
									//int result = cash + Rbet;
									int newishpoints = getplayerCoins(event.getMember())  + WL;
									setPlayerCoins(event.getMember(), newishpoints);
									event.getChannel().sendMessage("the numeber was " + Integer.toString(Rbet)).queue();
									event.getChannel().sendMessage("you have won " + Integer.toString(WL)).queue();
								}
								else if(!(Integer.parseInt(args[1]) == Rbet)) {
									int newishpoints = getplayerCoins(event.getMember()) - WL;
									setPlayerCoins(event.getMember(), newishpoints);
									event.getChannel().sendMessage("the numeber was " +Integer.toString(Rbet)).queue();
									event.getChannel().sendMessage("you have lost " + Integer.toString(WL)).queue();
								}
						
							//TODO
							//check if the bet is more than they have, make it so they can actually win the game, make it so it shows the target number they put in,fix info about game
				}
			}
		
			
		}
		 
		
		



public void randXp(Member member) {
	new Random();
	if(!playerCoins.containsKey(member))
		setPlayerCoins(member, 0);
		setPlayerCoins(member, getplayerCoins(member) + 10);
	
	
}


private int getplayerCoins(Member member) {
	return playerCoins.get(member);
}
private void setPlayerCoins(Member member, int num) {
	playerCoins.put(member, num);
}
private int getPlayerTime(Member member) {
	return playerTimer2.get(member);
}
private void setPlayerTime(Member member, int num) {
	playerTimer2.put(member, num);
}
private boolean canGetCoins(Member member) {
	if(!playerTimer2.containsKey(member)) {
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
			for(Member memeber: playerTimer2.keySet() ) {
				setPlayerTime(memeber, getPlayerTime(memeber) -1);
				if(getPlayerTime(memeber)==0) {
					playerTimer2.remove(memeber);
				}
			}
		}
	};
	timer.schedule(task, 1000, 1000);
}
}
