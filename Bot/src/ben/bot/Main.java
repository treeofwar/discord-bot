package ben.bot;

import javax.security.auth.login.LoginException;


import Games.games;
import events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
	public static JDA jda;
	public static String prefix = "!";
	//main method
	public static void main(String[] args) throws LoginException {
		//Xp system = new Xp();
		//games system2 = new games();
																																																						jda = JDABuilder.createDefault("NzQ2NDU4MTU0MTMyNTcwMTUz.X0AnVA.N2Hqjdt5EKHH7hljmWIX_JSJTHw").build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setActivity(Activity.watching("Ben is fixing like 40 bugs in this stupid bot"));
		jda.addEventListener(new Commands());
		jda.addEventListener(new Events());
		jda.addEventListener(new Filter());
		//jda.addEventListener(system);
		//jda.addEventListener(system2);
		//system2.startTimer();
		//system.startTimer();
		
	
	}
}
