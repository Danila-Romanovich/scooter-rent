CREATE TABLE scooter(
                        scooterId SERIAL PRIMARY KEY,
                        brandName VARCHAR(50) NOT NULL,
                        model VARCHAR(50) NOT NULL,
                        maxSpeed INT NOT NULL,
                        isActive BOOLEAN DEFAULT True
);