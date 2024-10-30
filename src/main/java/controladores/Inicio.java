package controladores;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.ClubDto;

public class Inicio {

	// Método para cargar datos de JSON
    private List<ClubDto> loadClubs() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/Data.json");
        Map<String, List<ClubDto>> data = mapper.readValue(inputStream, new TypeReference<Map<String, List<ClubDto>>>() {});
        return data.get("clubes");
    }

    // Endpoint  para obtener todos los clubes
    @GetMapping("/clubes")
    public List<ClubDto> getClubs() throws IOException {
        return loadClubs();
    }

    // Endpoint para obtener un club específico por ID
    @GetMapping("/clubes/{id}")
    public ClubDto getClubById(@PathVariable("id") String id) throws IOException {
        return loadClubs().stream()
                .filter(club -> club.getId_club().equals(id))
                .findFirst()
                .orElse(null);  // Si no se encuentra el club, retorna null
    }

    
}
