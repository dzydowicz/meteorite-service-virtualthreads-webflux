CREATE TABLE IF NOT EXISTS meteorite_landings (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    nametype VARCHAR(50),
    recclass VARCHAR(100),
    mass FLOAT,
    fall VARCHAR(50),
    year INT,
    reclat FLOAT,
    reclong FLOAT,
    geolocation VARCHAR(255)
);

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM meteorite_landings LIMIT 1) THEN
        COPY meteorite_landings(name, id, nametype, recclass, mass, fall, year, reclat, reclong, geolocation)
        FROM '/tmp/meteorite_landings.csv'
        DELIMITER ','
        CSV HEADER
        NULL '';
    END IF;
END $$;
