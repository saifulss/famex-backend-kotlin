CREATE TABLE expense_claims
(
    id          INT(11)      NOT NULL AUTO_INCREMENT,
    amount      VARCHAR(255) NOT NULL,
    category_id INT(11)      NOT NULL,
    description VARCHAR(255) NULL,
    payer_id    INT(11)      NOT NULL,
    settled_at  DATETIME     NULL,
    created_at  DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id           INT(11)      NOT NULL AUTO_INCREMENT,
    display_name VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id   INT(11)      NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);