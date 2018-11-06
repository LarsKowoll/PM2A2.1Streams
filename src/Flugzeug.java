
public class Flugzeug implements Runnable {
	private Flughafen _flughafen;
	private String _id;
	private long _flugdauer;
	private long _startzeit;
	private Status _status;
	private long _zeit;
	
	public Flugzeug(String id, Flughafen flughafen) {
		_flughafen = flughafen;
		_id = id;
		_flugdauer = 0;
		_startzeit = 0;
		_status = Status.GELANDET;
	}
	
	@Override
	public void run() {
		int random = 0;
		while (true) {
			while ((random < 3) || (random > 8)) {
				random = (int) (Math.random() * 10);
			}
			
			_flugdauer = (_zeit - _startzeit) / 1000;
			
			if (_flugdauer == random) {
				_status = Status.IM_LANDEANFLUG;
			}
			
			checkStatus();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// System.out.println("Thread Exception");
			}			
		}
	}
	
	public Status getStatus() {
		return _status;
	}
	
	public void setStatus(Status status) {
		_status = status;
	}
	
	private void checkStatus() {
		if (_status == Status.IM_LANDEANFLUG) {
			_flughafen.landen(this);
		}
	}
	
	public String toString() {
		return _id + " (Startzeit: " + _startzeit / 1000 + "; Flugdauer: " + _flugdauer + "s)";
	}
	
	public void setZeit(long zeit) {
		_zeit = zeit;
	}
	
	public void setStartzeit(long startzeit) {
		_startzeit = startzeit;
	}

}
