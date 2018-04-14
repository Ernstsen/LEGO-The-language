import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class bricksFromJSON {

    public ArrayList<Brick> getListOfBricksFromJSON(String pathToJSON) {
        Gson gson = new Gson();
        ArrayList<Brick> listOfBricks = new ArrayList<>();
        Reader reader = null;

        try {
            reader = new FileReader(pathToJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        JsonArray bricks = jsonObject.getAsJsonArray("Bricks");

        for (JsonElement brick : bricks) {
            listOfBricks.add(gson.fromJson(brick, Brick.class));
        }

        return listOfBricks;
    }
}
