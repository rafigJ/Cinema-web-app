import React, {useState} from 'react';
import FilmService from "../API/FilmService";

const FilmsPage = () => {
    const [query, setQuery] = useState('')
    const search = () => {
        console.log(query);
        console.log(FilmService.getByName(query));
    }

    return (
        <div style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            flexDirection: "column",
            top: "0", left: "0",
            width: "100vw", height: "80vh"
        }}>

            <input
                style={{width: "90vw", color: "whitesmoke", background: "transparent", border: "solid whitesmoke 1px", textAlign:"center"}}
                value={query}
                type="text"
                placeholder="Поиск"
                onChange={e => setQuery(e.target.value)}
            />
            <button onClick={search}>Поиск</button>

        </div>
    );
};

export default FilmsPage;