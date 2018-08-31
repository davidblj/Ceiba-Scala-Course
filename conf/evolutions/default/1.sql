# --- !Ups

CREATE TABLE humans (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    ethnicity VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE "humans" IF EXISTS;
