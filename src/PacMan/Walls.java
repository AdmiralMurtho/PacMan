package PacMan;

public class Walls {

	private int posX;
	private int posY;
	

	/*
	 * Mit den übergebenen Parametern wird eine neue Wall generiert
	 * @param int posX - die X Position, an der die Wall generiert werden soll
	 * @param int posY - die Y Position, an der die Wall generiert werden soll
	 */
	public Walls(int posX, int posY) 
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	/*
	 * Die Position gibt den X Wert der Wall zurück
	 * @return int - der X Wert der Wall
	 */
	public int getX() {
		return this.posX;
	}
	
	/*
	 * Die Position gibt den Y Wert der Wall zurück
	 * @return int - der Y Wert der Wall
	 */
	public int getY() {
		return this.posY;
	}
}
