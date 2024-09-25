CREATE TABLE tbl_mentor
(
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(7),
    avatar VARCHAR(255),
    email VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BIT(1) NULL
);

CREATE TABLE tbl_chapter_mentor
(
    mentor_id BINARY(16) NOT NULL,
    chapter_id BIGINT NOT NULL,
    PRIMARY KEY (mentor_id, chapter_id),
    CONSTRAINT fk_mentor FOREIGN KEY (mentor_id) REFERENCES tbl_mentor(id),
    CONSTRAINT fk_chapter FOREIGN KEY (chapter_id) REFERENCES tbl_chapter(id)
);