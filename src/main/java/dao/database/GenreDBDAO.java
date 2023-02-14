package dao.database;

import dao.api.IGenreDAO;
import dao.entity.GenreEntity;
import dao.util.ConnectionManager;
import dto.GenreDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenreDBDAO implements IGenreDAO {
    private final ConnectionManager connectionManager;

    public GenreDBDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<GenreDTO> getAll() {
        List<GenreDTO> list = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<GenreEntity> criteria = cb.createQuery(GenreEntity.class);
            Root<GenreEntity> genres = criteria.from(GenreEntity.class);
            criteria.select(genres);
            entityManager.createQuery(criteria).getResultStream()
                    .forEach(genre -> list.add(new GenreDTO(genre.getId(), genre.getGenre(), genre.getVersion())));
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
    public boolean exists(Long id) {
        boolean bool = false;
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            if (entityManager.find(GenreEntity.class, id) != null) {
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
        return bool;
    }

    @Override
    public GenreDTO get(Long id) {
        EntityManager entityManager = null;
        GenreDTO genreDTO;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            genreDTO = new GenreDTO(entityManager.find(GenreEntity.class, id));
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
        return genreDTO;
    }

    @Override
    public void add(String genre) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            entityManager.persist(new GenreEntity(genre));
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
    public void update(Long id, String genre, Long version) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.open();
            entityManager.getTransaction().begin();
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            if (genreEntity.getVersion().equals(version)) {
                genreEntity.setGenre(genre);
                genreEntity.setVersion(new Date().getTime());
            } else {
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
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.remove(genreEntity);
            entityManager.flush();
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