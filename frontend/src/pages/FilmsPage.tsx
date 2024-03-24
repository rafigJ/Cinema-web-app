import React, { FC } from 'react'
import '../styles/App.css'
import FilmGrid from '../components/user/FilmGridUI/FilmGrid/FilmGrid'


const FilmsPage: FC = () => {
	
	return (
		<main className='main-page'>
			<FilmGrid />
		</main>
	)
}

export default FilmsPage