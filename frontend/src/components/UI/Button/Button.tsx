import React, {ButtonHTMLAttributes, PropsWithChildren} from 'react';
import classes from "./Button.module.css"
import '../../../styles/App.css'

interface ButtonProps extends PropsWithChildren, ButtonHTMLAttributes<HTMLButtonElement> {
    isIconButton?: boolean;
}

const Button: React.FC<ButtonProps> = ({isIconButton = false, children, ...props}) => {
    return (
        <button className={
            isIconButton ?
                classes.iconBtn
                :
                classes.btn
        } {...props}>
            {children}
        </button>
    );
};

export default Button;