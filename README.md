# 🎰 Sparxie • HoYoverse-inspired Pull Simulator

**Banner pulling simulator** inspired by the mobile gacha game "Honkai: Star Rail" by HoYoverse (miHoYo).

> [!NOTE]
> The purpose of this project is to learn the basics of Event-Driven Architecture (EDA) and make use of
declarative REST clients (Spring Cloud OpenFeign). **Therefore, this system is not meant to be deployed in any kind of
production environment.**

## Features

- Fully functional **pulling** system
- **Pity** system (guaranteed wins after experiencing losses)
- **Inventory** tracking
- Mock **shop** to "purchase" items
- **Banner**, **character**, **weapon** and **material** lookup
- 3rd-party **OAuth2 authentication** (Google, GitHub, Discord, Twitch) supported

## Technologies

- Spring Boot 4.x
- PostgreSQL
- MongoDB
- Database migration tools (Liquibase, Flamingock)
- 3rd-party profile authentication management (Supabase)
- Apache Kafka
- Redis caching (through Redisson client)

## Installation setup

This section will guide you through the installation process.

### Prerequisites

- Docker
- A UNIX shell (like Git Bash)
- Apache Maven

### Guide

1. Manually set the environment variables inside `.env` files or run the `setup-env.sh` script to do the job for you.
2. Run the `run-docker.sh` script to start the services.
3. Make HTTP calls to the available endpoints.

> [!TIP]
> If you have an HTTP client like **Bruno** (or Postman), you can import the whole request collection at
`api-tests/bruno/Sparxie` and try them out easily.

4. Run the `run-docker.sh` script again, but this time to stop the services.

## License

This project is licensed under [MIT](https://opensource.org/license/mit).
