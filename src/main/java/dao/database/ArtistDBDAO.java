package dao.database;

import dao.api.IArtistDAO;
import dao.entity.ArtistEntity;
import dao.util.ConnectionManager;
import dto.ArtistDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtistDBDAO implements IArtistDAO {
private final ConnectionManager connectionManager;

    public ArtistDBDAO(ConnectionManager connectionManager) {
        this.connectionManager=connectionManager;
    }

    @Override
    public List<ArtistDTO> getAll() {
        List<ArtistDTO> list = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ArtistEntity> criteria = cb.createQuery(ArtistEntity.class);
            Root<ArtistEntity> artist = criteria.from(ArtistEntity.class);
            criteria.select(artist);
            entityManager.createQuery(criteria).getResultStream()
                    .forEach(artistEntity -> list.add(
                            new ArtistDTO(artistEntity.getId(), artistEntity.getArtist(),artistEntity.getVersion())));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction(). rollback();
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
    public boolean exists(Long id) {
        boolean bool = false;
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            if (entityManager.find(ArtistEntity.class, id) != null) {
                bool = true;
            }
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
        entityManager.close();
        return bool;
    }

    @Override
    public ArtistDTO get(Long id) {
        EntityManager entityManager = null;
        ArtistDTO artistDTO;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);
            artistDTO = new ArtistDTO(artistEntity);
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

        return artistDTO;
    }

    @Override
    public void add(String artist) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            entityManager.persist(new ArtistEntity(artist));
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

    @Override
    public void update(Long id, String artist,Long version) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();

            entityManager.getTransaction().begin();
            ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);
            if(artistEntity.getVersion().equals(version)) {
                artistEntity.setArtist(artist);
                artistEntity.setVersion(new Date().getTime());
            }else {
                throw new IllegalArgumentException("versions don't match");
            }
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

    @Override
    public void delete(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            ArtistEntity artistEntity = entityManager.find(ArtistEntity.class, id);
            entityManager.remove(artistEntity);
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