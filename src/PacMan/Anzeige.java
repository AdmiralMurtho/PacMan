package PacMan;

import java.util.Random;

public class Anzeige {

	Levels level = new Levels();
	Random random = new Random();
	int activeLevel = 1;

	Walls[] levelWalls;
	static final int SPIELBREITE = 8;
	static final int SPIELHOEHE = 8;
	
	static final char FIGUR = '>';
	static final char WALL = 'w';
	static final char POINT = 'p';
	static final char LEER = '.';
	
	private char[][] anzeigeCharArr;
	
	int spielerPosX,spielerPosY;
	
	/*
	 * Beim erstellen eines Objekts von Anzeige wird die Methode newLevel mit dem Parameter des aktuellen Levels aufgerufen
	 */
	public Anzeige() {
		newLevel(activeLevel);
	}
	
	/*
	 * Die Methode gibt das an der Position befindliche Element des Arrays
	 * @param int x - die X Position, um die es sich handelt
	 * @param int y - die Y Position, um die es sich handelt
	 * @return char - das char Element, welches an der Stelle eingetragen ist. 
	 */
	public char get(int x, int y) {
		return anzeigeCharArr[x][y];
	}
	
	/*
	 * Die Methode setzt in das Array das dementsprechende Char.
	 * @param int x - die X Position, an der das Char gesetzt werden soll
	 * @param int y - die Y Position, an der das Char gesetzt werden soll
	 * @param char c - das Char, welches gesetzt werden soll
	 */
	public void set(int x, int y, char c) {
		anzeigeCharArr[x][y] = c;
	}
	
	/*
	 * Die Methode ruft das aktuell geforderte Level aus der Klasse Levels ab, erstellt ein neues, zwei Dimensionalles Array
	 * und füllt dies mit den Wänden und Punkten.
	 * Danch werden zufallspositionen für die Spielfigur generiert und sollte die Position nicht die einer Wand entsprechen, wird die Pos gesetzt,
	 * sonst wird eine neue Position ermittelt.
	 * @param int lvl - Das Level, welches aufgerufen werden soll
	 */
	public void newLevel(int lvl) {
		
		activeLevel = lvl;
		levelWalls = level.getLevel(lvl);
		this.anzeigeCharArr = new char[SPIELBREITE][SPIELHOEHE];
		for(int i = 0; i < levelWalls.length; i++) {
			anzeigeCharArr[levelWalls[i].getX()][levelWalls[i].getY()] = WALL;
		}
		for(int i = 0; i < SPIELBREITE; i++) {
			for(int j = 0; j < SPIELHOEHE; j++) {
				if(anzeigeCharArr[i][j] != WALL) {
					anzeigeCharArr[i][j] = POINT;
				}
			}
		}
		boolean posFound = false;
		while(!posFound) {
			
			spielerPosX = random.nextInt(SPIELBREITE);
			spielerPosY = random.nextInt(SPIELHOEHE);
			if(level.istHierWand(spielerPosX, spielerPosY, activeLevel)){
				continue;
			} else {
				anzeigeCharArr[spielerPosX][spielerPosY] = FIGUR;
				posFound = true;
			}
		}
		
	}
}
