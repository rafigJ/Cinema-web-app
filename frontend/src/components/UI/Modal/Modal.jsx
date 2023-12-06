import React from 'react';
import ReactDOM from "react-dom";
import "./modal.css"

const Modal = ({active, setActive, children}) => {
    return ReactDOM.createPortal(
        <div className={active ? 'modal navigation__link' : 'modal'} onClick={() => setActive(false)}>
            <div className={active ? 'modal__content navigation__link' : 'modal__content'} onClick={(e) => e.stopPropagation()}>
                {children}
            </div>
        </div>,
        document.getElementById('modal-root')
    );
};

export default Modal;