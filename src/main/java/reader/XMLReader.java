package main.java.reader;

import static main.java.model.Estudiante.APELLIDOS;
import static main.java.model.Estudiante.FECHA_NACIMIENTO;
import static main.java.model.Estudiante.GENERO;
import static main.java.model.Estudiante.ID;
import static main.java.model.Estudiante.NOMBRE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.java.model.Estudiante;
import main.java.model.Genero;

public class XMLReader implements Reader {
		
	
	public Collection cargarDocumentoEstudiantes(String path, String ... rootLabel ){
		
		List<Estudiante> estudiantes = new ArrayList<>();
		
		try {
			File file = new File(path);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			Document document = builder.parse(file);
			// Puede utilizarse la llamada 
			// document = getDocument(file);
			document.getDocumentElement().normalize();
			
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			NodeList nodeList = document.getElementsByTagName(rootLabel[0]);
			
			for(int i=0; i<nodeList.getLength(); i++) {

				Element e = (Element)nodeList.item(i);
				
				//Atributo situado en la etiqueta de clase
				int id = Integer.valueOf(e.getAttribute(ID));
				
				//Elementos situados como nodos dentro de la clase
				String nombre = e.getElementsByTagName(NOMBRE).item(0).getTextContent();
				String apellidos = e.getElementsByTagName(APELLIDOS).item(0).getTextContent();
				String fechaNacimiento = e.getElementsByTagName(FECHA_NACIMIENTO).item(0).getTextContent();
				Genero genero = Genero.valueOf(e.getElementsByTagName(GENERO).item(0).getTextContent());
				
				
				Estudiante estudiante = new Estudiante(id, nombre, apellidos, fechaNacimiento, genero);
				System.out.println(estudiante);
				estudiantes.add(estudiante);
			}

			
			
			
		} catch (ParserConfigurationException  | SAXException | IOException e) {
			e.printStackTrace();
		} 
		
		
		
		return estudiantes;
	}
	
	
	public Document getDocument(File file) throws SAXException, IOException, ParserConfigurationException {
		return 	DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
	}

}
