import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Lars Kowoll, Philip Zirfaﬂ
 * @version 11/2018
 * 
 */

public class Flughafen implements Runnable {
	private int _anzahlFlugzeuge;
	private List<Flugzeug> _flugzeuge;
	private HashMap<Flugzeug, Thread> _threadMap;
	private long _zeitInit;
	
	/**
	 * Konstruktor
	 * 
	 * @param anzahlFlugzeuge	Anzahl der Flugzeuge auf dem Flughafen
	 */
	public Flughafen(int anzahlFlugzeuge) {
		_anzahlFlugzeuge = anzahlFlugzeuge;
		_zeitInit = System.currentTimeMillis(); // in Millisekunden
		_flugzeuge = new ArrayList<Flugzeug>();
		_threadMap = new HashMap<Flugzeug, Thread>();
	}
	
	/**
	 * Landet ein Flugzeug.
	 * 
	 * @param flugzeug		Flugzeug, das gelandet wird
	 */
	public void landen(Flugzeug flugzeug) {
		try {
			// Thread beenden
			Thread flugzeugThread = _threadMap.get(flugzeug);
			if (!flugzeugThread.isInterrupted()) {
				
				flugzeugThread.interrupt();
				
				_threadMap.remove(flugzeug);
				
				// Konsolenausgabe
				System.out.println(flugzeug.toString() + " ist gelandet"); // + ";  Zeit: " + LocalTime.now().toString());
								
				// neues Flugzeug starten
				for (Flugzeug flugzeug2: _flugzeuge) {
					if (flugzeug2.getStatus() == Status.GELANDET) {
						starten(flugzeug2);
						break;
					}
				}
				
				flugzeug.setStatus(Status.GELANDET);
			}
		} catch (NullPointerException e) {
			
		}
		
		
	}
	
	/**
	 * Run-Methode des Flughafens
	 */
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
			// Zeit der Flugzeuge aktualisieren
			for (Flugzeug flugzeug: _flugzeuge) {
				flugzeug.setZeit(System.currentTimeMillis() - _zeitInit);
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Startet ein Flugzeug.
	 * 
	 * @param flugzeug		Flugzeug, das gestartet wird
	 */
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
	
	/**
	 * Erzeugt ein neues Flugzeug.
	 */
	private void erzeugeFlugzeug() {
		// Flugzeug erstellen
		int number = _flugzeuge.size() + 1;
		String id = "Flugzeug " + number;
		Flugzeug flugzeug = new Flugzeug(id, this);
		_flugzeuge.add(flugzeug);
	}
	
	public static void main(String[] args) {
		int anzahlFlugzeuge = 4;
		Thread flughafenThread = new Thread(new Flughafen(anzahlFlugzeuge));
		flughafenThread.start();
	}
}
