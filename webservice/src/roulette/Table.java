package roulette;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
public class Table {
	private int id;
	private int turn;
	private Calendar turntime;
	private int minutes = 1;
	private int seconds = 0;
	private final int min = 0, max = 36;
	private Map<Integer, Player> players;
	private AtomicInteger idPlayers;
	
	public Table() {
		turntime = Calendar.getInstance();
		players = new ConcurrentHashMap<Integer, Player>();
		setTurn();
    }

    @XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public int getTurn() {	
		setTurn();
		return turn;
	}

	@XmlElement
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@XmlElement
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	private void setTurn() {
		//Set the time for the next turn
		Calendar nextTurn = turntime;
		nextTurn.set(Calendar.MINUTE, nextTurn.get(Calendar.MINUTE) + minutes);
		nextTurn.set(Calendar.MINUTE, nextTurn.get(Calendar.SECOND) + seconds);
		
		//Check if it is time for the next turn
		if (Calendar.getInstance().after(nextTurn) || Calendar.getInstance().equals(nextTurn)){
			//Set random number between 0 and 36
			//then set the time to now
			Random random = new Random();
			turn = random.nextInt(max - min + 1) + min;
			turntime = Calendar.getInstance();
		}
		
	}
	
	@XmlElement
	public Collection<Player> getPlayers() {
		return players.values();
	}
	
	public void addPlayer(Player player){
		player.setId(idPlayers.incrementAndGet());
    	this.players.put(player.getId(), player);
    }
}
