import React, { useEffect, useState } from 'react';
import { getExpenses } from '../services/api';
import { Expense } from '../types';

const ReportPage: React.FC = () => {
    const [expenses, setExpenses] = useState<Expense[]>([]);
    const [total, setTotal] = useState<number>(0);

    useEffect(() => {
        const fetchExpenses = async () => {
            const data = await getExpenses();
            setExpenses(data);
            const totalAmount = data.reduce((acc, expense) => acc + expense.amount, 0);
            setTotal(totalAmount);
        };

        fetchExpenses();
    }, []);

    return (
        <div>
            <h1>消费报告</h1>
            <h2>总支出: {total} 元</h2>
            <ul>
                {expenses.map((expense) => (
                    <li key={expense.id}>
                        {expense.description}: {expense.amount} 元
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ReportPage;