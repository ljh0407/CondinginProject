// 1.
import React from 'react';
import StyleSheet from '../css/footer.css'; // css 불러오기
// 2.
export default function Footer(props){
    return (
        <footer>
            <ul id="ful">
                <li id="fli">© CondingIn Korea Corporation All Rights Reserved. </li>
                <li id="flis"> 고은시 | 최예은 | 이종훈 | 이태준 | 주혁 </li>
            </ul>
        </footer>
    );
}