ALTER TABLE tbl_teacher
    ADD COLUMN email VARCHAR(255) NOT NULL;

Alter table tbl_user
    add column avatar     varchar(255) null,
    ADD COLUMN email VARCHAR(255) NOT NULL;

ALTER TABLE tbl_teacher
    ADD COLUMN user_id BINARY(16),
    ADD CONSTRAINT fk_user_teacher
    FOREIGN KEY (user_id) REFERENCES tbl_user(id);

ALTER TABLE tbl_mentor
    ADD COLUMN user_id BINARY(16),
    ADD CONSTRAINT fk_user_mentor
        FOREIGN KEY (user_id) REFERENCES tbl_user(id);

insert into tbl_roles (id, name)
values (1, 'ADMIN'),
       (2, 'TEACHER'),
       (3, 'MENTOR'),
       (4, 'USER');



