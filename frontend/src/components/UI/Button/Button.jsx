import React from 'react';
import classes from "./Button.module.css"
import '../../../styles/App.css'
const Button = ({isIconButton = false, children, ...props}) => {
    return (
        <button className={
            isIconButton ?
                classes.iconBtn
                :
                classes.btn
        }
                {...props}>
            {children}
        </button>
    );
};

export default Button;