//@ts-nocheck
import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import{MyRoute} from './Route/MyRoute'
import {ModelsProvider} from './Provider/ModelsProvider'

ReactDOM.render(

 
  <ModelsProvider>
  <MyRoute />
  </ModelsProvider>
 ,

  document.getElementById("data-myd3")
); 




// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
