package PacMan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener{

	private static Spiel spiel = new Spiel();
	Timer timer = new Timer(500, this);

	
	/*
	 * Beim erstellen des Panels wird der Timer für die Punkte gestartet
	 * Die Methode start wird aufgerufen
	 */
	public Panel() {
		GameScore.getInstance().start();
		start();
	}
	
	/*
	 * Die Methode zum starten eines neuen Spiels mit favorisiertem Level aus dem Menü heraus.
	 * @param int lvl - Das Level, welches gespielt werden soll
	 */
	public void newGame(int lvl) {
		spiel.newGame(lvl);
		timer.restart();
		repaint();
	}
	
	/*
	 * Die paint Methode wird zum zeichnen des Spielfeldes benötigt. 
	 */
	@Override
	public void paint(Graphics gr) {
		super.paint(gr);
		gr.setColor(Color.BLACK);
		gr.fillRect(0, 0, getWidth(), getHeight());
		gr.setColor(Color.ORANGE);
		gr.drawRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < Anzeige.SPIELBREITE; i++) {
			for(int j = 0; j < Anzeige.SPIELHOEHE; j++) {
				if(spiel.get(i, j) == Anzeige.WALL) {
					gr.setColor(Color.ORANGE);
					gr.fillRect(getWidth()/Anzeige.SPIELBREITE * i, getHeight()/Anzeige.SPIELHOEHE * j, getWidth()/Anzeige.SPIELBREITE, getHeight()/Anzeige.SPIELHOEHE);
				} else if(spiel.get(i, j) == Anzeige.FIGUR){
					gr.setColor(Color.GREEN);
					gr.fillRect(getWidth()/Anzeige.SPIELBREITE * i, getHeight()/Anzeige.SPIELHOEHE * j, getWidth()/Anzeige.SPIELBREITE, getHeight()/Anzeige.SPIELHOEHE);
				} else if(spiel.get(i, j) == Anzeige.POINT){
					gr.setColor(Color.BLUE);
					gr.fillOval(getWidth()/Anzeige.SPIELBREITE * i, getHeight()/Anzeige.SPIELHOEHE * j, getWidth()/Anzeige.SPIELBREITE, getHeight()/Anzeige.SPIELHOEHE);
				} else {
					gr.setColor(Color.BLACK);
					gr.drawRect(getWidth()/Anzeige.SPIELBREITE * i, getHeight()/Anzeige.SPIELHOEHE * j, getWidth()/Anzeige.SPIELBREITE, getHeight()/Anzeige.SPIELHOEHE);
				}
			}
		}
	}
	
	/*
	 * Die Methode startet den Timer für die Fortbewegung
	 */
	public void start() {
		timer.start();
	}
	
	/*
	 * Die Ausführung der Aktivitäten, wenn der Timer auslöst
	 * Dabei wird die Methode bewegen aus der Klasse spiel aufgerufen, sowie das Spielfeld neu gezeichnet.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		spiel.bewegen();
		this.repaint();
	}
}
