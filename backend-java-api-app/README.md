# Azure OpenAI API Backend

## Overview
This application is a Java Spring Boot backend API for interacting with Azure OpenAI LLM models, Azure AI Search, and Oracle Database using agent-based Retrieval-Augmented Generation (RAG) operations. It is designed to work as the backend for a prebuilt frontend web app, handling user questions and orchestrating responses using specialized agents.

## Agents
All agent code is located in `src/main/java/com/example/azureopenaiapi/agent/`.

- **ChatAgent**: Sends user prompts directly to Azure OpenAI and returns the LLM's answer.
- **SearchRAGAgent**: Queries Azure AI Search for relevant documents, performs RAG, and returns an answer based on retrieved data.
- **OracleDbRAGAgent**: Builds and executes queries against an Oracle database, performs RAG on the results, and returns an answer.
- **OrchestratorAgent**: Determines which agent(s) to use for each user question. It may use one or multiple agents in parallel, depending on the query.

## Setup
1. **Clone the repository** and open the `backend-java-api-app` folder in VS Code.
2. **Configure environment variables** for secrets and connection info. You can set these in your system environment or in `src/main/resources/application.properties`:
    - `AZURE_OPENAI_API_KEY`, `AZURE_OPENAI_ENDPOINT`, `AZURE_OPENAI_DEPLOYMENT`
    - `AZURE_SEARCH_API_KEY`, `AZURE_SEARCH_ENDPOINT`, `AZURE_SEARCH_INDEX`
    - `ORACLE_DB_URL`, `ORACLE_DB_USERNAME`, `ORACLE_DB_PASSWORD`
3. **Install Oracle JDBC driver** manually if needed (see pom.xml, scope: provided).
4. **Build the project** using Maven:
    ```shell
    mvn clean install
    ```
5. **Run the application**:
    ```shell
    mvn spring-boot:run
    ```

## API Usage
- The backend exposes a single endpoint for chat operations:
    - `POST /api/chat` with the user's question in the request body.
    - Returns the orchestrated answer from the appropriate agent(s).

## Folder Structure
- `src/main/java/com/example/azureopenaiapi/agent/` - All agent classes and logic
- `src/main/java/com/example/azureopenaiapi/controller/` - REST API controller
- `src/main/resources/application.properties` - Configuration file for environment variables
- `pom.xml` - Maven project file

## Notes
- The backend does not include a frontend. Connect your web app to `/api/chat`.
- Sensitive values should be set as environment variables for security.
- For local development, you may set values directly in `application.properties`.

## Troubleshooting
- Ensure all required environment variables are set before running.
- If using Oracle DB, make sure the JDBC driver is available.
- For Azure services, verify your keys and endpoints are correct.

---
For further customization or agent implementation details, see the agent classes in the `agent` folder.
