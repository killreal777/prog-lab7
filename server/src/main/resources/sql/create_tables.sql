CREATE TABLE IF NOT EXISTS organizations (
    organization_id             INT         NOT NULL UNIQUE PRIMARY KEY,
    organization_name           TEXT        NOT NULL,
    organization_full_name      TEXT        NOT NULL UNIQUE,
    organization_coordinate_x   INT         NOT NULL CHECK (organization_coordinate_x > -535),
    organization_coordinate_y   INT         NOT NULL CHECK (organization_coordinate_y <= 630),
    annual_turnover             BIGINT      NOT NULL CHECK (annual_turnover > 0),
    employees_count             INT         NOT NULL CHECK (employees_count > 0),
    organization_type           INT         NOT NULL CHECK (organization_type IN (0, 1, 2, 3)),
    zip_code                    VARCHAR(16) NOT NULL,
    location_coordinate_x       BIGINT      NOT NULL,
    location_coordinate_y       INT         NOT NULL,
    location_coordinate_z       FLOAT       NOT NULL,
    location_name               TEXT        NOT NULL
);

CREATE TABLE IF NOT EXISTS users ();