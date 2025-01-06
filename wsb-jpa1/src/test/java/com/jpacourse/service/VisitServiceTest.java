package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;


    @Transactional
    @Test
    public void testShouldRemovePatientAndCheckVisitsPlusDoctors(){
        // given
        Long patientId = 1L;

        // when
        List<VisitTO> visits = visitService.findByPatientId(patientId);

        // then
        assertThat(visits).isNotEmpty();
        assertThat(visits.size()).isEqualTo(2);
    }

}
