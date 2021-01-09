package cardgame;

public class GameRegle {
	
	private int people;
	private int level;
	private int map;

	public GameRegle(int p,int l,int m) {
		this.people = p;
		this.level = l;
		this.map = m;
	}
	
	public void SetPeople(int p) {
		this.people = p;
	}
	
	public void SetpLevel(int l) {
		this.people = l;
	}
	
	public void SetMap(int m) {
		this.people = m;
	}
	
	public int GetPeople() {
		return this.people;
	}
	
	public int GetLevel() {
		return this.level;
	}
	
	public int GetMap() {
		return this.map;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Nbplayer:"+people+" Level:"+level+" Map:"+map);
		return sb.toString(); 
	}
}
