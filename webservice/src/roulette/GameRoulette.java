package roulette;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roulette")
public class GameRoulette {
	private static GameRoulette instance = null;
	private Map<Integer, Table> tables;
	private Map<Integer, Player> players;
	private AtomicInteger idPlayer;
	
	protected GameRoulette() { 	
		players = new ConcurrentHashMap<Integer, Player>();
		idPlayer = new AtomicInteger(1);
		
		//Create player
		Player player1 = new Player();
		player1.setName("LauraSmit");
		player1.setPassword("bla");
		players.put(1, player1);
		
    	//Create 6 tables
    	tables = new ConcurrentHashMap<Integer, Table>();
    	Table table1 = new Table();
    	table1.setId(1);
    	tables.put(1, table1);    
    	
    	Table table2 = new Table();
    	table2.setId(2);
    	tables.put(2, table2);
    	
    	Table table3 = new Table();
    	table3.setId(3);
    	tables.put(3, table3);
    	
    	Table table4 = new Table();
    	table4.setId(4);
    	tables.put(4, table4);
    	
    	Table table5 = new Table();
    	table5.setId(5);
    	tables.put(5, table5);
    	
    	Table table6 = new Table();
    	table6.setId(6);
    	tables.put(6, table6);
    }
	
	public static GameRoulette getInstance() {
      if(instance == null) {
         instance = new GameRoulette();
      }
      return instance;
    }
	
	@XmlElement
	public Collection<Table> getTables() {
		return tables.values();
	}
	
	@XmlElement
	public Collection<Player> getPlayers() {
		return players.values();
	}
	
	public void addPlayer(Player player){    	    	
		player.setId(idPlayer.incrementAndGet());
    	this.players.put(player.getId(), player);
    }
	
	public void getPlayer(int id){
		//TODO:gegevens van een speler zonder wachtwoord
	}
	
	public int getAccount(String name, String password){
		for (Player p : this.players.values()){    		
    		if (p.getName().equals(name) && p.getPassword().equals(password)) return p.getId();
    	}
		return 0;
	}
}
