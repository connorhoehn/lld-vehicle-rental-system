
# Vehicle Rental System

## Planned Features & Requirements

### Filters
- Location-based filtering
- Price filtering
- Model/Year filtering
- Ratings filtering
- Combinational filters (optional)

### Sorting
- Sort by closer locations
- Sort by better rating

### Core Operations
- Book vehicle
- Check booking status
- Cancel booking
- Pay for vehicle
- Return vehicle

### Design Considerations
- Vehicle factory pattern
- Get vehicle next availability
- Payment strategy interface & adapter
	- Example: `paypalPayment = new PaypalPaymentStrategy()`
	- Usage: `rentalSystem.processPayment(IPaymentStrategy strategy)`