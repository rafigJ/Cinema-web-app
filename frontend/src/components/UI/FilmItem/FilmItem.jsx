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
            {/*<div className={*/}
            {/*    isActive ? classes.centeredBox : classes.hidden*/}
            {/*}>*/}
            {/*    <h3>{name}</h3><br/>*/}
            {/*    <span>{year}</span><br/>*/}
            {/*    {genres.map(el =>*/}
            {/*        <div key={el.id}>*/}
            {/*            <span>{el.name}</span>*/}
            {/*            <br/>*/}
            {/*        </div>*/}
            {/*    )}*/}
            {/*</div>*/}
        </article>
    );
};

export default FilmItem;