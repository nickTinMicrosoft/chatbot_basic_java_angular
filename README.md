# ⚠️ EDUCATIONAL USE ONLY

**This repository is intended for educational purposes and code examples only. It is NOT designed or recommended for production use. Use at your own risk.**

# Project Setup Guide

This workspace contains three backend applications (two Java-based, one Python-based) and an Angular frontend. Follow the instructions below to set up and run each environment. Choose the backend you want to use based on your requirements.

---

## 1. Python Backend (`backend-python`)

### Setup Instructions
1. **Install Python 3.10+**
2. Navigate to the `backend-python` directory:
   ```sh
   cd backend-python
   ```
3. (Recommended) Create a virtual environment:
   ```sh
   python -m venv venv
   .\venv\Scripts\activate  # On Windows
   source venv/bin/activate  # On Linux/Mac
   ```
4. Install dependencies:
   ```sh
   pip install -r requirements.txt
   ```
5. Create a `.env` file in the `backend-python` directory (see example below).
6. Run the backend:
   ```sh
   python main.py
   ```

### Example `.env` file
```
AZURE_OPENAI_KEY=your-azure-openai-key
AZURE_SEARCH_ENDPOINT=your-azure-search-endpoint
AZURE_SEARCH_KEY=your-azure-search-key
```

---

## 2. Java Backend 1 (`backend-java-api`)

### Setup Instructions
1. Ensure you have **Java 17+** and **Maven** installed.
2. Navigate to the `backend-java-api` directory:
   ```sh
   cd backend-java-api
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the backend:
   ```sh
   mvn spring-boot:run
   ```

---

## 3. Java Backend 2 (`backend-java-semanticKernel`)

### Setup Instructions
1. Ensure you have **Java 17+** and **Maven** installed.
2. Navigate to the `backend-java-semanticKernel` directory:
   ```sh
   cd backend-java-semanticKernel
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the backend:
   ```sh
   mvn spring-boot:run
   ```

### Setting up Java Backend 2 (`backend-java-semanticKernel`) Secrets

This backend requires Azure OpenAI credentials. Do NOT hardcode secrets in `application.properties` or commit them to version control.

**How to set the required variables:**

1. Create a file named `application.properties` in `backend-java-semanticKernel/src/main/resources/` (if it does not exist).
2. Add the following lines (replace with your actual values):

```
azure.openai.endpoint=your-azure-openai-endpoint
azure.openai.key=your-azure-openai-key
azure.openai.deploymentName=your-azure-deployment-name
```

3. Ensure `application.properties` is listed in `.gitignore` to prevent accidental commits of secrets.
4. Alternatively, you can set these as environment variables for your Java process:
   - `AZURE_OPENAI_ENDPOINT`
   - `AZURE_OPENAI_KEY`
   - `AZURE_OPENAI_DEPLOYMENTNAME`

Refer to your Spring Boot configuration for how environment variables are mapped.

---

## 4. Frontend (Angular)

The frontend is located in the `chat-frontend/client` directory. Please refer to the [frontend README](chat-frontend/client/README.md) for setup and usage instructions.

---

## Additional Notes
- **.gitignore** is configured to prevent committing sensitive keys and unnecessary files for all environments.
- Choose and run only the backend you need for your use case.
