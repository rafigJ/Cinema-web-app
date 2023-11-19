import React, {useState} from 'react';
import classes from './FilmItem.module.css'
// todo исправить моргание постера
const FilmItem = ({poster, name, genres, year, id}) => {
    const [isActive, setIsActive] = useState(false)

    return (
        <article className={classes.filmContainer}
                 onMouseEnter={() => setIsActive(true)}
                 onMouseLeave={() => setIsActive(false)}
                 onMouseDown={() => console.log("нажат постер")}
        >
            <img className={isActive ? classes.activePoster : classes.poster} alt="" src={poster}/>
            <div className={
                isActive ? classes.centeredBox : classes.hiddenBox
            }>
                <h4>{name}, {year}</h4>
                <ul>
                    {genres.map(genre =>
                        <li key={genre.id}>{genre.name}</li>
                    )}
                </ul>
            </div>
        </article>
    );
};

export default FilmItem;