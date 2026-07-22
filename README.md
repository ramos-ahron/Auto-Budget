# Auto Budget – Smart Financial Management

Auto Budget is a full-stack personal finance application for tracking income and expenses. It uses Google's Gemini LLM to provide data-driven insights, budget recommendations, and spending forecasts, with a React frontend, a Spring Boot REST API, and a MySQL database.

## Features

### Core Functionality
- **Account Management:**
  - Register and log in to a personal profile.
- **Income & Expense Tracking:**
  - Add salary and other income sources.
  - Log recurring and one-time expenses.
  - Spending is organized into categories.
- **Visual Analytics:**
  - Charts and graphs of spending over time.
- **Goal Setting:**
  - Define financial goals and track progress.

### AI-Powered Features
- **Pattern Recognition:** Identifies spending habits you might miss.
- **Smart Suggestions:** Actionable advice to optimize your budget.
- **Financial Forecasting:** Projects future scenarios from current behavior.
- **Goal Optimization:** Recommends adjustments to reach targets faster.

### Technical Highlights
- **Layered Architecture:** Separate React client, Spring Boot REST API, and MySQL database.
- **Containerized Deployment:** Single-command startup with Docker Compose.
- **Automated DB Initialization:** SQL scripts run on first container start.

## Technologies Used

- **Java (Spring Boot):** Backend REST API, built with Maven.
- **React.js:** Frontend user interface.
- **MySQL 8:** Relational data storage.
- **Google Gemini LLM:** AI recommendations and forecasting.
- **Docker / Docker Compose:** Containerized deployment.

## Project Structure

```
BE/                      # Spring Boot backend
frontend/                # React client
sql_files/               # Database init scripts
testing/                 # Tests
docker-compose.yml       # Orchestrates MySQL, backend, and frontend
README.md                # Main project documentation
```

## Setup Instructions

### Software Requirements
- **Docker and Docker Compose** (recommended), or:
- Java JDK 21 (Maven is optional; the `mvnw` wrapper downloads it)
- Node.js 20+ and npm
- MySQL 8
- A Google Cloud project with Vertex AI enabled, and a service account key for it
- Optional: a Gmail account with an app password, for emailed budget receipts

## How to Use

### Option 1: Docker (Recommended)
1. Clone this repository.
2. Create a `.env` file in the project root:
   ```
   DB_ROOT_PASSWORD=your_root_password
   # Optional, needed only for emailed receipts
   MAIL_USERNAME=you@gmail.com
   MAIL_PASSWORD=your_gmail_app_password
   ```
3. Save your Google Cloud service account key as `BE/src/main/resources/application_default_credentials.json` (it is gitignored), and set `spring.ai.vertex.ai.gemini.project-id` in `BE/src/main/resources/application.properties` to your project.
4. Build and start all services:
   ```
   docker compose up --build
   ```
5. Open the app:
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080

### Option 2: Run Locally
**Database**
1. Create a MySQL database named `budgetgeneratordatabase`.
2. Run the scripts in `sql_files/`.

**Backend**
1. Set up the Vertex AI credentials as in step 3 of the Docker option.
2. Set these environment variables (defaults are in `BE/src/main/resources/application.properties`):
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/budgetgeneratordatabase
   SPRING_DATASOURCE_USERNAME=<username>
   SPRING_DATASOURCE_PASSWORD=<password>
   MAIL_USERNAME=<gmail address>        # optional
   MAIL_PASSWORD=<gmail app password>   # optional
   ```
3. Build and run:
   ```
   cd BE
   ./mvnw spring-boot:run
   ```
   Server runs at http://localhost:8080.

**Frontend**
```
cd frontend
npm install
npm start
```
App runs at http://localhost:3000.

**Tests**
```
cd BE
./mvnw test
```
`BudgetGeneratorApplicationTests` starts the full app, so it needs MySQL running. The other tests don't. The Selenium UI tests in `testing/` expect the whole app to be running; see `testing/README.md`.

### Using the App
1. Register or log in.
2. Add your income sources.
3. Add recurring and one-time expenses.
4. Explore charts, AI recommendations, goals, and forecasts.

## Troubleshooting

### Startup Issues
- **Port Already in Use:**
  - Free ports 3000, 8080, or 3306, or change the mappings in `docker-compose.yml`.
- **Backend Cannot Connect to Database:**
  - Wait for the MySQL healthcheck to pass.
  - Confirm datasource credentials match your database settings.

### AI Issues
- **No Recommendations Returned:**
  - Verify the Vertex AI credentials file and project ID are set and valid.
  - Check backend logs for API errors.

### Frontend Issues
- **Blank Page or Failed Requests:**
  - Confirm the backend is running at http://localhost:8080.

## Contributors

- Samuel Laquerre
- Ahron Ramos
- Paulo Pineda
- Grazia Mena
- Jolie Jony
- Maliha Chowdhury Adrita

## License

This project is licensed under the MIT License. See the LICENSE file for details.

This project was created for SENG 401, University of Calgary.
