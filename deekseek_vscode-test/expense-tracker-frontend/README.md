# Expense Tracker

This is a personal expense tracking web application that allows users to record and manage their expenses efficiently.

## Project Structure

- **public/**: Contains static files.
  - **index.html**: The main HTML file for the application.
  - **favicon.ico**: The icon for the website.

- **src/**: Contains the source code for the application.
  - **components/**: Reusable components for the application.
    - **ExpenseList.tsx**: Component to display a list of expenses.
    - **ExpenseForm.tsx**: Component to add new expenses.
    - **Header.tsx**: Component for the application header.
  - **pages/**: Contains the main pages of the application.
    - **HomePage.tsx**: The homepage that includes the header and expense list.
    - **ReportPage.tsx**: Page to display expense reports.
  - **services/**: Contains API interaction functions.
    - **api.ts**: Functions to interact with the backend API.
  - **styles/**: Contains global styles for the application.
    - **global.css**: Global styles for the application.
  - **App.tsx**: The main component that sets up routing and state management.
  - **index.tsx**: The entry point of the application.
  - **types/**: TypeScript interfaces and types.
    - **index.ts**: Type definitions for expenses and other related types.

- **tsconfig.json**: TypeScript configuration file.

- **package.json**: npm configuration file listing dependencies and scripts.

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd expense-tracker-frontend
   ```

3. Install the dependencies:
   ```
   npm install
   ```

## Usage

To start the application, run:
```
npm start
```
This will launch the application in your default web browser.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or features.

## License

This project is licensed under the MIT License.