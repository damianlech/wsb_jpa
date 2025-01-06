package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void shouldThrowOptimisticLockingException(){
        // given
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setTelephoneNumber("111111111");
        doctor.setEmail("john@doe.com");
        doctor.setDoctorNumber("556");
        doctor.setSpecialization(Specialization.SURGEON);
        entityManager.persist(doctor);
        entityManager.flush();

        // when
        DoctorEntity doctor1 = entityManager.find(DoctorEntity.class, doctor.getId());

        DoctorEntity doctor2 = entityManager.find(DoctorEntity.class, doctor.getId());

        entityManager.clear();

        doctor1.setFirstName("test1");
        entityManager.merge(doctor1);
        entityManager.flush();

        doctor2.setFirstName("test2");

        // then

        assertThrows(OptimisticLockException.class, () -> {
            entityManager.merge(doctor2);
            entityManager.flush();
        });
    }
}
