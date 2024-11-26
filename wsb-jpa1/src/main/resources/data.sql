insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (2, 'xxx', 'yyy', 'cityyy', '03-062');
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (1, 'John', 'Doe', '111222333', 'john.doe@mail.com',
                    '123', 'SURGEON', 1);
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
            values (1, 'Adam', 'Smith', '444555666', 'adam.smith@mail.com',
        '666', '1990-01-01', 2);
insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'Lung check', '2024-10-10 12:00:00', 1, 1);
insert into medical_treatment (id, description, type, visit_id)
            values (1, 'Lung ultrasound', 'USG', 1);