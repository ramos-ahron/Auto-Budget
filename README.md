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
- Java JDK 11+
- Maven 3.6+
- Node.js 14+ and npm
- MySQL 8
- A Google Gemini API key ([Google AI Studio](https://aistudio.google.com/))

## How to Use

### Option 1: Docker (Recommended)
1. Clone this repository.
2. Create a `.env` file in the project root:
   ```
   DB_ROOT_PASSWORD=your_root_password
   ```
3. Build and start all services:
   ```
   docker compose up --build
   ```
4. Open the app:
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080

### Option 2: Run Locally
**Database**
1. Create a MySQL database named `budgetgeneratordatabase`.
2. Run the scripts in `sql_files/`.

**Backend**
1. Set the datasource and Gemini API key in the backend configuration:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/budgetgeneratordatabase
   SPRING_DATASOURCE_USERNAME=<username>
   SPRING_DATASOURCE_PASSWORD=<password>
   ```
2. Build and run:
   ```
   cd BE
   mvn clean install
   mvn spring-boot:run
   ```
   Server runs at http://localhost:8080.

**Frontend**
```
cd frontend
npm install
npm start
```
App runs at http://localhost:3000.

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
  - Verify your Gemini API key is set and valid.
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
