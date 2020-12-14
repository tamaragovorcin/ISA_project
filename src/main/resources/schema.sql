DROP TABLE IF EXISTS hospitals;
CREATE TABLE hospitals(api_key VARCHAR(255) PRIMARY KEY, "name" VARCHAR(255));

DROP TABLE IF EXISTS pharmacies;
CREATE TABLE pharmacies(api_key VARCHAR(255) PRIMARY KEY, "name" VARCHAR(255), town VARCHAR(255));

DROP TABLE IF EXISTS medicines;
CREATE TABLE medicines(id SERIAL PRIMARY KEY, "name" VARCHAR(255), description VARCHAR(255), unwantedReactions VARCHAR(255));


DROP TABLE IF EXISTS medicinesPharmacy;
CREATE TABLE medicinesPharmacy(id SERIAL PRIMARY KEY, pharmacyApi VARCHAR(255), medicineName VARCHAR(255), quantity INT);