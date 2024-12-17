package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.MedicalTreatmentDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class VisitServiceImpl implements VisitService
{
    private final VisitDao visitDao;

    private final MedicalTreatmentDao medicalTreatmentDao;

    private final DoctorDao doctorDao;

    @Autowired
    public VisitServiceImpl(VisitDao pVisitDao, MedicalTreatmentDao medicalTreatmentDao, DoctorDao doctorDao)
    {
        visitDao = pVisitDao;
        this.medicalTreatmentDao = medicalTreatmentDao;
        this.doctorDao = doctorDao;
    }


    @Override
    public List<VisitTO> findByPatientId(Long id) {
        final List<VisitEntity> entities = visitDao.findByPatientId(id);
        final List<VisitTO> tos = new ArrayList<VisitTO>();
        for (VisitEntity entity : entities) {
            final List<MedicalTreatmentEntity> treatments = medicalTreatmentDao.findByVisitId(entity.getId());
            final List<TreatmentType> treatmentTypes = new ArrayList<>();
            treatments.forEach(s -> treatmentTypes.add(s.getType()));
            final DoctorEntity doctor = doctorDao.findOne(entity.getDoctor().getId());
            final VisitTO to = new VisitTO();
            to.setTime(entity.getTime());
            to.setDoctorFirstName(doctor.getFirstName());
            to.setDoctorLastName(doctor.getLastName());
            to.setTreatmentTypes(treatmentTypes);
            tos.add(to);
        }
        return tos;
    }
}
