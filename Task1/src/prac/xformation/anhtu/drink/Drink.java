package prac.xformation.anhtu.drink;

public class Drink {
	private String name;
	private float price;
	private DrinkAdditional drinkAdditional;

	public Drink(String name, float price, DrinkAdditional drinkAdditional) {
		this.name = name;
		this.price = price;
		this.drinkAdditional = drinkAdditional;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float f) {
		this.price = f;
	}

	public DrinkAdditional getDrinkAdditional() {
		return drinkAdditional;
	}

	public void setDrinkAdditional(DrinkAdditional drinkAdditional) {
		this.drinkAdditional = drinkAdditional;
	}
	
}
