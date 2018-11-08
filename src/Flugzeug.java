/**
 * 
 * @author Lars Kowoll, Philip Zirfaß
 * @version 11/2018
 * 
 */

public class Flugzeug implements Runnable {
	private Flughafen _flughafen;
	private String _id;
	private long _flugdauer; // in Sekunden
	private long _startzeit; // in Millisekunden
	private Status _status;
	private long _zeit; // in Millisekunden
	
	/**
	 * Konstruktor
	 * 
	 * @param id			ID des Flughafens
	 * @param flughafen		Flughafen, auf dem Flugzeug starten und landen kann
	 */
	public Flugzeug(String id, Flughafen flughafen) {
		_flughafen = flughafen;
		_id = id;
		_flugdauer = 0;
		_startzeit = 0;
		_status = Status.GELANDET;
	}
	
	/**
	 * Run-Methode des Flugzeugs
	 */
	@Override
	public void run() {
		int random = 0;
		boolean gelandet = false;
		while (!gelandet) {
			// Ermittlung Zufallszahl zwischen 3 und 8
			while ((random < 3) || (random > 8)) {
				random = (int) (Math.random() * 10);
			}
			
			// Berechnung Flugdauer und Aktualisierung Status
			_flugdauer = (_zeit - _startzeit) / 1000;
			if (_flugdauer == random) {
				_status = Status.IM_LANDEANFLUG;
			}
			
			gelandet = checkStatus();
			Thread.yield();
		}
	}
	
	/**
	 * Gibt aktuellen Status des Flugzeugs aus.
	 * 
	 * @return Status des Flugzeugs
	 */
	public Status getStatus() {
		return _status;
	}
	
	/**
	 * Setzt Status des Flugzeugs auf festgelegten Wert.
	 * 
	 * @param status	neuer Status des Flugzeugs
	 */
	public void setStatus(Status status) {
		_status = status;
	}
	
	/**
	 * Überprüft Status des Flugzeugs. Wenn Flugzeug im Landeanflug ist, wird Landung eingeleitet.
	 * 
	 * @return true, wenn Flugzeug den Status GELANDET hat, false sonst
	 */
	private boolean checkStatus() {
		switch (_status) {
			case IM_LANDEANFLUG: _flughafen.landen(this); break;
			case GELANDET: return true;
			default: break;
		}
		return false;
	}
	
	/**
	 * Setzt Zeit des Flugzeugs auf festgelegten Wert.
	 * 
	 * @param zeit		neue Zeit des Flugzeugs
	 */
	public void setZeit(long zeit) {
		_zeit = zeit;
	}
	
	/**
	 * Setzt Startzeit des Flugzeugs auf festgelegten Wert.
	 * 
	 * @param startzeit		neue Startzeit des Flugzeugs
	 */
	public void setStartzeit(long startzeit) {
		_startzeit = startzeit;
	}
	
	/**
	 * Erzeugt einen String, der relevante Informationen über das Flugzeug enthält.
	 * 
	 * @return String, der relevante Informationen über das Flugzeug enthält
	 */
	@Override
	public String toString() {
		return _id + " (Startzeit: " + _startzeit / 1000 + "; Flugdauer: " + _flugdauer + "s)";
	}
}
