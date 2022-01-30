import React, { useState } from "react";
import UserFormComponent from '../UserForm/UserForm';
import ExamineComponent from '../Examine/Examine';
import './Style.css';
function CoreComponents() {

  const [examineFlag,setExamineFlag]= useState(false);
  const handlePage =()=>{
    console.log("Come")
    setExamineFlag(!examineFlag);
  } 


  return (
     <div>
       <div className="buttonArea">
        <input type="button" value="Başvur" onClick={handlePage} />
        <input type="button" value="Sorgula" onClick={handlePage} />
       </div>
       { examineFlag ?
          <ExamineComponent/>:<UserFormComponent/>
          
       }
     </div>
             
  );
}

export default CoreComponents;