# FarmBridge: Agribusiness Solution

Lightweight workflow connecting farmers/FPOs to buyers: produce lots, offers, orders and logistics in one system.

Week 1 (requirements analysis) deliverable for the Junior Software Developer: Agribusiness Solution internship.

## Problem

Farm records, market prices, buyer needs and logistics live in separate places (paper, WhatsApp, calls). Farmers lose time and margin finding buyers and arranging transport.

## MVP workflow

Farmer/FPO → Produce Lot → Buyer Requirement → Offer → Order → Logistics → Delivery → Completion

## Worked example (illustrative data)

| Step | Actor | Data |
|------|-------|------|
| 1. Produce Lot | Farmer | Tomato, 500 kg, Grade A, harvest ready 3 Oct, Amravati |
| 2. Buyer Requirement | Wholesaler | Tomato, 400 kg, Grade A, delivery by 6 Oct, Nagpur |
| 3. Offer | Farmer | 400 kg at ₹X/kg, valid 24h |
| 4. Order | Wholesaler | Accepts offer, order ID generated |
| 5. Logistics | Transporter | Pickup 5 Oct, drop 6 Oct |
| 6. Completion | System | Delivery confirmed, payment status updated |

## Proposed stack

- Backend: Java + Spring Boot
- Database: PostgreSQL
- Frontend: React
- API: REST/JSON
- Deployment: Docker-ready, cloud in later week

## Docs

- [Requirements](docs/requirements.md)
- [Architecture](docs/architecture.md)
- [Roadmap](docs/roadmap.md)
- [Risk register](docs/risk-register.md)

## Scope decision

Complements existing agri infrastructure. Does not recreate e-NAM or government registries.
