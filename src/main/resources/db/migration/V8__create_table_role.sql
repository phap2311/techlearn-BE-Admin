CREATE TABLE tbl_roles
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NULL,
    CONSTRAINT pk_tbl_roles PRIMARY KEY (id)
);

CREATE TABLE tbl_user_roles
(
    user BINARY(16) NOT NULL,
    role INT NOT NULL,
    PRIMARY KEY (user, role),
    FOREIGN KEY (user) REFERENCES tbl_user(id) ON DELETE CASCADE,
    FOREIGN KEY (role) REFERENCES tbl_roles(id) ON DELETE CASCADE
);