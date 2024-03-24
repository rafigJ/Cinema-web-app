import React from 'react'
import { GithubOutlined, MailOutlined } from '@ant-design/icons'
import './MyFooter.css'

const MyFooter = () => {
	
	return (
		<footer className='my-footer'>
			<div className='my-footer__application-name'>
				Copyright &copy; 2024 Cinema Now
			</div>
			<div className='my-footer__block'>
				Веб-приложение Кинотеатра для покупки билетов
			</div>
			<div className='my-footer__block'>
				Создан в учебных целях. По всем вопросам обращаться по почте ниже.
			</div>
			<div className='my-footer__contact'>
				<MailOutlined style={{ fontSize: '18px', marginRight: '5px' }} label={'Mail'} />
				<a className='my-footer__block' href='mailto:rafigdzabbarov0410@gmail.com'>rafigdzabbarov0410@gmail.com</a>
			</div>
			<div className='my-footer__contact'>
				<GithubOutlined style={{ fontSize: '18px', marginRight: '5px' }} label={'GitHub'} />
				<a className='my-footer__block' href='https://github.com/rafigJ'>GitHub</a>
			</div>
		</footer>
	)
}

export default MyFooter