package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface VisitService
{
    public List<VisitTO> findByPatientId(final Long id);
}