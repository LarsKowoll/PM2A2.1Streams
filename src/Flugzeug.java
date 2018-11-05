
public class Flugzeug implements Runnable {
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
		_status = Status.IM_FLUG;
	}
	
	@Override
	public void run() {
		int random = 0;
		while (true) {
			while ((random < 3) || (random > 8)) {
				random = (int) (Math.random() * 10);
			}
			if (_flugdauer == random) {
				_status = Status.IM_LANDEANFLUG;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// System.out.println("Thread Exception");
			}
			
			_flugdauer++;
		}
	}
	
	public boolean istImLandeanflug()
	{
		return _status == Status.IM_LANDEANFLUG;
	}
	
	public void istGelandet() {
		_status = Status.GELANDET;
	}
	
	public String toString() {
		return _id + " (Flugdauer: " + _flugdauer + "s)";
	}
	
	public void setZeit(int neueZeit) {
		_zeit = neueZeit;
	}

}
