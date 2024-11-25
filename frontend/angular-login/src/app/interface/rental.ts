import { Driver } from "./driver";
import { Vehicle } from "./vehicle";

export interface Rental {
    id: number;
    vehicle: Vehicle;
    driver: Driver;
    rentalDate: Date;
    returnDate: Date;
    totalDistance: number;
}
