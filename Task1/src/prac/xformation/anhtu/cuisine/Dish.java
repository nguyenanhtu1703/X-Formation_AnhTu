package prac.xformation.anhtu.cuisine;

public class Dish extends Cuisine {
	private String name;
	private float price;
	private LunchType lunchType;

	public Dish(String name, float price, Class<?> type, LunchType lunchType) {
		super(type);
		this.name = name;
		this.price = price;
		this.lunchType = lunchType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LunchType getLunchType() {
		return lunchType;
	}

	public void setLunchType(LunchType lunchType) {
		this.lunchType = lunchType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
