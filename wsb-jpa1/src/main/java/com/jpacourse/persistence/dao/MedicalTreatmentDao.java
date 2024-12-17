package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.MedicalTreatmentEntity;

import java.util.List;

public interface MedicalTreatmentDao extends Dao<MedicalTreatmentEntity, Long>
{
    public List<MedicalTreatmentEntity> findByVisitId(Long visitId);
}
