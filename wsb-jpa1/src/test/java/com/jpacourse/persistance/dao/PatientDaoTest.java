package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void shouldAddVisitToPatient(){
        // given
        Long patientId = 1L;
        Long doctorId = 2L;
        LocalDateTime visitDate = LocalDateTime.now();
        String description = "Test description";

        // when
        patientDao.addVisitToPatient(patientId, doctorId, visitDate, description);

        // then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        VisitEntity visit = patient.getVisits().get(patient.getVisits().size() - 1);
        assertThat(visit).isNotNull();
        assertThat(visit.getDescription()).isEqualTo(description);
        assertThat(visit.getTime()).isEqualTo(visitDate);
        assertThat(visit.getPatient().getId()).isEqualTo(patientId);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctorId);
    }

    @Transactional
    @Test
    public void shouldFindPatientsByLastName(){
        // given
        String lastName = "Smith";

        // when
        List<PatientEntity> patients = patientDao.getPatientsByLastName(lastName);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.get(0).getLastName()).isEqualTo(lastName);
        assertThat(patients.get(1).getLastName()).isEqualTo(lastName);
        assertThat(patients.get(0).getId()).isEqualTo(1);
        assertThat(patients.get(1).getId()).isEqualTo(4);
    }

    @Transactional
    @Test
    public void shouldFindPatientsWithNumberOfVisitsHigherThan(){
        // given
        Long numberOfVisits = 1L;

        // when
        List<PatientEntity> patients = patientDao.getPatientsHavingMoreThanGivenVisits(numberOfVisits);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.get(0).getId()).isEqualTo(1);
    }

    @Transactional
    @Test
    public void shouldFindPatientsWithCreationDateLaterThan(){
        // given
        Timestamp creationDate = Timestamp.valueOf("2022-01-01 00:00:00");

        // when
        List<PatientEntity> patients = patientDao.getPatientsCreatedAfter(creationDate);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.get(0).getId()).isEqualTo(2);
        assertThat(patients.get(1).getId()).isEqualTo(3);
    }
}
