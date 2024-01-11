import React, {FC} from 'react';
import {Splide, SplideSlide, SplideTrack} from "@splidejs/react-splide";
import FilmGridItem from "../../user/FilmGridUI/FilmGridItem/FilmGridItem";
import {IFilm} from "../../../types/model/IFilm";

interface FilmsSpliderProps {
    films: IFilm[]
}

const FilmsSlider:FC<FilmsSpliderProps> = ({films}) => {
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
                            <FilmGridItem film={f}/>
                        </SplideSlide>
                    )}
                </SplideTrack>
            </div>
        </Splide>
    );
};

export default FilmsSlider;