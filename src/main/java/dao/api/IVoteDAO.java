package dao.api;

import dto.SavedVoteDTO;

import java.util.List;

public interface IVoteDAO {

    List<SavedVoteDTO> getAll();

    void save(SavedVoteDTO vote);
}