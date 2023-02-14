package dao.database;

import dao.api.IVoteDAO;
import dao.entity.ArtistEntity;
import dao.entity.GenreEntity;
import dao.entity.VoteEntity;
import dao.util.ConnectionManager;
import dto.SavedVoteDTO;
import dto.VoteDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class VoteDBDAO implements IVoteDAO {
    private final ConnectionManager connectionManager;

    public VoteDBDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<SavedVoteDTO> getAll() {
        List<SavedVoteDTO> list = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<VoteEntity> criteria = cb.createQuery(VoteEntity.class);
            Root<VoteEntity> votes = criteria.from(VoteEntity.class);
            criteria.select(votes);
            List<VoteEntity> resultList = entityManager.createQuery(criteria).getResultList();
            resultList.forEach(vote -> list.add(new SavedVoteDTO(vote)));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    @Override
    public void save(SavedVoteDTO vote) {
        VoteDTO voteDTO = vote.getVoteDTO();
        VoteEntity voteEntity = new VoteEntity(vote);
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, voteDTO.getArtistId());
            voteEntity.setArtistEntity(artistEntity);
            for (Long genreId : voteDTO.getGenreIds()) {
                GenreEntity genreEntity = entityManager.find(GenreEntity.class, genreId);
                if (genreEntity == null) {
                    throw new NoSuchElementException("there is no nominated genre with this id, " +
                            "your vote is not counted try again");
                } else {
                    voteEntity.addGenre(genreEntity);
                }
            }
            entityManager.persist(voteEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
