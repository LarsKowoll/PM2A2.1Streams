import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flughafen implements Runnable {
	private int _anzahlFlugzeuge;
	private List<Flugzeug> _flugzeuge;
	private HashMap<Flugzeug, Thread> _threadMap;
	
	public Flughafen() {
		_flugzeuge = new ArrayList<Flugzeug>();
		_threadMap = new HashMap<Flugzeug, Thread>();
	}
	
	public void landen(Flugzeug flugzeug) {
		// Thread beenden
		Thread flugzeugThread = _threadMap.get(flugzeug);
		flugzeugThread.interrupt();
		
		flugzeug.istGelandet();
		_threadMap.remove(flugzeug);
		
		// Konsolenausgabe
		System.out.println(flugzeug.toString() + " ist gelandet;  Zeit: " + LocalTime.now().toString());
	}

	@Override
	public void run() {
		List<Flugzeug> zuEntfernendeFlugzeuge = new ArrayList<Flugzeug>();
		boolean startNeuesFlugzeug = false;
		erzeugeFlugzeug();
		erzeugeFlugzeug();
		erzeugeFlugzeug();
		
		while(true) {
			for (Flugzeug flugzeug: _flugzeuge) {
				if (flugzeug.istImLandeanflug()) {
					landen(flugzeug);
					zuEntfernendeFlugzeuge.add(flugzeug);
					startNeuesFlugzeug = true;
				}
			}
			
			for (Flugzeug flugzeug: zuEntfernendeFlugzeuge) {
				_flugzeuge.remove(flugzeug);
			}
			zuEntfernendeFlugzeuge.clear();
			
			if (startNeuesFlugzeug) {
				erzeugeFlugzeug();
				startNeuesFlugzeug = false;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void erzeugeFlugzeug() {
		// Flugzeug erstellen
		String id = "Flugzeug " + _anzahlFlugzeuge;
		Flugzeug flugzeug = new Flugzeug(id, 0, this, 0);
		
		// Thread erstellen und starten
		Thread flugzeugThread = new Thread(flugzeug);
		flugzeugThread.start();

		_threadMap.put(flugzeug, flugzeugThread);
		
		_flugzeuge.add(flugzeug);
		_anzahlFlugzeuge++;
		
		// Konsolenausgabe
		System.out.println(flugzeug.toString() + " ist gestartet; Zeit: " + LocalTime.now().toString());
	}
	
	public static void main(String[] args) {
		Thread flughafenThread = new Thread(new Flughafen());
		flughafenThread.start();
	}
}
