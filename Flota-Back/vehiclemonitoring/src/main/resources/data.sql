-- Inserta datos en la tabla Vehicle
INSERT INTO Vehicle (license_plate, model, year, status, mileage, fuel_consumption)
VALUES
    ('ABC123', 'Toyota Corolla', 2020, 'available', 15000, 6.5),
    ('DEF456', 'Honda Civic', 2019, 'rented', 22000, 7.2),
    ('GHI789', 'Ford Focus', 2021, 'maintenance', 12000, 6.8),
    ('JKL012', 'Chevrolet Cruze', 2018, 'out_of_service', 45000, 7.5);

INSERT INTO Driver (name, license_number)
VALUES
    ('Juan Prez', 'LIC123456'),
    ('Maria Gomez', 'LIC654321'),
    ('Luis Torres', 'LIC789123'),
    ('Ana Martinez', 'LIC321987');


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

