package PacMan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class GameScore extends JLabel implements ActionListener{

	static private GameScore instance = null;
	private float points = 0;
	private float seconds = 0;
	private float pktsMin = 0;
	private float pktsRound;
	private Timer timer = new Timer(1000, this);
	private JLabel punkteLabel = new JLabel("Punkte: " + points);
	private JLabel pktsMinLabel = new JLabel(pktsMin + "/pro Minute");
	
	private GameScore() {}
	
	/*
	 * Zum abrufen der Instance
	 * @return GameScore - Die eine instance des Objekts GameScore wird zurückgegeben
	 */
	public static GameScore getInstance() {
		if(instance == null) {
			instance = new GameScore();
		}
		return instance;
	}
	 
	/*
	 * Für den Neustart eines Spiels wird alles auf 0 gesetzt
	 */
	public void reset() {
		points = 0;
		seconds = 0;
		pktsMin = 0;
		timer.restart();
	}
	
	/*
	 * Zum hochzählen der Punkte bei erreichen eines Punktefelds
	 */
	public void setPoints() {
		points++;
		punkteLabel.setText("Punkte: " + Float.toString(points));
	}
	
	/*
	 * Zum abfrufen des JLabels für die Punkte
	 * @return JLabel - Das Label mit den bisher erreichten Punkten
	 */
	public JLabel getPktsLabel() {
		return punkteLabel;
	}
	
	/*
	 * Die Methode gibt ein JLabel mit dem Inhalt der Punkte pro Minute zurück
	 * @return JLabel - Das JLabel mit den Punkten pro Minute
	 */
	public JLabel getPktsMinLabel() {
		return pktsMinLabel;
	}
	
	/*
	 * Die Methode gibt den aktuellen Wert der Punkte pro Minute zurück
	 * @return float - Punkte pro Minute
	 */
	public float getPktsMinCount() {
		return pktsRound;
	}
	
	/*
	 * Der Timer wird gestartet
	 */
	public void start() {
		seconds = 0;
		timer.start();
	}
	
	/*
	 * Der Timer wird gestoppt
	 */
	public void stop() {
		timer.stop();
	}
	

	/*
	 * Die Action Methode für den Timer. Dieser zählt die Sekunden hoch und gerechnet anschließend
	 * den Punkte pro Minute wert und schreibt diesen in das JLabel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		seconds++;
		pktsMin = (points / seconds) * 60;
		pktsRound = Math.round(pktsMin);
		pktsMinLabel.setText(Float.toString(pktsRound) + "/pro Minute");		
	}

}
