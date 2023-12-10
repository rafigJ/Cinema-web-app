import React, {FC, PropsWithChildren} from 'react';
import ReactDOM from "react-dom";
import "./modal.css"

interface ModalProps extends PropsWithChildren{
    active: boolean;
    setActive: (b: boolean) => void;
}


const Modal: FC<ModalProps> = ({active, setActive, children}) => {
    return ReactDOM.createPortal(
        <div className={active ? 'modal navigation__link' : 'modal'} onClick={() => setActive(false)}>
            <div className={active ? 'modal__content navigation__link' : 'modal__content'} onClick={(e) => e.stopPropagation()}>
                {children}
            </div>
        </div>,
        // @ts-ignore todo удалить
        document.getElementById('modal-root')
    );
};

export default Modal;