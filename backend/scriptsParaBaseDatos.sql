CREATE USER 'application'@'%' IDENTIFIED BY 'Applic@ti0nC0nnection123';
CREATE SCHEMA securitybd;
GRANT SELECT, INSERT, UPDATE, DELETE ON securitybd.* TO 'application'@'%';
FLUSH PRIVILEGES;



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
  nombre VARCHAR(255) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  licencia VARCHAR(255) NOT NULL,
  fecha_vencimiento_licencia DATE NOT NULL,
  tipo_licencia VARCHAR(1) NOT NULL CHECK (tipo_licencia IN ('C', 'D', 'F', 'E'))
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
    FOREIGN KEY (driver_id) REFERENCES Driver(id) ON DELETE CASCADE
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
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    username VARCHAR(150) NOT NULL UNIQUE
);

Select * FROM user;
-- Tabla intermedia para la relacion User-Role (muchos a muchos)
CREATE TABLE User_Role (
    user_id INT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES Role(id),
    PRIMARY KEY (user_id, role_id)
);

SELECT * FROM Vehicle;
-- Inserta datos en la tabla Vehicle
INSERT INTO Vehicle (license_plate, model, year, status, mileage, fuel_consumption)
VALUES
    ('ABC123', 'Toyota Corolla', 2020, 'available', 15000, 6.5),
    ('DEF456', 'Honda Civic', 2019, 'rented', 22000, 7.2),
    ('GHI789', 'Ford Focus', 2021, 'maintenance', 12000, 6.8),
    ('JKL012', 'Chevrolet Cruze', 2018, 'out_of_service', 45000, 7.5);

INSERT INTO Driver (id, nombre, apellido, licencia, fecha_vencimiento_licencia, tipo_licencia)
VALUES
    (1, 'Juan', 'Prez', 'LIC123456', '2025-01-01', 'C'),
    (2, 'Maria', 'Gomez', 'LIC654321', '2024-06-01', 'D'),
    (3, 'Luis', 'Torres', 'LIC789123', '2023-12-01', 'E'),
    (4, 'Ana', 'Martinez', 'LIC321987', '2025-03-01', 'F');


INSERT INTO Rental (vehicle_id, driver_id, rental_date, return_date, total_distance)
VALUES
    (1, 1, '2024-01-05', '2024-01-10', 500),
    (2, 2, '2024-02-15', '2024-02-20', 300),
    (3, 3, '2024-03-01', NULL, 0), -- Este vehículo aún no ha sido devuelto
    (1, 4, '2024-04-10', '2024-04-15', 400);

INSERT INTO Maintenance (vehicle_id, maintenance_date, description, cost)
VALUES
    (1, '2024-01-20', 'Cambio de aceite y revision de frenos', 150.00),
    (3, '2024-02-10', 'Reparacion de la caja de cambios', 800.00),
    (2, '2024-03-15', 'Revision general y alineacion', 200.00),
    (4, '2024-04-01', 'Cambio de llantas', 400.00);
Select * from user;
-- Insertar roles
INSERT INTO Role (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');
-- Insertar usuario default
INSERT INTO user (country, firstname, lastname, password, role, username) 
VALUES ('Panama', 'Jose', 'Camarena', '$2y$10$uHSK88vPUqAxA0OjKqINkuTFl4PhCMiNWYe2PNezfXULJh1errmzC', 'ADMIN', 'jcamarena');

INSERT INTO user (country, firstname, lastname, password, role, username) 
VALUES ('Panama', 'Oscar', 'Perez', '$2y$10$uHSK88vPUqAxA0OjKqINkuTFl4PhCMiNWYe2PNezfXULJh1errmzC', 'ADMIN', 'operez');

-- Relacionar usuarios con roles
INSERT INTO User_Role (user_id, role_id) VALUES
    (1, 1),
    (2, 1) -- admin con ROLE_ADMIN

