package PacMan;

import javax.swing.JOptionPane;

public class Spiel extends Anzeige{

	static int laufrichtung = 0; //1 = hoch, 2 = rechts, 3 = runter, 4 = links, 0 = nicht bewegen
	private GameScore gameScore = GameScore.getInstance();


	/*
	 * Die Methode startet aus der Spielklasse ein neues Level. Wenn das level, welches geladen werden soll, kleiner oder gleichgroß mit
	 * der Anzahl der hinterlegten Level ist, wird dies geladen. Sollte ein Level angefordert werden, welches nicht eingerichtet ist,
	 * ist das Spiel vorbei und der Spieler kann entweder ein neues Spiel starten, oder das Spiel beenden
	 */
	public void newGame(int lvl) {
		if(Levels.getPlayableLevels() >= lvl) {
			laufrichtung = 0;
			this.activeLevel = lvl;
			this.newLevel(lvl);
		} else {
			GameScore.getInstance().stop();
			String[] options = {"Neues Spiel", "Beenden"};
			String endDeklaration = "Du hast alle Level geschafft. Glückwunsch dazu. Dies hast du mit " + gameScore.getPktsMinCount() + " Punkten pro Minute geschafft \n"
					+ "Möchtest du ein neues Spiel starten oder das Programm beenden?";
			int choosen = JOptionPane.showOptionDialog(null, endDeklaration, "Alle Level bewältigt", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if(choosen == 0) {
				restartGame();
			} else {
				System.exit(0);
			}
		}
		
	}
	
	/*
	 * Mit der Methode wird ein neues Spiel gestartet. Dazu wird gameScore resettet, das aktive Level auf eins gesetzt und ein neues Spiel mit aktivem Level gestartet
	 */
	public void restartGame() {
		gameScore.reset();
		this.activeLevel = 1;
		newGame(activeLevel);
		
	}
	
	/*
	 * Die Methode bewegt die Spielfigur in die angegebene Laufrichtiung. Es wird sich nur bewegt, wenn nicht das Spielfeldende oder
	 * eine Wand erreicht wurde.
	 * Dann wird die Position des Spielers verändert, der gegebenenfalls vorhandene Punkt gewertet und die Anzeige aktualisiert.
	 */
	public void bewegen() {
		if(laufrichtung == 1 && this.spielerPosY != 0 && this.get(this.spielerPosX, this.spielerPosY-1) != this.WALL) {
			this.set(this.spielerPosX, this.spielerPosY, LEER);
			this.spielerPosY--;
			if(this.get(this.spielerPosX, this.spielerPosY) == this.POINT) {
				gameScore.setPoints();
			}
			this.set(this.spielerPosX, this.spielerPosY, FIGUR);
		} else if(laufrichtung == 2 && this.spielerPosX != this.SPIELBREITE-1 && this.get(this.spielerPosX+1, this.spielerPosY) != this.WALL) {
			this.set(this.spielerPosX, this.spielerPosY, LEER);
			this.spielerPosX++;
			if(this.get(this.spielerPosX, this.spielerPosY) == this.POINT) {
				gameScore.setPoints();
			}
			this.set(this.spielerPosX, this.spielerPosY, FIGUR);
		} else if(laufrichtung == 3 && this.spielerPosY != this.SPIELHOEHE-1 && this.get(this.spielerPosX, this.spielerPosY+1) != this.WALL) {
			this.set(this.spielerPosX, this.spielerPosY, LEER);
			this.spielerPosY++;
			if(this.get(this.spielerPosX, this.spielerPosY) == this.POINT) {
				gameScore.setPoints();
			}
			this.set(this.spielerPosX, this.spielerPosY, FIGUR);
		} else if(laufrichtung == 4 && this.spielerPosX != 0 && this.get(this.spielerPosX-1, this.spielerPosY) != this.WALL) {
			this.set(this.spielerPosX, this.spielerPosY, LEER);
			this.spielerPosX--;
			if(this.get(this.spielerPosX, this.spielerPosY) == this.POINT) {
				gameScore.setPoints();
			}
			this.set(this.spielerPosX, this.spielerPosY, FIGUR);
		}
		if(levelFinished()) {
			newGame(this.activeLevel + 1);
		}
		
	}
	
	/*
	 * Nach einem Pfeiltastendruck wird in dieser Methode die Bewegungsrichtung geändert
	 */
	public static void richtungAendern(int direction) {
		laufrichtung = direction;
	}
	
	/*
	 * Die Methode checkt, ob das Level geschafft ist, indem die verbliebenen Punkte gezählt werden. Ist das der Fall,
	 * wird true zurück gegeben, andernfalls false
	 */
	public boolean levelFinished() {
		for(int i = 0; i < this.SPIELBREITE; i++) {
			for(int j = 0; j < this.SPIELHOEHE; j++) {
				if(this.get(i, j) == this.POINT) {
					return false;
				}
			}
		}
		return true;
	}
	

	
	
}
