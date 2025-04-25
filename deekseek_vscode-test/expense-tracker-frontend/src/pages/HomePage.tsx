import React from 'react';
import Header from '../components/Header';
import ExpenseList from '../components/ExpenseList';

const HomePage: React.FC = () => {
    return (
        <div>
            <Header />
            <ExpenseList />
        </div>
    );
};

export default HomePage;