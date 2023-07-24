package model.workout;

import model.Data;
import model.Date;
import model.exceptions.DoesNotExist;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// A database for all workouts performed
public class AllWorkouts implements Data, Writable {
    private List<Workout> allWorkouts;

    // EFFECTS: constructs a new AllWorkouts with no workouts added
    public AllWorkouts() {
        this.allWorkouts = new ArrayList<>();
    }

    // getters:
    public List<Workout> getWorkouts() {
        return this.allWorkouts;
    }

    // MODIFIES: this
    // EFFECTS: adds workout to list of workouts
    public void addWorkout(Workout workout) {
        this.allWorkouts.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: removes workout from list of workouts
    public void removeWorkout(Workout workout) {
        this.allWorkouts.remove(workout);
    }

    // EFFECTS: produces true if there is a workout from today
    @Override
    public boolean today() {
        Date date = new Date();
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date.getDate())) {
                return true;
            }
        }
        return false;
    }


    // EFFECTS: return the workout with the given date from list
    //          if workout doesn't exist, throws DoesNotExist exception
    public Workout retrieveWorkout(String date) throws DoesNotExist {
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date)) {
                return w;
            }
        }
        throw new DoesNotExist();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workouts", workoutsToJson());
        return json;
    }

    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : allWorkouts) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }
}
