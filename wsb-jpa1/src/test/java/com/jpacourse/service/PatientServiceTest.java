package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.jpacourse.dto.PatientTO;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldRemovePatientAndCheckVisitsPlusDoctors(){
        // given
        // when
        final PatientTO patient = patientService.findById(1L);
        assertThat(patient).isNotNull();
        final List<VisitEntity> visits = visitDao.findByPatientId(1L);
        assertThat(visits).isNotEmpty();
        final List<DoctorEntity> doctors = new ArrayList<>();
        visits.forEach(visit -> doctors.add(visit.getDoctor()));
        assertThat(doctors).isNotEmpty();

        patientService.deleteById(patient.getId());
        // then
        final PatientTO removedPatient = patientService.findById(1L);
        assertThat(removedPatient).isNull();
        final List<VisitEntity> removedVisits = visitDao.findByPatientId(1L);
        assertThat(removedVisits).isEmpty();
        for(final DoctorEntity doctor : doctors){
            final Long doctorId = doctor.getId();
            assertThat(doctorDao.findOne(doctorId)).isNotNull();
        }
    }

    @Transactional
    @Test
    public void testShouldFindPatientAndCheckStructure(){
        // given
        // when
        final PatientTO patient = patientService.findById(1L);
        assertThat(patient).isNotNull();
        // then
        assertThat(patient.getVisits()).isNotEmpty();
        assertThat(patient.getVisits().get(0)).isNotNull();
        assertThat(patient.getVisits().get(0).getDoctorFirstName()).isNotNull();
        assertThat(patient.getVisits().get(0).getDoctorLastName()).isNotNull();
        assertThat(patient.getVisits().get(0).getTime()).isNotNull();
        assertThat(patient.getVisits().get(0).getTreatmentTypes()).isNotEmpty();
        assertThat(patient.getVisits().get(0).getTreatmentTypes().get(0)).isNotNull();
        assertThat(patient.getVisits().get(0).getTreatmentTypes().get(0)).isInstanceOf(TreatmentType.class);
    }
}
