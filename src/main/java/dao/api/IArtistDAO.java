package dao.api;

import dto.ArtistDTO;

import java.util.List;

public interface IArtistDAO {

    List<ArtistDTO> getAll();

    boolean exists(Long id);

    ArtistDTO get(Long id);

    void add(String artist);

    void update(Long id, String artist,Long version);

    void delete(Long id);
}