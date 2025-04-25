import React from 'react';

const Header: React.FC = () => {
    return (
        <header>
            <h1>个人消费记录</h1>
            <nav>
                <ul>
                    <li><a href="/">主页</a></li>
                    <li><a href="/report">报告</a></li>
                </ul>
            </nav>
        </header>
    );
};

export default Header;