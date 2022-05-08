package main.java.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import main.java.model.Estudiante;

public class GsonReader implements Reader {

	@Override
	public Collection cargarDocumentoEstudiantes(String path, String ... rootLabel ) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Collection<Estudiante> result = new ArrayList<>();
		try {
			result.addAll(Arrays.asList(gson.fromJson(new FileReader(new File(path)), Estudiante[].class)));
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
