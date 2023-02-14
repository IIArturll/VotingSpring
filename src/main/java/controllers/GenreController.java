package controllers;

import dto.GenreDTO;
import org.springframework.web.bind.annotation.*;
import service.api.IGenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final IGenreService service;
    public GenreController(IGenreService genreService) {
        this.service = genreService;
    }

    @GetMapping()
    public List<GenreDTO> getAll() {
        return service.getAll();
    }

    @PostMapping(path = "/{genre}")
    public void add(@PathVariable("genre") String genre) {
        service.add(genre);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}/{genre}/version/{version}")
    public void update(@PathVariable("id") Long id,
                       @PathVariable("genre") String genre,
                       @PathVariable("version") Long version) {
        service.update(id, genre,version);
    }

}
