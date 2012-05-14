/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 22, 2012.
 */
public class Player {

	private int score;
	private int health;
	private int currency;

	public Player(int mana, int health) {
		this.score = 0;
		this.health = health;
		this.currency = mana;
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

	public void setCurrency(int amount) {
		if (amount <= 0) {
			this.currency = 0;
		} else {
			this.currency = amount;
		}
	}

	public boolean decCurrency(int amount) {
		if (amount <= this.currency) {
			this.currency -= amount;
			return true;
		}
		return false;
	}

	public int getCurrency() {
		return this.currency;
	}

	public void incCurrency(int amount) {
		this.currency += amount;
	}

	// -----------------------------

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
