package com.projeto.departamentoSpringBoot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



//T eh um objeto | pk eh um id
public class AbstractDao<T, PK extends Serializable> {

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    //metodo para salvar
    public void save(T entity) {

        entityManager.persist(entity);

    }

    //metodo para editar
    public void update(T entity) {

        entityManager.merge(entity);

    }

    //metodo para excluir
    public void delete(PK id) {

        entityManager.remove(entityManager.getReference(entityClass, id));

    }

    //Busca apenas um usuario
    public T findById(PK id) {

        return entityManager.find(entityClass, id);

    }

    //metodo para buscar todos os objetos
    public List<T> findAll() {

        return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();

    }

    
    protected List<T> createQuery(String jpql, Object... params) {

        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        
        return query.getResultList();
    }

}
