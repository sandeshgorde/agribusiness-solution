# Architecture

```text
[ Farmer / FPO / Buyer / Logistics ]
                |
        React Web Application
                |
        REST API / HTTPS
                |
       Spring Boot Backend
      /        |         \
 Auth/RBAC   Business     Notifications
             Services
                |
          PostgreSQL DB
                |
 External integrations (future):
 market data, maps, SMS/WhatsApp, payments
```

## Design principles
- Keep business rules in backend services.
- Enforce authorization server-side.
- Treat inventory/order changes as transactions.
- Put external integrations behind adapter interfaces.
- Keep external data source and timestamp with market information.
