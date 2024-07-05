-- Insert brands
INSERT INTO brand (brand_name) VALUES ('Kia');
INSERT INTO brand (brand_name) VALUES ('Mazda');
INSERT INTO brand (brand_name) VALUES ('Honda');
INSERT INTO brand (brand_name) VALUES ('Toyota');

-- Insert car models
INSERT INTO car_model (car_name, brand_id) VALUES ('Rio', (SELECT id FROM brand WHERE brand_name = 'Kia'));
INSERT INTO car_model (car_name, brand_id) VALUES ('Sportage', (SELECT id FROM brand WHERE brand_name = 'Kia'));
INSERT INTO car_model (car_name, brand_id) VALUES ('3', (SELECT id FROM brand WHERE brand_name = 'Mazda'));
INSERT INTO car_model (car_name, brand_id) VALUES ('Civic', (SELECT id FROM brand WHERE brand_name = 'Honda'));
INSERT INTO car_model (car_name, brand_id) VALUES ('Corolla', (SELECT id FROM brand WHERE brand_name = 'Toyota'));

-- Insert car versions
INSERT INTO car_version (version_name, full_name, car_model_id) VALUES ('LX TA', 'Kia Rio LX TA', (SELECT id FROM car_model WHERE car_name = 'Rio'));
INSERT INTO car_version (version_name, full_name, car_model_id) VALUES ('LX', 'Kia Sportage LX', (SELECT id FROM car_model WHERE car_name = 'Sportage'));
INSERT INTO car_version (version_name, full_name, car_model_id) VALUES ('i Sport TM', 'Mazda 3 i Sport TM', (SELECT id FROM car_model WHERE car_name = '3'));
INSERT INTO car_version (version_name, full_name, car_model_id) VALUES ('Touring', 'Honda Civic Touring', (SELECT id FROM car_model WHERE car_name = 'Civic'));
INSERT INTO car_version (version_name, full_name, car_model_id) VALUES ('XLE', 'Toyota Corolla XLE', (SELECT id FROM car_model WHERE car_name = 'Corolla'));
