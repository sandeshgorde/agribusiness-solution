# FarmBridge: Agribusiness Solution

Lightweight workflow connecting farmers/FPOs to buyers: produce lots, offers, orders and logistics in one system.

Deliverables for the Junior Software Developer: Agribusiness Solution internship.

| Week | Deliverable | Status |
|------|-------------|--------|
| 1 | Requirements analysis and planning | Done |
| 2 | Software architecture design and documentation | Done |
| 3 | Code implementation and unit testing (prototype) | Done |
| 4 | Evaluation, debugging and presentation documentation | Done |

> This repository contains design documentation (Weeks 1-2), a small working prototype (Week 3) and its evaluation and fixes (Week 4) in [farmbridge-core](farmbridge-core). The prototype covers produce lot and buyer requirement validation plus matching, with 17 unit tests. It is a plain Java module, not the full Spring Boot application.

## Problem

Farm records, market prices, buyer needs and logistics live in separate places (paper, WhatsApp, calls). Farmers lose time and margin finding buyers and arranging transport.

## MVP workflow

Farmer/FPO → Produce Lot → Buyer Requirement → Offer → Order → Logistics → Delivery → Completion

## Worked example (illustrative data)

| Step | Actor | Data |
|------|-------|------|
| 1. Produce Lot | Farmer | Tomato, 500 kg, Grade A, harvest ready 3 Oct, Amravati |
| 2. Buyer Requirement | Wholesaler | Tomato, 400 kg, Grade A, delivery by 6 Oct, Nagpur |
| 3. Offer | Farmer | 400 kg at ₹18/kg, valid 24h |
| 4. Order | Wholesaler | Accepts offer, Order #1001 = ₹7,200; lot stock 500 kg → 100 kg |
| 5. Logistics | Transporter | Pickup 5 Oct, drop 6 Oct |
| 6. Completion | System | Delivery confirmed, order completed |

## Week 2: Architecture

FarmBridge is designed as a **modular monolith**: one Spring Boot application with strict module boundaries and one PostgreSQL database. This fits the pilot sizing (about 460 users, about 10 requests/s at the stress point) and keeps the "accept offer" step a single database transaction.

![System architecture](docs/diagrams/01-system-architecture.png)

More diagrams:

- [Component interaction](docs/diagrams/02-component-interaction.png)
- [Data flow (Order #1001)](docs/diagrams/03-data-flow.png)
- [Entity-relationship model](docs/diagrams/04-entity-relationship.png)
- [Order state machine](docs/diagrams/05-order-state-machine.png)

Diagram sources (Graphviz) are in [`docs/diagrams/source`](docs/diagrams/source).

## Week 3: Prototype

A small working slice of the design, in [`farmbridge-core`](farmbridge-core):

- `ProduceLot` and `BuyerRequirement`: immutable models that reject bad input (empty crop, quantity <= 0, grade not A/B/C)
- `MatchingService`: a lot matches a requirement only if crop, quantity and grade all satisfy the buyer
- 11 JUnit 5 tests covering success, each failure condition, and validation errors

Run the tests (needs Java 21 and Maven):

```bash
cd farmbridge-core
mvn test
```

## Week 4: Evaluation and debugging

I attacked the Week 3 prototype with extra tests ([`BugHuntTest`](farmbridge-core/src/test/java/com/farmbridge/service/BugHuntTest.java)) and measured its speed ([`PerfCheck`](farmbridge-core/src/test/java/com/farmbridge/service/PerfCheck.java)).

| Finding | Fix | Evidence |
|---------|-----|----------|
| NaN and Infinity quantity passed validation (`NaN <= 0` is false) | `!Double.isFinite(quantityKg) \|\| quantityKg <= 0` | 4 failing tests, now passing |
| Regex grade check compiled a pattern on every object creation | Plain character check | 1M object pairs: 534-707 ms before, 6-72 ms after |

Logs before and after each change are in [`docs/week4-logs`](docs/week4-logs). Timing is a crude single-machine loop, not a proper benchmark; the report explains the limits.

## Proposed stack

- Backend: Java 21 + Spring Boot 3
- Database: PostgreSQL 16
- Frontend: React (mobile-first)
- API: REST/JSON, JWT authentication with role-based access
- Deployment: Docker Compose (Nginx, app, database); cloud in a later week

## Docs

**Week 4 (evaluation and debugging)**

- [Week 4 report: Evaluation, Debugging and Presentation Documentation](docs/Week_4_Evaluation_Debugging_and_Presentation.docx)
- [Before and after logs](docs/week4-logs)

**Week 3 (implementation)**

- [Week 3 report: Code Implementation and Unit Testing](docs/Week_3_Code_Implementation_and_Unit_Testing.docx)
- [Prototype code and tests](farmbridge-core)

**Week 2 (architecture)**

- [Week 2 report: Software Architecture Design and Documentation](docs/Week_2_Architecture_Design.docx)
- [Architecture summary](docs/architecture.md)

**Week 1 (requirements)**

- [Week 1 report: Requirements Analysis and Planning](docs/Week_1_Agribusiness_Requirements_Analysis.docx)
- [Requirements](docs/requirements.md)
- [Roadmap](docs/roadmap.md)
- [Risk register](docs/risk-register.md)

## Scope decision

Complements existing agri infrastructure. Does not recreate e-NAM or government registries.
