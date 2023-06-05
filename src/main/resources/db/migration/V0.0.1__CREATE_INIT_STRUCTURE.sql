CREATE TABLE `user`(
                     ID BIGINT(20) AUTO_INCREMENT,
                     NAME VARCHAR(100),
                     SURNAME VARCHAR(100),
                     USERID VARCHAR(50),
                     PASS VARCHAR(256),
                     CREATED_AT DATE,
                     UPDATED_AT DATE,
                     DELETED_AT DATE,
                     PRIMARY KEY (ID)
);

CREATE TABLE role(
    ID INT AUTO_INCREMENT,
    ROLE_TYPE VARCHAR(50),
    PRIMARY KEY (ID)
);

CREATE TABLE user_role(
    USER_ID BIGINT(20),
    ROLE_ID INT,
    PRIMARY KEY (USER_ID, ROLE_ID),
    FOREIGN KEY (USER_ID) REFERENCES `user`(ID),
    FOREIGN KEY (ROLE_ID) REFERENCES role(ID)
);

CREATE TABLE state_type(
                           ID INT(11) AUTO_INCREMENT,
                           NAME VARCHAR(100),
                           CREATED_AT DATE,
                           UPDATED_AT DATE,
                           DELETED_AT DATE,
                           PRIMARY KEY (ID)
);

CREATE TABLE animal(
                       ID BIGINT(20) AUTO_INCREMENT,
                       NAME VARCHAR(100),
                       CHIP VARCHAR(100),
                       RACE VARCHAR(100),
                       AGALLAS_DATE DATE,
                       ORIGIN VARCHAR(100),
                       DESCRIPTION VARCHAR(100),
                       PHOTO LONGBLOB,
                       LAST_INTERNAL_DEWORMING DATE,
                       LAST_EXTERNAL_DEWORMING DATE,
                       FIRST_VACINE BIT,
                       SECOND_VACINE BIT,
                       GENERAL_ANALYTICS BIT,
                       MEDITERRANEANS BIT,
                       CASTRATION BIT,
                       PHOTO_GAL1 LONGBLOB,
                       PHOTO_GAL2 LONGBLOB,
                       PHOTO_GAL3 LONGBLOB,
                       PHOTO_GAL4 LONGBLOB,
                       CREATED_AT DATE,
                       UPDATED_AT DATE,
                       DELETED_AT DATE,
                       PRIMARY KEY (ID)
);

CREATE TABLE animal_state(
                             ID BIGINT(20) AUTO_INCREMENT,
                             STATE_TYPE INT(11),
                             START_DATE DATE,
                             END_DATE DATE,
                             ANIMAL BIGINT(20),
                             CREATED_AT DATE,
                             UPDATED_AT DATE,
                             DELETED_AT DATE,
                             PRIMARY KEY (ID),
                             FOREIGN KEY (STATE_TYPE) REFERENCES state_type(ID),
                             FOREIGN KEY (ANIMAL) REFERENCES animal(ID)
);