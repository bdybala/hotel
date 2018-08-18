import React, { Component } from 'react';

import { Table, Glyphicon, Button } from 'react-bootstrap';

import CustomPagination from './CustomPagination';

class RoomsList extends Component {
	constructor(props) {
		super(props);
		this.handleRemoveClick = this.handleRemoveClick.bind(this);
		this.changePage = this.changePage.bind(this);
	}

	handleRemoveClick(id) {
		this.props.deleteRoom(id);
	}

	changePage(page) {
		this.props.changePage(page);
	}

	render () {
		const isLoading= this.props.isLoading;

		if (isLoading) {
			return <p> Loading... </p>;
		}

		if (this.props.rooms) {
			var listOfRooms = this.props.rooms.map((item, i) => {
				return <tr key={item.id}>
							<th>{i+1}</th>
							<th>{item.number}</th>
							<th>{item.maxCapacity}</th>
							<th>{item.isFree}</th>
							<th>{item.roomTypeDescription}</th>
							<th>
								<Button bsStyle="danger" onClick={(e) => this.handleRemoveClick(item.id, e)}  >
									<Glyphicon glyph='glyphicon glyphicon-remove' />
								</Button>
							</th>
						</tr>
			});
		}
		return (
			<div className='RoomsList'>
				<h3>Lista pokojów</h3>
				<CustomPagination changePage={this.changePage} currentPage={this.props.currentPage} totalPages={this.props.totalPages} />
				<Table responsive striped bordered condensed hover>
				<thead>
					<tr>
						<th>#</th>
						<th>Numer</th>
						<th>Pojemność</th>
						<th>Wolny</th>
						<th>Typ pokoju</th>
						<th>Opcje</th>
					</tr>
				</thead>
				<tbody>
					{listOfRooms}
				</tbody>
				</Table>
			</div>
		);
	}
}

export default RoomsList;