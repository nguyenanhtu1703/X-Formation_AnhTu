package prac.xformation.anhtu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import prac.xformation.anhtu.cuisine.Italian;
import prac.xformation.anhtu.cuisine.Dish;
import prac.xformation.anhtu.cuisine.LunchType;
import prac.xformation.anhtu.cuisine.Mexican;
import prac.xformation.anhtu.cuisine.Polish;
import prac.xformation.anhtu.dish.*;
import prac.xformation.anhtu.drink.*;

public class App {
	private static List<Dish> dishes = new ArrayList<>();
	private static List<Drink> drinks = new ArrayList<>();
	private static List<Dish> orderDishes = new ArrayList<>();
	private static List<Drink> orderDrinks = new ArrayList<>();
	private static int cuisineLastDishType; 
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		dishes.add(new HotDogItalian("Hot dog Italian", 5, Italian.class, LunchType.MainCourse));
		dishes.add(new SteakPolish("Steak Polish", 7, Polish.class, LunchType.Dessert));
		dishes.add(new PierogiPolish("Pierogi Polish", 6, Polish.class, LunchType.MainCourse));
		dishes.add(new BeefMexican("Beef Mexican", 4, Mexican.class, LunchType.Dessert));
		dishes.add(new ChickenMexican("Chicken Mexican", 6.5f, Mexican.class, LunchType.MainCourse));
		dishes.add(new PestoItalian("Pesto Italian", 10, Italian.class, LunchType.Dessert));

		drinks.add(new EspressoMartini("EspressoMartini", 2, DrinkAdditional.None));
		drinks.add(new Margarita("Margarita", 3, DrinkAdditional.None));

		System.out.println("Hello, Welcome to the X-Formation restaurent!\n");
		order();
	}

	static void order() {
		int orderKind;
		do {
			orderKind = chooseKindOfOrder();
		} while (orderKind > 4 || orderKind < 1);
	
		if (orderKind == 1) {
			chooseCuisine();
		} else if (orderKind == 2) {
			chooseDrink();
		} else if (orderKind == 3) {
			checkout();
		} else {
			System.out.println("Thank you!");
		}
	}

	static void chooseCuisine() {
		try {
			System.out.println("Please choose Cuisine\n");
			System.out.println("1 Polish");
			System.out.println("2 Mexican");
			System.out.println("3 Italian");
			
			scanner = new Scanner(System.in);
			cuisineLastDishType = scanner.nextInt();
			if (cuisineLastDishType > 3 || cuisineLastDishType < 1) {
				chooseCuisine();
			} else {
				chooseLunch();
			}
				
		} catch (Exception e) {
			chooseCuisine();
		}
	}
	
	static Class<?> typeCuisine() {
		if (cuisineLastDishType == 1) {
			return Polish.class;
		} else if (cuisineLastDishType == 2) {
			return Mexican.class;
		} else {
			return Italian.class;
		}
	}
	
	static void chooseLunch() {
		try {
			List<Dish> tmpDishes = new ArrayList<>();

			System.out.println("Please choose\n");
			System.out.println("\nMain Course\n\n");
			System.out.println(String.format("%4s", "Nr") + String.format("%20s", "Name") + String.format("%21s", "Price-$"));
			
			int mainCourseCounter = 0;
			for (int i = 0; i < dishes.size(); i++) {
				if (dishes.get(i).getLunchType() == LunchType.MainCourse && dishes.get(i).getType() == typeCuisine()) {
					System.out.println(String.format("%4d", (mainCourseCounter + 1)) + " " + String.format("%20s", dishes.get(i).getName()) + String.format("%20.1f", dishes.get(i).getPrice()));
					mainCourseCounter++;
					tmpDishes.add(dishes.get(i));
				}
			}

			System.out.println("\n\nDessert\n\n");
			
			int dessertCounter = 0;
			for (int i = 0; i < dishes.size(); i++) {
				if (dishes.get(i).getLunchType() == LunchType.Dessert && dishes.get(i).getType() == typeCuisine()) {
					System.out.println(String.format("%4d", (mainCourseCounter + dessertCounter + 1)) + " " + String.format("%20s", dishes.get(i).getName()) + String.format("%20.1f", dishes.get(i).getPrice()));
					dessertCounter++;
					tmpDishes.add(dishes.get(i));
				}
			}
			
			System.out.println("\n");
			
			System.out.println(String.format("%4d", (tmpDishes.size() + 1)) + " Main Menu");
			
			scanner = new Scanner(System.in);
			int numberOrder = scanner.nextInt();
			
			if (numberOrder == tmpDishes.size() + 1) {
				order();
			} else if (numberOrder > tmpDishes.size() + 1 || numberOrder < 1) {
				chooseLunch();
			} else {
				orderDishes.add(tmpDishes.get(numberOrder - 1));
				chooseLunch();
			}
		} catch (Exception e) {
			chooseLunch();
		}
	}

	static void chooseDrink() {
		try {
			System.out.println("Please choose\n");
			System.out.println(String.format("%4s", "Nr") + String.format("%20s", "Name") + String.format("%21s", "Price-$"));
			for (int i = 0; i < drinks.size(); i++) {
				System.out.println(String.format("%4d", (i + 1)) + " " + String.format("%20s", drinks.get(i).getName()) + String.format("%20.1f", drinks.get(i).getPrice()));
			}
			
			scanner = new Scanner(System.in);
			int numberOrder = scanner.nextInt();
			if (numberOrder < 1 || numberOrder >= drinks.size() + 1)
				chooseDrink();
			else {
				orderDrinks.add(drinks.get(numberOrder - 1));
			}
		} catch (InputMismatchException e) {
			chooseDrink();
			return;
		}
		chooseDrinkAdditional();
	}
	
	static void chooseDrinkAdditional() {
		try {
			System.out.println("Additional for drink?\n");
			System.out.println("1 Ice cubes(+1$)");
			System.out.println("2 Lemon(+1.5$)");
			System.out.println("3 Both Ice Cubes and Lemon(+2.5$)");
			System.out.println("4 No additional(0$)");
			
			scanner = new Scanner(System.in);
			int numberOrder = scanner.nextInt();
			if (numberOrder > 4 || numberOrder < 1) {
				chooseDrinkAdditional();
			} else {
				if (numberOrder == 1) {
					orderDrinks.get(orderDrinks.size() - 1).setDrinkAdditional(DrinkAdditional.IceCubes);
					orderDrinks.get(orderDrinks.size() - 1).setPrice(orderDrinks.get(orderDrinks.size() - 1).getPrice() + 1f);
				} else if (numberOrder == 2) {
					orderDrinks.get(orderDrinks.size() - 1).setDrinkAdditional(DrinkAdditional.Lemon);
					orderDrinks.get(orderDrinks.size() - 1).setPrice(orderDrinks.get(orderDrinks.size() - 1).getPrice() + 1f);
				} else if (numberOrder == 3) {
					orderDrinks.get(orderDrinks.size() - 1).setDrinkAdditional(DrinkAdditional.Boths);
					orderDrinks.get(orderDrinks.size() - 1).setPrice(orderDrinks.get(orderDrinks.size() - 1).getPrice() + 2.5f);
				}
			}
		} catch (Exception e) {
			chooseDrinkAdditional();
		}
		order();
	}

	static int chooseKindOfOrder() {
		try {
			System.out.println("Menu\n");
			System.out.println("1 Launch");
			System.out.println("2 Drink");
			System.out.println("3 Checkout");
			System.out.println("4 Exit");

			scanner = new Scanner(System.in);
				int res = scanner.nextInt();
			if (res > 4 || res < 1) {
				System.out.print(":) ");
			}
			return res;
		} catch (InputMismatchException e) {
			System.out.print(":) ");
			return chooseKindOfOrder();
		}
	}
	
	static void checkout() {
		float totalOrdered = 0;
		
		System.out.println(String.format("%4s", "Nr") + String.format("%40s", "Name") + String.format("%21s", "Price-$"));
		
		for(int i = 0; i < orderDishes.size(); i++) {
			System.out.println(String.format("%4d", (i + 1)) + " " + String.format("%40s", orderDishes.get(i).getName()) 
					           + String.format("%20.1f", orderDishes.get(i).getPrice()));
			totalOrdered += orderDishes.get(i).getPrice();
		}
		
		for(int i = 0; i < orderDrinks.size(); i++) {
			System.out.println(String.format("%4d", (i + 1 + orderDishes.size())) + " " + String.format("%40s", orderDrinks.get(i).getName() 
					           + formatStringDrinkAdditional(orderDrinks.get(i).getDrinkAdditional())) + String.format("%20.1f", orderDrinks.get(i).getPrice()));
			totalOrdered += orderDrinks.get(i).getPrice();
		}
		System.out.println(String.format("%65s", "Total: " + String.format("%.1f", totalOrdered)));

		try {

			System.out.println("\n\n1 Pay");
			System.out.println("2 Main Menu");

			scanner = new Scanner(System.in);
			int res = scanner.nextInt();
			if (res > 2 || res <= 1) {
				checkout();
			} else {
				order();
			}
		} catch (InputMismatchException e) {
			System.out.print(":) ");
			checkout();
		}
	}
	
	static String formatStringDrinkAdditional(DrinkAdditional drinkAdditional) {
		if (drinkAdditional == DrinkAdditional.Boths) {
			return "(+Ice Cubes+Lemon)";
		} else if (drinkAdditional == DrinkAdditional.IceCubes) {
			return "(+Ice Cubes)";
		} else if (drinkAdditional == DrinkAdditional.Lemon) {
			return "(+Lemon)";
		} else return "";
	}
}
