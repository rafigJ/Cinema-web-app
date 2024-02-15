import { Result, Spin } from 'antd'
import { FC, useEffect, useState } from 'react'
import SessionService from '../../../api/SessionService'
import { useFetching } from '../../../hooks/useFetching'
import './TicketBooking.css'

interface TicketBookingProps {
	sessionId: number
}

const TicketBooking: FC<TicketBookingProps> = ({ sessionId }) => {
	const [seatGrid, setSeatGrid] = useState([] as number[][])
	const [fetchOccupiedTickets, isLoading, isError, error] = useFetching(async (id: number) => {
		const aboutSessionResponse = await SessionService.getById(id)
		const occupiedResponse = await SessionService.getOccupiedTickets(id)
		const seatArray: number[][] = []
		for (let i = 0; i < aboutSessionResponse.data.hall.rowCount; i++) {
			seatArray[i] = []
			for (let j = 0; j < aboutSessionResponse.data.hall.columnCount; j++) {
				if (occupiedResponse.data.some(ticket => ticket.sessionId === id && ticket.row === i && ticket.column === j)) {
					seatArray[i][j] = -1 // занятые
				} else {
					seatArray[i][j] = 0 // незанятые
				}
			}
		}
		setSeatGrid(seatArray)
	})
	
	useEffect(() => {
		fetchOccupiedTickets(sessionId)
	}, [sessionId])
	
	if (isLoading) {
		return (
				<Spin />
		)
	}
	
	if (isError) {
		return (
			<div className='booking-grid'>
				<Result status='error' title={'Произошла ошибка: ' + error} />
			</div>
		)
	}
	
	
	return (
		<div className='booking-container'>
			<div className='booking-grid'>
				<div className='screen' />
				{seatGrid.map((row, rowIndex) => (
					<div className='seat-row' key={rowIndex}>
						{row.map((col, colIndex) => (
							<div key={`${rowIndex}-${colIndex}`}
							     className={col === -1 ? 'seat occupied' : col === 0 ? 'seat' : 'seat selected'} />
						))}
					</div>
				))}
			</div>
			<ul className='showcase'>
				<li>
					<div className='seat seat_small' />
					<small>Свободные</small>
				</li>
				
				<li>
					<div className='seat selected seat_small' />
					<small>Выбранные</small>
				</li>
				
				<li>
					<div className='seat occupied seat_small' />
					<small>Занятые</small>
				</li>
			</ul>
		</div>
	)
	
}

export default TicketBooking