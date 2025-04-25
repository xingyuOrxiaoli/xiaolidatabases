import React from 'react';

interface Expense {
  id: number;
  description: string;
  amount: number;
  date: string;
}

interface ExpenseListProps {
  expenses: Expense[];
}

const ExpenseList: React.FC<ExpenseListProps> = ({ expenses }) => {
  return (
    <div>
      <h2>消费记录</h2>
      <ul>
        {expenses.map(expense => (
          <li key={expense.id}>
            <span>{expense.description}</span> - 
            <span>¥{expense.amount}</span> - 
            <span>{new Date(expense.date).toLocaleDateString()}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpenseList;