package model;

// All of the meals of the day

import java.util.ArrayList;
import java.util.List;

public class Meals {
    private List<Food> meal;
    private Date date;

    // EFFECTS: constructs a new AllMeals with no foods and a new date
    public Meals(String date) {
        meal = new ArrayList<>();
        this.date = new Date(date);
    }

    // getters
    public List<Food> getMeal() {
        return meal;
    }

    public String getDate() {
        return date.getDate();
    }

    // MODIFIES: this
    // EFFECTS: add given food item to meal
    public void addFood(Food food) {
        meal.add(food);
    }

    // MODIFIES: this
    // EFFECTS: removes given food item from meal
    public void removeFood(Food food) {
        meal.remove(food);
    }
}
