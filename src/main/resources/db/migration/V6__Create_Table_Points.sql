CREATE TABLE tbl_currency
(
    id            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    units          VARCHAR(255) NULL,
    is_deleted    BIT(1) NULL
);

    CREATE TABLE tbl_points
    (
        id            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        created_by    VARCHAR(255) NULL,
        created_date  datetime NULL,
        modified_date datetime NULL,
        modified_by   VARCHAR(255) NULL,
        name          VARCHAR(255) NULL,
        points        INT NULL,
        price         DECIMAL(10, 2) NULL,
        id_currency   INT NULL,
        is_deleted    BIT(1) NULL,
        CONSTRAINT fk_currency FOREIGN KEY (id_currency) REFERENCES tbl_currency(id)
);
-- CREATE TABLE tbl_student_point
-- (
--     id            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
--     created_by    VARCHAR(255) NULL,
--     created_date  datetime NULL,
--     modified_date datetime NULL,
--     modified_by   VARCHAR(255) NULL,
--     id_points   INT NULL,
--     id_user       BINARY(16)            NULL,
--     is_deleted    BIT(1) NULL,
--     CONSTRAINT fk_points FOREIGN KEY (id_points) REFERENCES tbl_points(id),
--     CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES tbl_user(id)
-- );
INSERT INTO tbl_user (id, created_by, created_date, modified_date, modified_by, full_name, age, is_deleted)
VALUES
    (UUID_TO_BIN(UUID()), 'admin', NOW(), NOW(), 'admin', 'Vi trần', 30, 0);
INSERT INTO tbl_teacher (id, name, color, avatar, created_by, created_date, modified_by, modified_date, is_deleted)
VALUES
    (UUID_TO_BIN(UUID()), 'Teacher A', '#FF5733', 'avatar1.png', 'admin', NOW(), 'admin', NOW(), 0),
    (UUID_TO_BIN(UUID()), 'Teacher B', '#33FF57', 'avatar2.png', 'admin', NOW(), 'admin', NOW(), 0),
    (UUID_TO_BIN(UUID()), 'Teacher C', '#3357FF', 'avatar3.png', 'admin', NOW(), 'admin', NOW(), 0);

INSERT INTO tbl_currency (created_by, created_date, modified_date, modified_by, units, is_deleted)
VALUES
    ('admin', NOW(), NOW(), 'admin', 'USD', 0),
    ('admin', NOW(), NOW(), 'admin', 'EUR', 0),
    ('admin', NOW(), NOW(), 'admin', 'VND', 0);

INSERT INTO tbl_points (created_by, created_date, modified_date, modified_by, name, points, price, id_currency, is_deleted)
VALUES
    ('admin', NOW(), NOW(), 'admin', 'Points Package A', 100, 9.99, 1, 0),
    ('admin', NOW(), NOW(), 'admin', 'Points Package B', 200, 19.99, 1, 0),
    ('admin', NOW(), NOW(), 'admin', 'Points Package C', 500, 49.99, 2, 0);


-- SET @vi_tran_id = (SELECT id FROM tbl_user WHERE email = 'tieuvi200904@gmail.com');
-- INSERT INTO tbl_student_point (created_by, created_date, modified_date, modified_by, id_points, id_user, is_deleted)
-- VALUES
--     ('admin', NOW(), NULL, NULL, 1,  @vi_tran_id, 0),
--     ('admin', NOW(), NULL, NULL, 2,  @vi_tran_id, 0),
--     ('admin', NOW(), NULL, NULL, 3,  @vi_tran_id, 0);



