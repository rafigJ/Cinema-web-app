import React, {FC} from 'react';
import {Splide, SplideSlide, SplideTrack} from "@splidejs/react-splide";
import FilmCard from "../../user/FilmGridUI/FilmCard/FilmCard";
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
            fixedHeight: 350,
            gap: '2rem',
            type: 'slide',
            autoHeight: true,
            autoWidth: true,
            pagination: false,
        }} hasTrack={false} tag="section">
            <div className="custom-wrapper">
                <SplideTrack>
                    {films.map(f =>
                        <SplideSlide style={{marginTop: "15px"}} key={f.id}>
                            <FilmCard film={f}/>
                        </SplideSlide>
                    )}
                </SplideTrack>
            </div>
        </Splide>
    );
};

export default FilmsSlider;