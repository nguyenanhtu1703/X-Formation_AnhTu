package prac.xformation.anhtu.cuisine;

public class Cuisine {
	private Class<?> type;
	
	Cuisine(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
}
