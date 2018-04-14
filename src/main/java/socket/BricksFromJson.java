import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import example.Brick;

import java.util.ArrayList;
import java.util.List;

public class BricksFromJson {

    public static List<Brick> getListOfBricksFromJson(String jsonPackage) {
        Gson gson = new Gson();
        ArrayList<Brick> listOfBricks = new ArrayList<>();

        JsonObject jsonObject = gson.fromJson(jsonPackage, JsonObject.class);
        JsonArray bricks = jsonObject.getAsJsonArray("Bricks");

        for (JsonElement brick : bricks) {
            listOfBricks.add(gson.fromJson(brick, Brick.class));
        }

        return listOfBricks;
    }
}
