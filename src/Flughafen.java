import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flughafen implements Runnable {
	private int _anzahlFlugzeuge;
	private List<Flugzeug> _flugzeuge;
	private HashMap<Flugzeug, Thread> _threadMap;
	private long _zeitInit;
	private boolean _flugzeugGelandet;
	
	public Flughafen(int anzahlFlugzeuge) {
		_anzahlFlugzeuge = anzahlFlugzeuge;
		_zeitInit = System.currentTimeMillis();
		_flugzeuge = new ArrayList<Flugzeug>();
		_threadMap = new HashMap<Flugzeug, Thread>();
		_flugzeugGelandet = false;
	}
	
	public void landen(Flugzeug flugzeug) {
		try {
			// Thread beenden
			Thread flugzeugThread = _threadMap.get(flugzeug);
			if (!flugzeugThread.isInterrupted()) {
				
				flugzeugThread.interrupt();
				
				_threadMap.remove(flugzeug);
				
				// Konsolenausgabe
				System.out.println(flugzeug.toString() + " ist gelandet"); // + ";  Zeit: " + LocalTime.now().toString());
				
				_flugzeugGelandet = true;
				
				flugzeug.setStatus(Status.GELANDET);
			}
		} catch (NullPointerException e) {
			
		}
	}

	@Override
	public void run() {		
		// Flugzeuge erstellen
		for (int i = 0; i < _anzahlFlugzeuge; i++) {
			erzeugeFlugzeug();
		}
		// Flugzeuge starten
		for (Flugzeug flugzeug: _flugzeuge) {
			starten(flugzeug);
		}
		
		while(true) {
			for (Flugzeug flugzeug: _flugzeuge) {
				flugzeug.setZeit(System.currentTimeMillis() - _zeitInit);
			}
			
			if (_flugzeugGelandet) {
				// neues Flugzeug starten
				for (Flugzeug flugzeug: _flugzeuge) {
					if (flugzeug.getStatus() == Status.GELANDET) {
						starten(flugzeug);
						_flugzeugGelandet = false;
						break;
					}
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void starten(Flugzeug flugzeug)
	{
		// Thread erstellen und starten
		flugzeug.setStartzeit(System.currentTimeMillis() - _zeitInit);
		flugzeug.setStatus(Status.IM_FLUG);
		Thread flugzeugThread = new Thread(flugzeug);
		flugzeugThread.start();

		_threadMap.put(flugzeug, flugzeugThread);
		
		// Konsolenausgabe
		System.out.println(flugzeug.toString() + " ist gestartet"); // + "; Zeit: " + LocalTime.now().toString());
	}
	
	private void erzeugeFlugzeug() {
		// Flugzeug erstellen
		int number = _flugzeuge.size() + 1;
		String id = "Flugzeug " + number;
		Flugzeug flugzeug = new Flugzeug(id, this);
		_flugzeuge.add(flugzeug);
	}
	
	public static void main(String[] args) {
		int anzahlFlugzeuge = 10;
		Thread flughafenThread = new Thread(new Flughafen(anzahlFlugzeuge));
		flughafenThread.start();
	}
}
