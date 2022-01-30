import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./Style.css";
import apiClient from "../../axios/apiClient";
import ExamineComponent from "../Examine/Examine";
import axios from "axios";




function UserFormComponent() {
  const [name, setName] = useState("");
  const [userName, setUserName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [tcnumber, setTcnumber] = useState("");
  const [mountlyIncome, setMountlyIncome] = useState("");
  const [colleteralAmount, setColleteralAmount] = useState("");
  const [birthDate, setBirthDate] = useState(new Date());
  const [recordDate, setRecordDate] = useState(new Date());
  const [popupFlag,setPopupFlag]= useState(false); 
  const [getResult, setGetResult] = useState(null);

  const request={
   userTckn: tcnumber,
    userName: name,
    fullName: name,
    userPhone: phoneNumber,
    recordDate: recordDate,
    birthDate:"1995-01-04",
    montlyIncome:mountlyIncome,
    colleteralAmount: colleteralAmount
  }
  const handleSubmit = (evt) => {
      evt.preventDefault();
      console.log(request)
     const responseAll= getAllData();
     responseAll.then(function(item){
        
       if(item.status==="SUCCESS"){
         setGetResult("Kayıt Başarılı");
       }
       else{
        setGetResult("Kayıt Başarısız");
       }
     })
  }
  
  const formatResponse = (res) => {
    return JSON.stringify(res, null, 2);
  };
  async function getAllData() {
    let responseUser=null;
    try {
           const url = "http://localhost:8085/users/";
           responseUser=axios.post(url,
              {
                  userTckn: tcnumber,
                  userName: name,
                  fullName: name,
                  userPhone: phoneNumber,
                  recordDate: recordDate,
                  birthDate:"1995-01-04",
                  montlyIncome:mountlyIncome,
                  colleteralAmount: colleteralAmount,
              })
              .then(function (response) { 
                console.log('response.data.status');
              console.log(response.data);
              return response.data;
            });
         } catch (err) {
            console.log(err);
            setGetResult(formatResponse(err.response?.data || err));
         } 
          return responseUser;
  }
   

  return (
    <div> 
    <form onSubmit={e => {handleSubmit(e)}}>
      <label>
        Kullanıcı Adı:
        <input 
          type="text"
          name="userName"
          value={userName}
          onChange={e => setUserName(e.target.value)}
          />
      </label>
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
        İsim Soyisim:
        <input 
          type="text"
          name="name"
          value={name}
          onChange={e => setName(e.target.value)}
          />
      </label>
      <label>
          Telefon Numarası:
          <input
            name="phoneNumber"
            type="number"
            placeholder="+90(5xx xxx xx xx)"
            className="phoneNumber"
            value={phoneNumber}
            onChange={e => setPhoneNumber(e.target.value)} />
        </label>
        <label>
          Aylık Gelir:
          <input
            name="mountlyIncome"
            type="number"
            value={mountlyIncome}
            onChange={e => setMountlyIncome(e.target.value)} />
        </label>
        <label>
          Teminat:
          <input
            name="colleteralAmount"
            type="number"
            value={colleteralAmount}
            onChange={e => setColleteralAmount(e.target.value)} />
        </label>
        <label>
          Doğum Tarihi:
        <DatePicker selected={birthDate} onChange={(date) => setBirthDate(date)} />
        </label>

         
        <DatePicker selected={recordDate} onChange={(date) => setBirthDate(date)} />
      
      <input type="submit" value="Başvur" />
    </form> 
    {getResult}
    </div>
  );
} 

export default UserFormComponent;