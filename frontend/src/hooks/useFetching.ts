import {useState} from "react";

type FetchingCallback = (...args: any[]) => Promise<void>;

export const useFetching = (callback: FetchingCallback): [FetchingCallback, boolean, string] => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');

    const fetching: FetchingCallback = async (...args) => {
        try {
            setIsLoading(true);
            await callback(...args);
        } catch (e: any) {
            setError(e.message);
        } finally {
            setIsLoading(false);
        }
    };

    return [fetching, isLoading, error];
};
