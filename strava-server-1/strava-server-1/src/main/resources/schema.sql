CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    weight FLOAT,
    height FLOAT,
    max_heart_rate INT,
    rest_heart_rate INT,
    token VARCHAR(255) NOT NULL
);

CREATE TABLE challenges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    start_date VARCHAR(255) NOT NULL,
    end_date VARCHAR(255) NOT NULL,
    target FLOAT NOT NULL,
    target_type VARCHAR(255) NOT NULL,
    sport VARCHAR(255) NOT NULL,
    creator_id BIGINT NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES users (id)
);


