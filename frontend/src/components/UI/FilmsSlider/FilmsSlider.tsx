import React, {FC} from 'react';
import {Splide, SplideSlide, SplideTrack} from "@splidejs/react-splide";
import FilmItem from "../../user/FilmGridUI/FilmItem/FilmItem";
import {IFilm} from "../../../types/model/IFilm";

interface FilmsSliderProps {
    films: IFilm[]
}
/**
 * Нужен для главной страницы HomePage
 */
const FilmsSlider:FC<FilmsSliderProps> = ({films}) => {
    return (
        <Splide options={{
            rewind: true,
            gap: '2rem',
            type: 'slide',
            autoHeight: true,
            autoWidth: true,
            pagination: false,
        }} hasTrack={false} tag="section">
            <div className="custom-wrapper">
                <SplideTrack>
                    {films.map(f =>
                        <SplideSlide key={f.id}>
                            <FilmItem film={f}/>
                        </SplideSlide>
                    )}
                </SplideTrack>
            </div>
        </Splide>
    );
};

export default FilmsSlider;