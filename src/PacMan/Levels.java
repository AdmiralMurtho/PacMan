package PacMan;

public class Levels {

	static int playableLevelCount = 3;
	
	/*
	 * Drei verschiedene Level, die geladen werden können. 
	 */
	private Walls[] level1 = {
			new Walls(0,0),
			new Walls(0,1),
			new Walls(0,2),
			new Walls(0,3),
			new Walls(0,4),
			new Walls(0,5),
			new Walls(0,6),
			new Walls(0,7),
			new Walls(1,0),
			new Walls(2,0),
			new Walls(3,0),
			new Walls(4,0),
			new Walls(4,1),
			new Walls(2,2),
			new Walls(2,3),
			new Walls(2,4),
			new Walls(2,5),
			new Walls(3,3),
			new Walls(3,4),
			new Walls(3,5),
			new Walls(3,6),
			new Walls(4,3),
			new Walls(5,5),
			new Walls(5,6),
			new Walls(6,1),
			new Walls(6,2),
			new Walls(6,3),
			new Walls(6,5),
			new Walls(7,7)
	};
	private Walls[] level2 = {
			new Walls(0,0),
			new Walls(0,4),
			new Walls(0,5),
			new Walls(0,6),
			new Walls(0,7),
			new Walls(1,0),
			new Walls(1,2),
			new Walls(1,4),
			new Walls(2,0),
			new Walls(2,6),
			new Walls(3,0),
			new Walls(3,2),
			new Walls(3,3),
			new Walls(3,4),
			new Walls(3,5),
			new Walls(3,6),
			new Walls(4,0),
			new Walls(4,2),
			new Walls(5,2),
			new Walls(5,4),
			new Walls(6,1),
			new Walls(6,2),
			new Walls(7,5),
			new Walls(7,6),
			new Walls(7,7),
			
	};
	private Walls[] level3 = {
			new Walls(0,1),
			new Walls(0,2),
			new Walls(0,3),
			new Walls(0,4),
			new Walls(1,1),
			new Walls(1,3),
			new Walls(3,1),
			new Walls(3,3),
			new Walls(3,4),
			new Walls(5,2),
			new Walls(5,3),
			new Walls(5,4),
			new Walls(6,2),
			new Walls(7,3),
			new Walls(7,4)
	};

	
	/*
	 * Die Methode gibt ein Array vom Typ Walls zurück.
	 * @return Walls[] - ein Array des Typs Walls mit dem Inhalt der Wandpositionen 
	 */
	public Walls[] getLevel(int level) {
		if(level == 1) {
			return this.level1;
		} else if(level == 2) {
			return this.level2;
		} else {
			return this.level3;
		}
	}

	/*
	 * Die Methode checkt, ob in dem betreffendem Level an der Stelle eine Wand ist.
	 * @return boolean - true, wenn an der Stelle eine Wand ist, sonst falce
	 */
	public boolean istHierWand(int x, int y, int lvl) {
		boolean vorhanden = false;
		if(lvl == 1) {
			for(int i = 0; i < level1.length; i++) {
				if(level1[i].getX() == x && level1[i].getY() == y) {
					vorhanden = true;
				}
			}
		} else if(lvl == 2) {
			for(int i = 0; i < level2.length; i++) {
				if(level2[i].getX() == x && level2[i].getY() == y) {
					vorhanden = true;
				}
			}
		} else {
			for(int i = 0; i < level3.length; i++) {
				if(level3[i].getX() == x && level3[i].getY() == y) {
					vorhanden = true;
				}
			}
		}
		return vorhanden;
	}
	
	/*
	 * Die Methode gibt die Anzahl an spielbaren Levels zurück
	 * @return int - Die Anzahl der Spielbaren Level 
	 */
	public static int getPlayableLevels() {
		return playableLevelCount;
	}
}
