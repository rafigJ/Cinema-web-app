import React from 'react';
import ReactDOM from "react-dom";
import "./modal.css"

const Modal = ({active, setActive, children}) => {
    return ReactDOM.createPortal(
        <div className={active ? 'modal active' : 'modal'} onClick={() => setActive(false)}>
            <div className={active ? 'modal__content active' : 'modal__content'} onClick={(e) => e.stopPropagation()}>
                {children}
            </div>
        </div>,
        document.getElementById('modal-root')
    );
};

export default Modal;