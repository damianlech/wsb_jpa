package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao
{

    @Override
    public List<VisitEntity> findByPatientId(Long patientId) {
        String query = "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId";
        TypedQuery<VisitEntity> typedQuery = entityManager.createQuery(query, VisitEntity.class);
        typedQuery.setParameter("patientId", patientId);
        return typedQuery.getResultList();
    }
}
