
CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        cin VARCHAR(50) unique not null,
        name VARCHAR(100) NOT NULL,
        age INT NOT NULL
);


CREATE TYPE consumption_type AS ENUM ('TRANSPORT', 'HOUSING', 'FOOD');


CREATE TABLE consumption (
        id SERIAL PRIMARY KEY,
        start_date DATE NOT NULL,
        end_date DATE NOT NULL,
        value FLOAT NOT NULL,
        consumption_impact DOUBLE PRECISION,
        consumption_type consumption_type NOT NULL,
        user_id BIGINT REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE transport (
        id SERIAL PRIMARY KEY,
        distance_traveled DOUBLE PRECISION NOT NULL,
        vehicle_type VARCHAR(50) NOT NULL,
        consumption_id INT REFERENCES consumption(id) ON DELETE CASCADE
);


CREATE TABLE housing (
        id SERIAL PRIMARY KEY,
        energy_consumption DOUBLE PRECISION NOT NULL,
        energy_type VARCHAR(50) NOT NULL,
        consumption_id INT REFERENCES consumption(id) ON DELETE CASCADE
);


CREATE TABLE food (
        id SERIAL PRIMARY KEY,
        type_of_food VARCHAR(100) NOT NULL,
        weight DOUBLE PRECISION NOT NULL,
        consumption_id INT REFERENCES consumption(id) ON DELETE CASCADE
);

-- add new users
INSERT INTO users (cin,name, age)
VALUES ('p123dfgh','Ibtissam elh', 23),
       ('p123fvvb','nabil Smith', 25),
       ('p123vbn','mbarek Johnson', 40);



