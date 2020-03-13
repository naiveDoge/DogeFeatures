package cn.hotdoge.dogefeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class DogeFeatures extends JavaPlugin {
	
	public static List<UUID> votedPlayersRestart;
	public static ArrayList<BanWantedPlayersObj> banWantedPlayers;
	
	@Override
	public void onEnable() {
		System.out.println("FunnyFeatures Enabled!");
		this.saveDefaultConfig();
		
		//auto fill config.yml
		for(String key:this.getConfig().getDefaults().getKeys(true)) {
			if(this.getConfig().get(key).equals(this.getConfig().getDefaults().get(key))) {
				this.getConfig().set(key, this.getConfig().getDefaults().get(key));
			}
		}
		this.saveConfig();
		
		
		votedPlayersRestart = new ArrayList<UUID>();
		banWantedPlayers = new ArrayList<BanWantedPlayersObj>();
		
		Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new EventPlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new EventEatFood(), this);
		
		if(this.getConfig().getBoolean("featureSettings.vote.restart")) Bukkit.getPluginCommand("restartvote").setExecutor(new EventRestartVoteCommand());
		if(this.getConfig().getBoolean("featureSettings.vote.ban")) {
			Bukkit.getPluginCommand("banvotecreate").setExecutor(new EventCreateBanVoteCommand());
			Bukkit.getPluginCommand("banvote").setExecutor(new EventBanVoteCommand());
		}
	}
}
