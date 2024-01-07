import React, {FC} from 'react';
import styles from "./SessionButton.module.css"
import Button from "../../../UI/Button/Button";

interface SessionButtonProps {
    time: string;
    price: number;
}

const SessionButton:FC<SessionButtonProps> = ({time, price}) => {
    return (
        <div className={styles.btnContainer}>
            <Button>
                <span className={styles.timeContainer}>{time}</span>
            </Button>
            <span className={styles.priceContainer}>{price} ₽</span>
        </div>
    );
};

export default SessionButton;