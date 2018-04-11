package ru.otus.HW08;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 01.04.2018.
 */
public class JsonObjectWriterTest {

    private JsonObjectWriter jsonObjectWriter = new JsonObjectWriter();
    private Gson gson = new Gson();

    @Test
    public void primitiveFields() throws Exception {
        BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();
        String json = jsonObjectWriter.toJson(bagOfPrimitives);

        assertEquals(gson.toJson(bagOfPrimitives), json);
    }

    @Test
    public void arrayFields() throws Exception {
        BagWithArray bagWithArray = new BagWithArray();
        String json = jsonObjectWriter.toJson(bagWithArray);

        assertEquals(gson.toJson(bagWithArray), json);
    }

    @Test
    public void objrctFields() throws Exception {
        BagWithObject bagWithObject = new BagWithObject();
        String json = jsonObjectWriter.toJson(bagWithObject);
        System.out.println(json);
        assertEquals(gson.toJson(bagWithObject), json);
    }
}