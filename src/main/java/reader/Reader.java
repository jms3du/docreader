package main.java.reader;

import java.util.Collection;

public interface Reader {

	public Collection cargarDocumentoEstudiantes(String path, String ... rootLabel);
}
