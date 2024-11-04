-- Crear la tabla Vehicle primero
CREATE TABLE Vehicle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(15) NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    mileage INT DEFAULT 0,
    fuel_consumption DECIMAL(5, 2) DEFAULT 0.0
);

-- Crear la tabla Driver
CREATE TABLE Driver (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    license_number VARCHAR(20) NOT NULL UNIQUE
);

-- Crear la tabla Rental, que referencia a Vehicle y Driver
CREATE TABLE Rental (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id BIGINT,
    driver_id BIGINT,
    rental_date DATE NOT NULL,
    return_date DATE,
    total_distance INT DEFAULT 0,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id),
    FOREIGN KEY (driver_id) REFERENCES Driver(id)
);

CREATE TABLE Maintenance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id BIGINT,
    maintenance_date DATE NOT NULL,
    description TEXT,
    cost DECIMAL(10, 2) DEFAULT 0.0,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id)
);

-- Tabla Role para definir roles
CREATE TABLE Role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla User para los usuarios
CREATE TABLE User (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla intermedia para la relación User-Role (muchos a muchos)
CREATE TABLE User_Role (
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (role_id) REFERENCES Role(id),
    PRIMARY KEY (user_id, role_id)
);

