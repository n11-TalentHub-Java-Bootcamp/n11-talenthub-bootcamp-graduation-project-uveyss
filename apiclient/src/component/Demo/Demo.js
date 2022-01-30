import React from "react";
import Warper from "./Warper";
import Popup from "reactjs-popup";
//

const contentStyle = {
  maxWidth: "600px",
  width: "90%"
};

const CustomModal = () => (
  <Popup
    trigger={<button className="button"> Kredi Sonucu Öğren </button>}
    modal
    contentStyle={contentStyle}
  >
    {close => (
      <div className="modal" style={{backgroundColor:'white'}}>
        <a className="close" onClick={close}>
          &times;
        </a>
        <div className="header"> Onay </div>
        <div className="content">
          {" "}
          Kullanıcı Bilgileriniz Kaydedilecek Onaylıyor musunuz
        </div>
        <div className="actions">
         
          <button
            //className="button"
            onClick={() => {  
              close();
            }}
          >
            close modal
          </button>
        </div>
      </div>
    )}
  </Popup>
);

export default Warper(CustomModal);
