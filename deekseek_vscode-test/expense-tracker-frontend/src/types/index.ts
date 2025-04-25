export interface Expense {
    id: number;
    description: string;
    amount: number;
    date: string;
}

export interface ExpenseFormData {
    description: string;
    amount: number;
    date: string;
}

export interface ApiResponse<T> {
    data: T;
    message: string;
    success: boolean;
}