package main.java.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import main.java.model.Estudiante;



public class GsonWriter {

	
	public void toJSONFile(Collection coleccion, String path) {
		
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();

		try {
			File f = new File(path);
			f.createNewFile();
			Writer w = new FileWriter(f);
			gson.toJson(coleccion, w);
			w.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
