CREATE TABLE scooter(
    scooterId SERIAL PRIMARY KEY,
    brandName VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    maxSpeed INT NOT NULL,
    isActive BOOLEAN DEFAULT True
);

CREATE TABLE session (
    sessionId SERIAL PRIMARY KEY,
    scooterId INT REFERENCES scooter(scooterId) NOT NULL,
    dateStart TIMESTAMP NOT NULL,
    dateEnd TIMESTAMP NOT NULL
);

