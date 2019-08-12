CREATE TABLE expense_claims
(
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  amount VARCHAR(255) NOT NULL,
  payer_id INT(11) NOT NULL,
  settled_at DATETIME NULL,
  created_at DATETIME NULL,
  PRIMARY KEY (id)
);

CREATE TABLE famex_users
(
  id INT(11) NOT NULL AUTO_INCREMENT,
  display_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);