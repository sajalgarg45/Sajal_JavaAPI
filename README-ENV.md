# Environment Variables Setup

This application requires the following environment variables to run:

## Required Environment Variables

1. **OFFICIAL_EMAIL** - Your Chitkara University email address
2. **GEMINI_API_KEY** - Your Google Gemini API key

## Local Development Setup

### Option 1: Using IDE (Recommended)
Set environment variables in your IDE's run configuration:
- IntelliJ IDEA: Run → Edit Configurations → Environment Variables
- VS Code: Add to `launch.json` env section

### Option 2: Using Terminal
```bash
export OFFICIAL_EMAIL="sajal2357.be23@chitkara.edu.in"
export GEMINI_API_KEY="your-api-key-here"
mvn spring-boot:run
```

### Option 3: Using .env file (with spring-dotenv)
1. Copy `.env.example` to `.env`
2. Fill in your actual values
3. The `.env` file is already in `.gitignore` so it won't be committed

## Render Deployment

In your Render dashboard:
1. Go to your service settings
2. Navigate to "Environment" tab
3. Add the following environment variables:
   - Key: `OFFICIAL_EMAIL`, Value: `sajal2357.be23@chitkara.edu.in`
   - Key: `GEMINI_API_KEY`, Value: `your-actual-gemini-api-key`

## Security Note

- ✅ `.env` is in `.gitignore` - local secrets are safe
- ✅ `application.properties` uses environment variables only
- ✅ No sensitive data in GitHub repository
- ✅ Render uses environment variables from dashboard
