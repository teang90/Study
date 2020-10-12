package javaStudy.Stream;

public class Student2 {

	String name;
	boolean isMale;
	int hak;
	int ban;
	int score;
	
	public Student2(String name, boolean isMale, int hak, int ban, int score) {
		this.name = name;
		this.isMale = isMale;
		this.hak = hak;
		this.ban = ban;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public int getHak() {
		return hak;
	}

	public void setHak(int hak) {
		this.hak = hak;
	}

	public int getBan() {
		return ban;
	}

	public void setBan(int ban) {
		this.ban = ban;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	enum Level{ HIGH, MID, LOW }
	
	@Override
	public String toString() {
		return String.format("[%s %s %d학년 %d반 %3d점]", name, isMale?"남":"여", hak, ban, score);
	}
	
}
