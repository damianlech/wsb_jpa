package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.MedicalTreatmentDao;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MedicalTreatmentDaoImpl extends AbstractDao<MedicalTreatmentEntity, Long> implements MedicalTreatmentDao
{
    @Override
    public List<MedicalTreatmentEntity> findByVisitId(Long visitId) {
        String query = "SELECT v FROM MedicalTreatmentEntity v WHERE v.visit.id = :visitId";
        TypedQuery<MedicalTreatmentEntity> typedQuery = entityManager.createQuery(query, MedicalTreatmentEntity.class);
        typedQuery.setParameter("visitId", visitId);
        return typedQuery.getResultList();
    }
}
