package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;

    private final VisitService visitService;


    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao, VisitService visitService)
    {
        patientDao = pPatientDao;
        this.visitService = visitService;
    }


    @Override
    public PatientTO findById(Long id) {
        final PatientEntity patientEntity = patientDao.findOne(id);
        final List<VisitTO> visits = visitService.findByPatientId(id);

        return PatientMapper.mapToTO(patientEntity, visits);
    }

    @Override
    public void deleteById(Long id) {
        PatientEntity patientEntity = patientDao.findOne(id);
        if (patientEntity != null){
            patientDao.delete(id);
            return;
        }
        throw new EntityNotFoundException(id);
    }

}

