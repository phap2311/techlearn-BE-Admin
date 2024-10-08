INSERT INTO tbl_course (created_by, created_date, modified_date, modified_by, name, price, thumbnail_url, public_point, description, currency_unit, is_active, is_public, is_deleted)
VALUES
('admin', NOW(), NOW(), 'admin', 'Java Programming', 199.99, 'java_course_thumbnail.jpg', 100, 'Learn the fundamentals of Java programming.', 'USD', 1, 1, 0),
('admin', NOW(), NOW(), 'admin', 'Web Development with Vue.js', 299.99, 'vuejs_course_thumbnail.jpg', 200, 'Master frontend web development using Vue.js.', 'USD', 1, 1, 0),
('admin', NOW(), NOW(), 'admin', 'Data Structures and Algorithms', 149.99, 'ds_algo_thumbnail.jpg', 150, 'Comprehensive guide to data structures and algorithms.', 'USD', 1, 1, 0);

INSERT INTO tbl_chapter (created_by, created_date, modified_date, modified_by, name, chapter_order, is_public, is_deleted, course_id)
VALUES
('admin', NOW(), NOW(), 'admin', 'Introduction to Java', 1, 1, 0, 1),
('admin', NOW(), NOW(), 'admin', 'Control Structures', 2, 1, 0, 1),
('admin', NOW(), NOW(), 'admin', 'Getting Started with Vue.js', 3, 1, 0, 2),
('admin', NOW(), NOW(), 'admin', 'Components and Data Binding', 4, 1, 0, 2),
('admin', NOW(), NOW(), 'admin', 'Introduction to Algorithms', 5, 1, 0, 3);


INSERT INTO tbl_lesson (created_by, created_date, modified_date, modified_by, title, type, lesson_order, content, video_url, content_refer, is_deleted, chapter_id)
VALUES
('admin', NOW(), NOW(), 'admin', 'Java Basics', 'EXERCISES', 1, 'Introduction to Java Basics', 'java_basics_video.mp4', 'https://reference.com/java_basics', 0, 1),
('admin', NOW(), NOW(), 'admin', 'If Statements', 'LECTURES', 2, 'Control flow with If Statements', 'if_statements_video.mp4', 'https://reference.com/java_if', 0, 1),
('admin', NOW(), NOW(), 'admin', 'Vue.js Basics', 'READINGS', 3, 'Introduction to Vue.js framework', 'vuejs_intro_video.mp4', 'https://reference.com/vue_intro', 0, 1);

INSERT INTO tbl_tech_stack (created_by, created_date, modified_date, modified_by, name, is_deleted)
VALUES
('admin', NOW(), NOW(), 'admin', 'Java', 0),
('admin', NOW(), NOW(), 'admin', 'Vue.js', 0),
('admin', NOW(), NOW(), 'admin', 'Spring Boot', 0);


INSERT INTO tbl_course_tech_stack (id_tech_stack, id_course)
VALUES
(1, 1), -- Java for Java Programming course
(2, 2), -- Vue.js for Web Development with Vue.js
(3, 1); -- Spring Boot for Java Programming