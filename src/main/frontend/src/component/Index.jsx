import React from 'react';
import Header from './Header';
import Footer from './Footer';
import logo from '../img/logo.png'
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';

export default function Index( props ){
     return  (
            <div className="box">
                <BrowserRouter>
                    <Header/>
                        <h3>메인페이지</h3>
                    <Footer/>
                    <Routes>
                        <Route path="/"  />
                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
















