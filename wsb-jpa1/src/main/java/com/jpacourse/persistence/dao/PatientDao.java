package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{

    @Transactional
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription);

    @Transactional
    List<PatientEntity> getPatientsByLastName(String lastName);

    @Transactional
    List<PatientEntity> getPatientsHavingMoreThanGivenVisits(Long numberOfVisits);
}
