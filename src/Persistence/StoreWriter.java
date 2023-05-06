package Persistence;

import Model.Store;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StoreWriter {
    private static final int TAB = 4;
    private String destination;
    private PrintWriter writer;

    public StoreWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(Store store) {
        JSONObject json = store.toJson();
        saveToFile(json.toString(TAB));
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

    public void close() {
        writer.close();
    }
}
