package model.food;

import model.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

// All the meals of the day
public class Meals implements Writable {
    private List<Food> foods;
    private Date date;

    // EFFECTS: constructs a new Meals with today's date
    public Meals() {
        foods = new ArrayList<>();
        date = new Date();
    }

    // EFFECTS: constructs a new Meals with no foods and a new date
    public Meals(String date) {
        foods = new ArrayList<>();
        this.date = new Date(date);
    }

    // getters
    public List<Food> getFoods() {
        return foods;
    }

    public String getDate() {
        return date.getDate();
    }


    // MODIFIES: this
    // EFFECTS: add given food item to meal
    public void addFood(Food food) {
        foods.add(food);
    }

    // MODIFIES: this
    // EFFECTS: removes given food item from meal
    public void removeFood(Food food) {
        foods.remove(food);
    }

    // MODIFIES: this
    // EFFECTS: add given list of foods to meal
    public void addFoods(List<Food> foods) {
        for (Food f : foods) {
            this.foods.add(f);
        }
    }

    // EFFECTS: returns the total calories consumed
    public int sumCalories() {
        int soFar = 0;
        for (Food f : foods) {
            soFar += f.getCalories();
        }
        return soFar;
    }

    // EFFECTS: returns the total protein consumed
    public int sumProtein() {
        int soFar = 0;
        for (Food f : foods) {
            soFar += f.getProtein();
        }
        return soFar;
    }

    // TODO: is this the best way to do this
    // EFFECTS: sorts foods into a list of list
    //          the list will contain all the food in each meal type in order
    //          breakfast, lunch, dinner, snack
    public ArrayList<ArrayList<Food>> separateFoodTypes() {
        ArrayList<ArrayList<Food>> soFar = new ArrayList<>();
        ArrayList<Food> breakfast = new ArrayList<>();
        ArrayList<Food> lunch = new ArrayList<>();
        ArrayList<Food> dinner = new ArrayList<>();
        ArrayList<Food> snack = new ArrayList<>();
        for (Food f : foods) {
            if (f.getType().equals(MealType.BREAKFAST)) {
                breakfast.add(f);
            } else if (f.getType().equals(MealType.LUNCH)) {
                lunch.add(f);
            } else if (f.getType().equals(MealType.DINNER)) {
                dinner.add(f);
            } else {
                snack.add(f);
            }
        }
        soFar.add(breakfast);
        soFar.add(lunch);
        soFar.add(dinner);
        soFar.add(snack);
        return soFar;
    }

    // EFFECTS: returns the meal data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", getDate());
        json.put("foods", foodsToJson());
        return json;
    }

    // EFFECTS: return the food data as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foods) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
