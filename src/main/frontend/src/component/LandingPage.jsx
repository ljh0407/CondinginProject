import React, { useEffect } from 'react';
import axios from 'axios';

export default function LandingPage(props) {  //로그아웃

  const onClickHandler = () => {
      axios.get(`/member/logout`).then((response) => {
        if (response.data.success) {
          navigate("/login");
        } else {
          alert("로그아웃에 실패했습니다");
        }
    });
  };

  return (
    <div>
      <button onClick={onClickHandler}>로그아웃</button>
    </div>
  );
}
