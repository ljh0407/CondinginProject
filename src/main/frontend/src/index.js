import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// 1. 사용할 컴포넌트 호출 [import 컴포넌트명 from 파일명 ]
import Header from './component/Header';
import Index from './component/Index';
import Footer from './component/Footer';
/*import Bview from './component/board/Bview';
import Bwrite from './component/board/Bwrite';*/
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <Index />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
