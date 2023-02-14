package controllers;

import dto.ArtistDTO;
import org.springframework.web.bind.annotation.*;
import service.api.IArtistService;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final IArtistService service;

    public ArtistController(IArtistService artistService) {
        this.service = artistService;
    }

    @GetMapping
    public List<ArtistDTO> getAll() {
        return service.getAll();
    }

    @PostMapping(path = "/{artist}")
    public void add(@PathVariable("artist") String artist) {
        service.add(artist);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}/{artist}/version/{version}")
    public void update(@PathVariable("id") Long id,
                       @PathVariable("artist") String artist,
                       @PathVariable("version") Long version) {
        service.update(id, artist, version);
    }

}
