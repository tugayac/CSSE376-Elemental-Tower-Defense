/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 22, 2012.
 */
public class Player {

	private int score;
	private int mana;
	private int health;

	public Player() {
		this.score = 0;
		this.mana = 0;
		this.health = 0;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void setScore(int score) {
		if (score < 0) {
			this.score = 0;
		} else {
			this.score = score;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void decScore(int score) {
		int temp = this.score - score;
		if (temp < 0) {
			this.score = 0;
		} else {
			this.score = temp;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void incScore(int score) {
		this.score += score;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void setMana(int mana) {
		if (mana < 0) {
			this.mana = 0;
		} else {
			this.mana = mana;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getMana() {
		return this.mana;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void decMana(int mana) {
		int temp = this.mana - mana;
		if (temp < 0) {
			this.mana = 0;
		} else {
			this.mana = temp;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void incMana(int mana) {
		this.mana += mana;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void setHealth(int health) {
		if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void decHealth(int health) {
		int temp = this.health - health;
		if (temp < 0) {
			this.health = 0;
		} else {
			this.health = temp;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public void incHealth(int health) {
		this.health += health;
	}
}
