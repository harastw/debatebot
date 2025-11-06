# Dialog Backend  
Spring Boot backend for a dialogue management system with PostgreSQL storage and Docker support.

This service handles conversation state, user sessions, and message routing.  
It’s designed to be **self-contained**, **container-native**, and easy to run locally for development or testing.

## Quick Start

1. Clone the repo  
2. From the root directory:
   ```bash
   ./mvnw clean package -DskipTests
   cd dialog-spring
   docker-compose up --build


> 💡 Если используешь Windows — добавь альтернативу с `mvn` или уточни: «требуется Maven в PATH».

#### 4. **Требования** (если неочевидны)  
> Только то, без чего **ничего не запустится**.

```md
## Requirements
- Java 17+
- Maven (or use `./mvnw`)
- Docker & Docker Compose

## Project Structure
- `dialog-core` — shared domain models  
- `dialog-spring` — web layer, REST API, Docker config  
- `dialog-fx` — (опционально: desktop client)

## Links
- [API Docs](http://localhost:8080/swagger-ui.html) (after launch)  
- [Configuration](dialog-spring/src/main/resources/application-docker.properties)  
- [Roadmap](ROADMAP.md) *(если есть)*