import React, {useEffect, useState} from 'react';
import axios from 'axios';

export default function App() {
  const [cuisines, setCuisines] = useState([]);
  useEffect(()=>{
    axios.get('http://localhost:8080/api/cuisines').then(r=>setCuisines(r.data)).catch(()=>{});
  },[]);
  return (
    <div style={{padding:24}}>
      <h1>OneBanc - MERN Client</h1>
      <ul>{cuisines.map(c=> <li key={c.id}>{c.name}</li>)}</ul>
      <p>Open README in server/ and client/ for run instructions.</p>
    </div>
  );
}
