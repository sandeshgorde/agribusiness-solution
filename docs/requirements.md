# Requirements

## Functional
1. User registration and role-based access.
2. Farmer/FPO crop and harvest records.
3. Produce lot creation and inventory tracking.
4. Market-price information with source and timestamp.
5. Buyer requirements.
6. Offer and order lifecycle.
7. Logistics request/status.
8. Notifications.
9. Admin verification/support.
10. Audit history.

## Non-functional
- Secure authentication and authorization.
- Mobile-first and multilingual-ready UI.
- Transaction-safe inventory updates.
- Basic observability and error handling.
- Target common API response under ~2 seconds at MVP load.
- 99% monthly availability target excluding planned maintenance.

## Core entities
User, Farm, Crop, ProduceLot, BuyerRequirement, Offer, Order, Shipment, Notification, AuditLog.
