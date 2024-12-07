insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (2, 'aa', 'bb', 'cityyy', '30-630');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (3, 'affa', 'bbsa', 'cisdatyyy', '20-640');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (4, 'xeqwx', 'ydsay', 'city', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (5, 'aewqa', 'bsafb', 'cityycxzy', '30-690');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (6, 'aewqffa', 'bbsrqewdsa', 'cisdaqewdatyyy', '45-640');


insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (1, 'John', 'Doe', '111222333', 'john.doe@mail.com',
                    '123', 'SURGEON', 1);
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (2, 'Johnny', 'Smith', '444555666', 'johnny.smith@mail.com',
                    '456', 'SURGEON', 2);
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (3, 'Adam', 'Washington', '777888999', 'adam.washington@mail.com',
                    '789', 'SURGEON', 3);


insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
            values (1, 'Jane', 'Smith', '333222111', 'jane.smith@mail.com',
        '111', '1990-01-01', 4);
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
            values (2, 'Kathy', 'Old', '666555444', 'kathy.old@mail.com',
        '222', '1990-01-01', 5);
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
            values (3, 'Eve', 'Clinton', '999888777', 'eve.clinton@mail.com',
        '333', '1990-01-01', 6);


insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'Lung check', '2024-10-10 12:00:00', 1, 1);
insert into visit (id, description, time, doctor_id, patient_id)
            values (2, 'Lung check', '2024-10-10 12:00:00', 2, 2);
insert into visit (id, description, time, doctor_id, patient_id)
            values (3, 'Lung check', '2024-10-10 12:00:00', 2, 3);


insert into medical_treatment (id, description, type, visit_id)
            values (1, 'Lung ultrasound', 'USG', 1);
insert into medical_treatment (id, description, type, visit_id)
            values (2, 'Lung ultrasound', 'USG', 2);
insert into medical_treatment (id, description, type, visit_id)
            values (3, 'Lung ultrasound', 'USG', 3);
insert into medical_treatment (id, description, type, visit_id)
            values (4, 'Lung ultrasound 2', 'USG', 3);