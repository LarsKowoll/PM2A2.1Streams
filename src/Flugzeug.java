
public class Flugzeug {
	private Flughafen _flughafen;
	private String _id;
	private int _flugdauer;
	private int _startzeit;
	private Status _status;
	public int _zeit;
	
	public Flugzeug(String id, int flugdauer, Flughafen flughafen, int startzeit) {
		_flughafen = flughafen;
		_id = id;
		_flugdauer = flugdauer;
		_startzeit = startzeit;
	}
	
	public void run() {
		
	}
	
	public void istGelandet() {
		_status = Status.GELANDET;
	}
	
	public String toString() {
		return null;
	}
	
	public void setZeit(int neueZeit) {
		_zeit = neueZeit;
	}

}
