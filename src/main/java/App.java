package main.java;

import java.util.Collection;
import java.util.List;

import main.java.model.Estudiante;
import main.java.reader.GsonReader;
import main.java.reader.XMLReader;
import main.java.writer.GsonWriter;
import main.java.writer.XMLWriter;

public class App {

	public static void main(String[] args) {
		
		XMLReader reader = new XMLReader();
		Collection estudiantes = reader.cargarDocumentoEstudiantes("./files/estudiantes.xml", "estudiante");
		
		XMLWriter writer = new XMLWriter();
		
		new GsonWriter().toJSONFile(estudiantes, "./files/estudiantes.json");
		Collection coleccion = new GsonReader().cargarDocumentoEstudiantes("./files/estudiantes.json", null);
		
		writer.escribirDocumentoColeccion(estudiantes, "studiantes.xml");
		writer.escribirDocumentoEstudiantes((Collection<Estudiante>)estudiantes, "estudiantes2.xml");
	}

}
