package des.kanban.entidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomListDeserializer extends StdDeserializer<List<Tarea>>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomListDeserializer() {
        this(null);
    }

    public CustomListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Tarea> deserialize(
      JsonParser jsonparser, 
      DeserializationContext context) 
      throws IOException, JsonProcessingException {
        
        return new ArrayList<>();
    }
}