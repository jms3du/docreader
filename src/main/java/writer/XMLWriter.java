package main.java.writer;

import static main.java.model.Estudiante.APELLIDOS;
import static main.java.model.Estudiante.FECHA_NACIMIENTO;
import static main.java.model.Estudiante.GENERO;
import static main.java.model.Estudiante.ID;
import static main.java.model.Estudiante.NOMBRE;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.java.model.Estudiante;

public class XMLWriter {
	
	public static final String FILES_RELATIVE_URL = "./files/";
	public static final String FILE_SEPARATOR	  = "\\.";
	public static final String GETTER_PREFFIX	  = "get";
	
	
	
	/**
	 * Recibe una lista de estudiantes y las escribe en formato XML en el fichero especificado en el path
	 * @param estudiantes colección de estudiantes
	 * @param path nombre del documento .xml. Su nombre se incluirá como nodo raíz
	 */
	public void escribirDocumentoEstudiantes(Collection<Estudiante> estudiantes, String path) {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element root = document.createElement(path.split(FILE_SEPARATOR)[0]);
			document.appendChild(root);
			
			for(Estudiante e : estudiantes) {
				Element hijo = document.createElement(e.getClass().getSimpleName().toLowerCase());
				root.appendChild(hijo);
				
				Attr attribute = document.createAttribute(ID);
				attribute.setValue(e.getId().toString());
				
				hijo.setAttributeNode(attribute);
				
				
				Element nombre = document.createElement(NOMBRE);
				nombre.appendChild(document.createTextNode(e.getNombre()));
				hijo.appendChild(nombre);
				
				Element apellidos = document.createElement(APELLIDOS);
				apellidos.appendChild(document.createTextNode(e.getApellidos()));
				hijo.appendChild(apellidos);
				
				Element fechaNacimiento = document.createElement(FECHA_NACIMIENTO);
				fechaNacimiento.appendChild(document.createTextNode(e.getFechaNacimiento().toString()));
				hijo.appendChild(fechaNacimiento);
				
				Element genero = document.createElement(GENERO);
				genero.appendChild(document.createTextNode(e.getGenero().toString()));
				hijo.appendChild(genero);
				
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(FILES_RELATIVE_URL+path));
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			
			
			
		} catch (ParserConfigurationException | IllegalArgumentException |  SecurityException |  TransformerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Escribe una coleccion de datos en el fichero especificado en el path en formato XML
	 * @param coleccion colección de datos
	 * @param path nombre del fichero
	 */
	public void escribirDocumentoColeccion(Collection coleccion, String path) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element root = document.createElement(path.split(FILE_SEPARATOR)[0]);
			document.appendChild(root);
			
			for(Object o : coleccion) {
				Element hijo = document.createElement(o.getClass().getSimpleName().toLowerCase());
				root.appendChild(hijo);
				
				for(Field f : o.getClass().getDeclaredFields()) {
					if(f.getModifiers()==2) {
						Element element = document.createElement(f.getName().toLowerCase());
						for(Method m : Class.forName(o.getClass().getName()).getMethods()) {
							if(m.getName().equalsIgnoreCase(GETTER_PREFFIX + f.getName())){
								element.appendChild(document.createTextNode(String.valueOf(m.invoke(o))));
							}
						}
						hijo.appendChild(element);
						
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(FILES_RELATIVE_URL+path));

			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			
			
		} catch (ParserConfigurationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | ClassNotFoundException | TransformerException e) {
			e.printStackTrace();
		}
		
	}
	

}
