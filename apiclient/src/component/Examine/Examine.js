import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import ReactDatePicker from "react-datepicker";
import "./Style.css";
import apiClient from "../../axios/apiClient";
import axios from "axios";
function ExamineComponent() {
  const [tcnumber, setTcnumber] = useState("");
  const [birthDate, setBirthDate] = useState(new Date());
  
  const [getResult, setGetResult] = useState(null);

  const request={
    tckn:tcnumber, 
     birthDate: birthDate.getFullYear() + '-' + ("0"+(birthDate.getMonth() + 1)).slice(-2) + '-' + ("0" + (birthDate.getDate())).slice(-2) ,
     
  }
  const handleSubmit = (evt) => {

     evt.preventDefault();
     console.log(birthDate);
     const response =getAllData();
     response.then(function (item){
       if(item.creditStatus === 0 ){
           setGetResult("Kredi Başvurunuz Onaylandı. Limitiniz : "+item.creditAmount);
       }
       else {
         setGetResult("Kredi Başvurunuz Reddedildi!");
       }
     });
   }
  
  const formatResponse = (res) => {
    return JSON.stringify(res, null, 2);
  };

 async function getAllData() {
    let response=null;
    try {
      console.log(request.birthDate.getMonth);
      console.log(birthDate);
           const url = "http://localhost:8085/credit/"+request.tckn+"/"+request.birthDate;
            response=axios.get(url).then(function (response) { 

              return response.data;
            });
         } catch (err) {
            console.log(err);
            setGetResult(formatResponse(err.response?.data || err));
         } 
          return response;
  }
  return (
    <div>  
    <form onSubmit={e => {handleSubmit(e)}}>
      <label>
          T.C Kimlik Numarası:
          <input
            name="tcnumber"
            type="number"
            className="phoneNumber"
            value={tcnumber}
            onChange={e => setTcnumber(e.target.value)} />
        </label> 
        <label>
          Doğum Tarihi:
        <DatePicker selected={birthDate} dateFormat="dd-MM-yyyy" onChange={(date) => setBirthDate(date)} />
        </label>
      <input type="submit" value="Sorgula" />
    </form>
    <label>{getResult}</label> 
    </div>
  );
} 

export default ExamineComponent;