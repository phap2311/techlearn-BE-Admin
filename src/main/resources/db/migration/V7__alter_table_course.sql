ALTER TABLE tbl_course
DROP COLUMN point;

ALTER TABLE tbl_course
ADD COLUMN public_point INT NULL;

ALTER TABLE tbl_course
ADD COLUMN private_point INT NULL;